package com.valimised.server;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.gson.Gson;
import com.valimised.shared.Candidate;
import com.valimised.shared.Data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CandidateServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
public void doGet(HttpServletRequest req, HttpServletResponse resp)
	  throws IOException {
		PrintWriter out = resp.getWriter();
	  Connection c = null;
	    try {
	      DriverManager.registerDriver(new AppEngineDriver());
	      String id = req.getParameter("id");
	      c = DriverManager.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
	      String statement ="SELECT firstName, lastName, party, area, description, picture FROM candidate WHERE id=?";
	      PreparedStatement stmt = c.prepareStatement(statement);
	      stmt.setString(1, id);
	      ResultSet success = stmt.executeQuery();
	      success.next();
	      Candidate candidate = new Candidate(success.getString("lastName"), success.getString("firstName"), Integer.parseInt(id), Data.areas[success.getInt("area")-1], Data.parties[success.getInt("party")-1], success.getString("description"), success.getString("picture"));
	      String gson = new Gson().toJson(candidate);
	      resp.setContentType("application/json");
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