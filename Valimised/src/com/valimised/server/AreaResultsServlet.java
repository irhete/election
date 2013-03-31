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

@SuppressWarnings("serial")
public class AreaResultsServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());

			// returns particular area results
			// currently only returns Tallinn3 area.
			// String area = request.getParameter("");
			String query = "select firstName, lastName, id, partyName, votes from candidate"
					+ " join election.party on party = party.PartyID where area = 3 order by lastName, firstName";

			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");

			PreparedStatement statement = c.prepareStatement(query);
			ResultSet tableRows = statement.executeQuery();
			List<AreaResult> results = new ArrayList<AreaResult>();
			while (tableRows.next()) {
				AreaResult areaResult = new AreaResult(tableRows.getString("firstName")
						+ " " + tableRows.getString("lastName"),
						tableRows.getInt("id"),
						tableRows.getString("partyName"),
						tableRows.getInt("votes"));
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