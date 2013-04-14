package com.valimised.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.gson.Gson;
import com.valimised.shared.AreaResult;
import com.valimised.shared.Data;

@SuppressWarnings("serial")
public class PartyResultsServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());

			String query = "SELECT firstName, lastName, id, party, votes, area FROM candidate WHERE party = ?";

			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String partyId = request.getParameter("partyId");
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, partyId);
			ResultSet tableRows = statement.executeQuery();
			List<AreaResult> results = new ArrayList<AreaResult>();
			while (tableRows.next()) {
				AreaResult areaResult = new AreaResult(
						tableRows.getString("firstName") + " "
								+ tableRows.getString("lastName"),
						tableRows.getInt("id"),
						Data.parties[tableRows.getInt("party") - 1],
						tableRows.getInt("votes"), Data.areas[tableRows.getInt("area")-1]);
				results.add(areaResult);
			}
			String gson = new Gson().toJson(results);
			response.setContentType("application/json");
			out.write(gson);
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
