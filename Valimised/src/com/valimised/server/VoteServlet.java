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
public class VoteServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection c = null;
		try {
			String statement;
			boolean hasVoted = false;
			DriverManager.registerDriver(new AppEngineDriver());
			/*
			 * Mockup data
			 */
			String id = "36554657645";
			String firstName = "Mari";
			String lastName = "Maripuu";
			String votedFor = request.getParameter("votedId");
			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String query = "SELECT id FROM voter WHERE id = ?";
			PreparedStatement qry = c.prepareStatement(query);
			qry.setString(1, id);
			ResultSet success = qry.executeQuery();
			// test if the person has voted or not
			if (!success.next()) { // has not voted
				statement = "INSERT INTO voter (id, firstName, lastName, votedFor) VALUES (?,?,?,?)";
			} else { // has voted
				hasVoted = true;
				statement = "UPDATE voter SET votedFor = ? WHERE id = ?";
			}
			PreparedStatement ps = c.prepareStatement(statement);
			if (!hasVoted) {
				ps.setString(1, id);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setString(4, votedFor);
			} else {
				ps.setString(1, votedFor);
				ps.setString(2, id);
			}
			ps.executeUpdate(); // inserts/updates database values

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