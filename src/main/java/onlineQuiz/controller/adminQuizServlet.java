package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.QuizInfo;
import onlineQuiz.service.QuestionService;
import onlineQuiz.service.QuizService;

import java.io.IOException;

public class adminQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int quizId = Integer.parseInt(req.getParameter("id"));

        QuizInfo quizInfo = QuizService.getQuizWithCache(quizId);
        req.setAttribute("quiz", quizInfo);

        req.setAttribute("action", "quiz");
        req.getServletContext().getRequestDispatcher("/jsps/adminPage.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int questionID = Integer.parseInt(req.getParameter("questionID"));
        int questionStatus = Integer.parseInt(req.getParameter("status"));
        int quizID = Integer.parseInt(req.getParameter("quizID"));

        questionStatus = questionStatus == 1 ? 2 : 1;
        QuestionService.updateQuestionStatus(questionID, questionStatus);

        res.sendRedirect(req.getContextPath() + "/admin/quiz?id=" + quizID);
    }
}