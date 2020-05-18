package onlineQuiz.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import onlineQuiz.config.HibernateConfigUtil;
import onlineQuiz.dao.QuestionDAO;
import onlineQuiz.domain.Option;
import onlineQuiz.domain.Question;
import onlineQuiz.domain.QuizInfo;
import onlineQuiz.pojo.OptionPOJO;
import onlineQuiz.pojo.QuestionPOJO;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public static Question getQuestion(int questionID, boolean currentSession) {

        Session session = null;
        if(currentSession) session = HibernateConfigUtil.getCurrentSession();
        else session = HibernateConfigUtil.openSession();

        Transaction transaction = null;
        QuestionDAO questionDAO = new QuestionDAO();

        Question question = null;

        try {

            transaction = session.beginTransaction();

            question = questionDAO.getQuestion(questionID, session);
            question.getChoices().size();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return question;
    }

    public static boolean updateQuestionStatus(int questionID, int questionStatus) {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;

        boolean success = true;

        try {
            transaction = session.beginTransaction();

            Question question = getQuestion(questionID, false);
            question.setStatus(questionStatus);

            session.merge(question);

            transaction.commit();

        }  catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            success = false;
            e.printStackTrace();
        } finally {
            session.close();
        }

        return success;
    }

    public static boolean addQuestion(QuestionPOJO questionPOJO) {
        Session session = HibernateConfigUtil.getCurrentSession();
        Transaction transaction = null;

        boolean success = true;

        try {
            transaction = session.beginTransaction();

            Question question = new Question();

            question.setQuestionType(questionPOJO.getQuestionType());
            question.setQuestionString(questionPOJO.getQuestionString());
            question.setModifiedTime(questionPOJO.getModifiedTime());
            question.setStatus(questionPOJO.getStatus());

            List<OptionPOJO> choicePOJOS = questionPOJO.getChoicesPOJOs();
         
            List<Option> choices = new ArrayList<>();
            for(OptionPOJO choicePOJO : choicePOJOS) {
                Option choice = new Option();
                choice.setChoiceString(choicePOJO.getChoiceString());
                choice.setIfCorrect(choicePOJO.isIfCorrect());
                choice.setQuestion(question);
                choices.add(choice);
            }
            for(int i = 0; i < choices.size(); i++) {
            	System.out.println(choices.get(i));
            }
            question.setChoices(choices);

            QuizInfo quizInfo = QuizService.getQuizById(questionPOJO.getQuizID(), false);

            quizInfo.getQuestions().add(question);
            question.setQuizInfo(quizInfo);

            session.merge(question);

            transaction.commit();

        }  catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            success = false;
            e.printStackTrace();
        } finally {
            session.close();
        }

        return success;
    }
}
