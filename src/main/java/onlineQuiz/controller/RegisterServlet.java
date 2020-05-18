package onlineQuiz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import onlineQuiz.config.Response;
import onlineQuiz.domain.UserInfo;
import onlineQuiz.pojo.UserInfoPOJO;
import onlineQuiz.service.UserService;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		int groupID = 1;
		int status = 1;
		
		Response response = new Response();

		if (userName == null || userName.length() == 0) {
			response.setSuccess(false);
			response.setErrMsg("Username should not be empty!");
		} else if (password == null || password.length() == 0) {
			response.setSuccess(false);
			response.setErrMsg("Password should not be empty!");
		} else if (firstName == null || firstName.length() == 0) {
			response.setSuccess(false);
			response.setErrMsg("First name should not be empty!");
		} else if (UserService.getUser(userName, password) != null) {
			response.setSuccess(false);
			response.setErrMsg("Same username already exists.");
		}

		if (response.isSuccess()) {

			UserInfoPOJO userInfoPOJO = new UserInfoPOJO();

			userInfoPOJO.setUserName(userName);
			userInfoPOJO.setUserPassword(password);
			userInfoPOJO.setUserStatus(status);
			userInfoPOJO.setFirstName(firstName);
			userInfoPOJO.setLastName(lastName);
			userInfoPOJO.setAddress(address);
			userInfoPOJO.setPhone(phone);
			userInfoPOJO.setGroupID(groupID);

			boolean success = UserService.setUser(userInfoPOJO);
			UserInfo userInfo = UserService.getUser(userName, password);

			if (success && userInfo != null) {
				HttpSession session = req.getSession();

				session.setAttribute("userID", userInfo.getUserId());
				session.setAttribute("firstName", userInfo.getFirstName());
				session.setAttribute("lastName", userInfo.getLastName());

				response.setSuccess(true);
				response.setRedirectUrl(req.getContextPath() + "/page/quiz");
			} else {
				response.setSuccess(false);
			}
		}

		PrintWriter writer = res.getWriter();
		Gson gson = new Gson();
		String respJson = gson.toJson(response);
		writer.append(respJson);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("action", "register");
		req.getServletContext().getRequestDispatcher("/jsps/loginPage.jsp").forward(req, res);
	}
}
