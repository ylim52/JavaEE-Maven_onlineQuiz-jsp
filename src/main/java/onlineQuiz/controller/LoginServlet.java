package onlineQuiz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import onlineQuiz.config.Response;
import onlineQuiz.domain.UserInfo;
import onlineQuiz.service.UserService;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("action", "login");
		req.getServletContext().getRequestDispatcher("/jsps/loginPage.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		Response response = new Response();

		UserInfo user = UserService.getUser(userName, password);

		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("userID", user.getUserId());
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());

			response.setSuccess(true);
			if (user.getGroupID() == 1) {

				session.setAttribute("role", 1);
				response.setRedirectUrl(req.getContextPath() + "/page/quiz");

			} else if (user.getGroupID() == 2) {

				session.setAttribute("role", 2);
				response.setRedirectUrl(req.getContextPath() + "/admin");

			} else {
				response.setRedirectUrl("/logout");
			}
		} else {

			response.setSuccess(false);
			response.setErrMsg("Incorrect Information");

		}

		PrintWriter writer = res.getWriter();
		Gson gson = new Gson();
		String respJson = gson.toJson(response);
		writer.append(respJson);
	}

}
