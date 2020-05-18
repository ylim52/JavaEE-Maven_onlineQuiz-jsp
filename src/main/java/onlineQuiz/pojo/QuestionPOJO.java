package onlineQuiz.pojo;

import java.util.ArrayList;
import java.util.List;

import onlineQuiz.domain.Option;
import onlineQuiz.domain.QuizInfo;


public class QuestionPOJO {

    private int questionID;
    private String questionType;
    private String questionString;
	private int quizID;
    private String modifiedTime;
    private QuizInfo quizInfo;
    private int status;
    private List<OptionPOJO> choicesPOJOs = new ArrayList<>();

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public QuizInfo getQuizInfo() {
        return quizInfo;
    }

    public void setQuizInfo(QuizInfo quizInfo) {
        this.quizInfo = quizInfo;
    }

    public List<OptionPOJO> getChoicesPOJOs() {
        return choicesPOJOs;
    }

    public void setChoicesPOJOs(List<OptionPOJO> choicesPOJOs) {
        this.choicesPOJOs = choicesPOJOs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
