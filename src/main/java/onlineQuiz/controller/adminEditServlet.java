package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.Question;
import onlineQuiz.domain.QuizInfo;
import onlineQuiz.service.QuestionService;
import onlineQuiz.service.QuizService;

import java.io.IOException;

public class adminEditServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int questionId = Integer.parseInt(req.getParameter("id"));
        int quizId = Integer.parseInt(req.getParameter("quizId"));

        Question question = QuestionService.getQuestion(questionId, true);
        req.setAttribute("question", question);
        req.setAttribute("quizId", quizId);

        req.setAttribute("action", "editQuestion");
        req.getServletContext().getRequestDispatcher("/jsps/adminPage.jsp").forward(req, res);
    }
}
