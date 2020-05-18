package onlineQuiz.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import onlineQuiz.config.HibernateConfigUtil;
import onlineQuiz.dao.OptionDAO;
import onlineQuiz.domain.Option;

public class OptionService {

    public static boolean ifCorrect(int questionID, String choiceString, boolean currentSession) {

        Session session = null;
        if(currentSession) session = HibernateConfigUtil.getCurrentSession();
        else session = HibernateConfigUtil.openSession();

        Transaction transaction = null;
        OptionDAO choiceDAO = new OptionDAO();
        boolean res = true;

        try {
            transaction = session.beginTransaction();

            res = choiceDAO.ifCorrect(questionID, choiceString, session);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return res;
    }
}
