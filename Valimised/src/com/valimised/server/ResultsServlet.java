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
import com.valimised.shared.Result;

@SuppressWarnings("serial")
public class ResultsServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());

			// returns overall results by areas by party.
			String query = "select areaName, partyName, sum(votes) as result from candidate"
					+ " join election.area on area.areaID = candidate.area join"
					+ " election.party on candidate.party = party.partyID"
					+ " group by partyName, areaName order by area, partyID";

			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");

			PreparedStatement statement = c.prepareStatement(query);
			ResultSet tableRow = statement.executeQuery();
			List<Result> results = new ArrayList<Result>();
			while (tableRow.next()) {
				Result result = new Result(tableRow.getString("areaName"),
						tableRow.getString("partyName"),
						tableRow.getInt("result"));
				results.add(result);
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