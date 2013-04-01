package com.valimised.server;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;

public class ApplicationFormServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112571060470183501L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String firstName = request.getParameter("firstNameBox");
			String lastName = request.getParameter("lastNameBox");
			String area = request.getParameter("areaBox");
			String party = request.getParameter("partyBox");
			String statement = "insert into candidate (firstName, lastName, area, party, description, votes) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = c.prepareStatement(statement);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, area);
			stmt.setString(4, party);
			stmt.setString(5, "");
			stmt.setString(4, "0");
			stmt.executeUpdate();

			out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException ignore) {
				System.out.println(ignore.getMessage());
			}
		}
	}
}
