package onlineQuiz.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.QuizInfo;
import onlineQuiz.service.QuizService;

public class QuizServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<QuizInfo> quizInfos = QuizService.getQuizzes();

	    req.setAttribute("quizInfos", quizInfos);
	    req.setAttribute("action", "quiz");

		req.getServletContext().getRequestDispatcher("/jsps/quizPage.jsp").forward(req, res);
	}
}
