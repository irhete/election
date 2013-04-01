package com.valimised.server;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			String statement;
			boolean isCandidate = false;
			DriverManager.registerDriver(new AppEngineDriver());
			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String area = request.getParameter("area");
			String party = request.getParameter("party");
			String idCode = request.getParameter("idCode");
			
			String query = "SELECT idCode FROM candidate WHERE idCode = ?";
			PreparedStatement qry = c.prepareStatement(query);
			qry.setString(1, idCode);
			ResultSet success = qry.executeQuery();
			// test if the person is already candidate
			if (!success.next()) { // isn't candidate
				statement = "INSERT INTO candidate (firstName, lastName, area, party, description, votes, idCode) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(statement);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, area);
			ps.setString(4, party);
			ps.setString(5, "");
			ps.setString(6, "0");
			ps.setString(7, idCode);
			ps.executeUpdate(); // inserts/updates database values
			} 
			
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
