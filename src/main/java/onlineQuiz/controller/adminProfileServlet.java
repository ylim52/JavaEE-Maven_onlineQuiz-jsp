package onlineQuiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlineQuiz.domain.UserInfo;
import onlineQuiz.service.UserService;

import java.io.IOException;
import java.util.List;

public class adminProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        List<UserInfo> userInfos = UserService.getUsers();
        req.setAttribute("users", userInfos);

        req.setAttribute("action", "profile");
        req.setAttribute("title", "View Profile");
        req.getServletContext().getRequestDispatcher("/jsps/adminPage.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));
        int userStatus = Integer.parseInt(req.getParameter("status"));

        userStatus = userStatus == 1 ? 2 : 1;
        UserService.updateUserStatus(userId, userStatus);

        res.sendRedirect(req.getContextPath()+"/admin/profile");
    }
}
