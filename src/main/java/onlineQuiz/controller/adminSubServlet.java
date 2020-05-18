package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.UserSubmission;
import onlineQuiz.service.SubmissionService;

import java.io.IOException;
import java.util.List;

public class adminSubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<UserSubmission> userSubmissions = SubmissionService.getSubmissions();
        req.setAttribute("submissions", userSubmissions);

        req.setAttribute("action", "submission");
        req.setAttribute("title", "View Submission");
        req.getServletContext().getRequestDispatcher("/jsps/adminPage.jsp").forward(req, res);
    }
}
