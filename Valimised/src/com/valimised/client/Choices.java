package com.valimised.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Choices extends Composite {

	private InlineHTML html;
	private InlineHTML html2;
	Button cancelVote;
	Button toCandidates;
	Button addCandidate;
	Button cancelCandidate;
	
	public Choices() {
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.addStyleName("valikud");

		HorizontalPanel votePanel = new HorizontalPanel();
		votePanel.addStyleName("chosenCandidatePanel");
		
		HorizontalPanel candidatePanel = new HorizontalPanel();
		candidatePanel.addStyleName("chosenCandidatePanel");

		html = new InlineHTML("");
		html.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Element element = event.getNativeEvent().getEventTarget()
						.cast();
				if (element.getTagName().equals("A")) {
					ContentContainer.getInstance().setContent(
							new Candidate("111"));
				}
			}
		}, ClickEvent.getType());
		
		toCandidates = new Button("Vaata kandidaate");
		toCandidates.addStyleName("toCandidatesButton");
		toCandidates.getElement().setAttribute("onclick",
				"createCandidatesTable(0, \"\")");
		
		cancelVote = new Button("Tühista");
		cancelVote.addStyleName("cancelVoteButton");
		cancelVote.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setVoted(false);
				changeVoted();
			}
		});
		
		html2 = new InlineHTML("");
		
		addCandidate = new Button("Lisa end kandidaadiks");
		addCandidate.addStyleName("addCandidateButton");
		addCandidate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new ApplicationForm());
			}
		});
		
		cancelCandidate = new Button("Tühista kandideerimine");
		cancelCandidate.addStyleName("cancelCandidateButton");
		cancelCandidate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setCandidate(false);
				changeCandidate();
			}
		});

		votePanel.add(html);
		votePanel.add(toCandidates);
		votePanel.add(cancelVote);
		
		candidatePanel.add(html2);
		candidatePanel.add(addCandidate);
		candidatePanel.add(cancelCandidate);

		verticalPanel.add(votePanel);
		verticalPanel.add(candidatePanel);
		
		changeVoted();
		changeCandidate();
	}

	public void changeVoted() {
		if (!ContentContainer.getInstance().getVoted()) {
			html.setHTML("<p>Te ei ole veel hääletanud.</p>");
			if (cancelVote.isVisible()) {
				cancelVote.setVisible(false);
			}
			if (!toCandidates.isVisible()){
				toCandidates.setVisible(true);
			}
		}
		else {
			html.setHTML("<p>Olete hääletanud kandidaadi <a href='#'>" + "Andres Tamm" + "</a> poolt.</p>");
			if (!cancelVote.isVisible()) {
				cancelVote.setVisible(true);
			}
			if (toCandidates.isVisible()){
				toCandidates.setVisible(false);
			}
		}
	}
	
	public void changeCandidate() {
		if (!ContentContainer.getInstance().getCandidate()) {
			html2.setHTML("");
			if (cancelCandidate.isVisible()) {
				cancelCandidate.setVisible(false);
			}
			if (!addCandidate.isVisible()){
				addCandidate.setVisible(true);
			}
		}
		else {
			html2.setHTML("<p>Olete ennast lisanud kandidaadiks piirkonnas " + "Tartu linn" + 
					". Kandideerimist saab tühistada 1.juunini (k.a).</p>");
			if (!cancelCandidate.isVisible()) {
				cancelCandidate.setVisible(true);
			}
			if (addCandidate.isVisible()){
				addCandidate.setVisible(false);
			}
		}
	}
}
