package com.valimised.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Candidate extends Composite {
	public Candidate(String candidateNumber) {
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setStyleName("candidatePagePanel");

		Image candidatePicture = new Image("maesalu.jpeg");
		candidatePicture.setStyleName("candidatePicture");

		VerticalPanel credentialsPanel = new VerticalPanel();
		InlineHTML nameLabel = new InlineHTML("<b>Andres Tamm</b>, "
				+ candidateNumber);
		Label partyLabel = new Label("Keskerakond");
		Label areaLabel = new Label("Tartu");
		credentialsPanel.add(nameLabel);
		credentialsPanel.add(partyLabel);
		credentialsPanel.add(areaLabel);

		Button votingButton = new Button("Hääleta");
		votingButton.setStyleName("candidatePageVotingButton");

		topPanel.add(candidatePicture);
		topPanel.add(credentialsPanel);
		topPanel.add(votingButton);

		Label descriptionLabel = new Label(
				"MSc matemaatikas (TÜ), Tartu Linnavalitsuses aastast 2010. "
						+ "Abielus, kahe poja isa. Meeldib suusatamine. "
						+ "Andres väärtustab ausust, perekonda, haridust, koostööd, ilu, sporti ja keskkonnasäästlikkust. "
						+ "Andres seisab Tartu arengu eest.");

		mainPanel.add(topPanel);
		mainPanel.add(descriptionLabel);
	}
}
