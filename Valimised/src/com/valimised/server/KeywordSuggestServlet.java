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

public class KeywordSuggestServlet extends HttpServlet {
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
		      String key = req.getParameter("term");
		      c = DriverManager.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
		      String statement ="SELECT firstName, lastName FROM candidate WHERE lastName LIKE ?";
		      PreparedStatement stmt = c.prepareStatement(statement);
		      stmt.setString(1, key + "%");
		      ResultSet success = stmt.executeQuery();
		      List<String> candidates = new ArrayList<String>();
		      while (success.next()) {
		    	String candidateInfo = success.getString("lastName") + ", " + success.getString("firstName");
		    	candidates.add(candidateInfo);
		      }
		      String gson = new Gson().toJson(candidates);
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
