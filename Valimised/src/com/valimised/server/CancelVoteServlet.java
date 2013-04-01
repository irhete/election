package com.valimised.server;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;

@SuppressWarnings("serial")
public class CancelVoteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			String id = request.getParameter("id");

			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String statement = "DELETE FROM voter WHERE id=?";
			PreparedStatement stmt = c.prepareStatement(statement);
			stmt.setString(1, id);
			stmt.executeUpdate();

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