package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.UserSubmission;
import onlineQuiz.service.SubmissionService;

import java.io.IOException;

public class adminSubdetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String ID = req.getParameter("id");

        if (ID != null) {

            int submissionId = Integer.parseInt(ID);

            UserSubmission userSubmission = SubmissionService.getSubmissionWithCache(submissionId);

            req.setAttribute("submission", userSubmission);
            req.setAttribute("action", "admin");

            if(userSubmission.getCorrectCount() > userSubmission.getTotalCount() * 0.6)
                req.setAttribute("status", "PASS");
            else
                req.setAttribute("status", "FAIL");

            req.getServletContext().getRequestDispatcher("/jsps/quizPage.jsp").forward(req, res);

        } else {
            res.sendRedirect(req.getContextPath()+"/admin");
        }
    }
}
