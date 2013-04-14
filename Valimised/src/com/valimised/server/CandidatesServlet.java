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
import com.valimised.shared.Candidate;
import com.valimised.shared.Data;

public class CandidatesServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3553714513938767720L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		  throws IOException {
		PrintWriter out = resp.getWriter();
		 Connection c = null;
		    try {
		      DriverManager.registerDriver(new AppEngineDriver());
		      String key = req.getParameter("keywords");
		      String area = req.getParameter("area");
		      String[] keywords;
		      if (key == null || key.equals("")) {
		    	  keywords = new String[0];
		      } else {
		    	  keywords = key.split("[, ]");
		      }
		      String areaChoice = area.equals("0") ? "" : " area = " + area;
		      String nameChoice = "";
		      if (keywords.length >= 1) {
		    	  if (!areaChoice.equals("")) nameChoice += " AND";
		    	  for(String keyword : keywords) {
		    		  if (!keyword.equals("")) {
		    			  nameChoice += " (lastName LIKE '" + keyword + "%' OR firstName LIKE '" + keyword + "%') AND";
		    		  }
		    	  }
		    	  nameChoice = nameChoice.substring(0, nameChoice.length()-3);
		      }
		      String statement ="SELECT id, firstName, lastName, area, party FROM candidate";
		      if (!(areaChoice.equals("") && nameChoice.equals(""))) {
		    	  statement += " WHERE" + areaChoice + nameChoice;
		      }
		      c = DriverManager.getConnection("jdbc:google:rdbms://e-election-app:instance2/election"); 
		      
		      PreparedStatement stmt = c.prepareStatement(statement);
		      ResultSet success = stmt.executeQuery();
		      List<Candidate> candidates = new ArrayList<Candidate>();
		      while (success.next()) {
		    	  Candidate candidate = new Candidate(success.getString("lastName"), success.getString("firstName"), success.getInt("id"), Data.areas[success.getInt("area")-1], Data.parties[success.getInt("party") - 1]);
		    	  candidates.add(candidate);
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
