package com.valimised.server;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class CancelCandidateServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
			String idCode = request.getParameter("idCode");

			c = DriverManager
					.getConnection("jdbc:google:rdbms://e-election-app:instance2/election");
			String statement = "DELETE FROM candidate WHERE idCode=?";
			PreparedStatement stmt = c.prepareStatement(statement);
			stmt.setString(1, idCode);
			stmt.executeUpdate();

			for (String channelKey : Valimised.areaOrPartyResultsChannelKeys) {
				
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