package com.valimised.server;

import java.io.*;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.valimised.shared.LoggedInUser;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private int userID;
	private String userRegion;
	private int votedFor;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		if (request.getSession().getAttribute("userID") != null) {
			userID = (int) (request.getSession().getAttribute("userID"));
			userRegion = (String) (request.getSession()
					.getAttribute("userRegion"));
			votedFor = (int) request.getSession().getAttribute("votedFor");

			return;
		}
		String accessToken = request.getHeader("accessToken");
		try {
			String json = getUserInfo(accessToken);
			LoggedInUser user = new Gson().fromJson(json, LoggedInUser.class);
			createSession(request.getSession());
		} finally {
		}
	}

	private void createSession(HttpSession session) {
		session.setAttribute("userID", userID);
		session.setAttribute("userRegion", userRegion);
		session.setAttribute("votedFor", votedFor);
	}

	private String getUserInfo(String accessToken) throws IOException {
		URL fp = new URL(
				"https://graph.facebook.com/me?method=get&access_token="
						+ accessToken + "&pretty=0&format=json");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				fp.openStream()));
		StringWriter stringWriter = new StringWriter();
		BufferedWriter out = new BufferedWriter(stringWriter);

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			out.write(inputLine);
		}
		in.close();
		out.close();
		stringWriter.flush();
		return stringWriter.toString();
	}

}