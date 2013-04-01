package com.valimised.server;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
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
	   
	  Connection c = null;
	    try {
	      DriverManager.registerDriver(new AppEngineDriver());
	      String id = req.getParameter("id");
	      c = DriverManager.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
	      String statement ="SELECT firstName, lastName FROM candidate WHERE id=?";
	      PreparedStatement stmt = c.prepareStatement(statement);
	      stmt.setString(1, id);
	      ResultSet success = stmt.executeQuery();
	      while (success.next()) {
	        System.out.println(success.getString(1) + " " + success.getString(2));
	      }
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
