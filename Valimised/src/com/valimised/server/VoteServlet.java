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

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.rdbms.AppEngineDriver;
import com.valimised.client.Valimised;

@SuppressWarnings("serial")
public class VoteServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection c = null;
		try {
			String statement;
			String decreaseVotes;
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
			String query = "SELECT votedFor FROM voter WHERE id = ?";
			PreparedStatement qry = c.prepareStatement(query);
			qry.setString(1, id);
			ResultSet success = qry.executeQuery();
			// test if the person has voted or not
			if (!success.next()) { // has not voted
				statement = "INSERT INTO voter (id, firstName, lastName, votedFor) VALUES (?,?,?,?)";
			} else { // has voted
				hasVoted = true;
				statement = "UPDATE voter SET votedFor = ? WHERE id = ?";
				decreaseVotes = "UPDATE candidate SET votes = votes - 1 WHERE id = ?";
				PreparedStatement decreaseVote = c.prepareStatement(decreaseVotes);
				decreaseVote.setString(1, success.getString("votedFor"));
				decreaseVote.executeUpdate();
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
			String increaseVotes = "UPDATE candidate SET votes = votes + 1 WHERE id = ?";
			PreparedStatement increaseVote = c.prepareStatement(increaseVotes);
			increaseVote.setString(1, votedFor);
			increaseVote.executeUpdate();
			
			System.out.println(Valimised.channelKeys.size());
			for (String channelKey : Valimised.channelKeys) {
				
				if (channelKey != null) {
				ChannelService channelService = ChannelServiceFactory.getChannelService();
				
			      channelService.sendMessage(new ChannelMessage(channelKey, "tere"));
				}
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