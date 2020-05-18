package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.QuizInfo;
import onlineQuiz.service.QuizService;

import java.io.IOException;
import java.util.List;

public class adminModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<QuizInfo> quizInfos = QuizService.getQuizzes();
        req.setAttribute("quizzes", quizInfos);

        req.setAttribute("action", "modified");
        req.setAttribute("title", "Modified Quiz");
        req.getServletContext().getRequestDispatcher("/jsps/adminPage.jsp").forward(req, res);
    }
}
