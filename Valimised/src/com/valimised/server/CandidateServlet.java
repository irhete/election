package com.valimised.server;

import com.google.appengine.api.rdbms.AppEngineDriver;

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
	  System.out.println("request saadetud");
	    try {
	      DriverManager.registerDriver(new AppEngineDriver());
	      System.out.println("driver loodud");
	      String id = req.getParameter("id");
	      System.out.println(id);
	      c = DriverManager.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
	      System.out.println("connection loodud");
	      String statement ="SELECT firstName, lastName FROM candidate WHERE id=?";
	      PreparedStatement stmt = c.prepareStatement(statement);
	      stmt.setString(1, id);
	      ResultSet success = stmt.executeQuery();
	      System.out.println("päring tehtud");
	      while (success.next()) {
	        System.out.println(success.getString(0) + " " + success.getString(1));
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
