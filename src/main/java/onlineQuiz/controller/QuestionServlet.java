package onlineQuiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import onlineQuiz.domain.Question;
import onlineQuiz.domain.QuizInfo;
import onlineQuiz.service.QuizService;

public class QuestionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String ID = req.getParameter("id");

		if (ID != null) {

			int quizID = Integer.parseInt(ID);

			QuizInfo quizInfo = QuizService.getQuizWithCache(quizID);
			List<Question> questions = quizInfo.getQuestions();
			req.setAttribute("quizId", quizInfo.getQuizID());
		    req.setAttribute("questions", questions);
			req.setAttribute("action", "question");

			req.getServletContext().getRequestDispatcher("/jsps/quizPage.jsp").forward(req, res);

		} else {
			res.sendRedirect(req.getContextPath()+"/page/quiz");
		}
	}
}
