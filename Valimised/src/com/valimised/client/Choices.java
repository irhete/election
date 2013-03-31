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
	
	public Choices() {
		VerticalPanel verticalPane = new VerticalPanel();
		initWidget(verticalPane);
		verticalPane.addStyleName("valikud");

		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.addStyleName("chosenCandidatePanel");

		html = new InlineHTML(
				"<p>Te ei ole veel hääletanud.</p>");
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
		
		Button toCandidates = new Button("Vaata kandidaate");
		toCandidates.addStyleName("toCandidatesButton");
		toCandidates.getElement().setAttribute("onclick",
				"createCandidatesTable(0, \"\")");
		
		Button cancel = new Button("Tühista");
		cancel.addStyleName("cancelButton");
		cancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				changeVoted("");
			}
		});
		
		Button addCandidate = new Button("Lisa end kandidaadiks");
		addCandidate.addStyleName("addButton");
//		addCandidate.getElement().setAttribute("onclick", "test()");
		addCandidate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new ApplicationForm());
			}
		});

		mainPanel.add(html);
		mainPanel.add(toCandidates);
		mainPanel.add(cancel);
		cancel.setVisible(false);

		verticalPane.add(mainPanel);
		verticalPane.add(addCandidate);
	}

	public void changeVoted(String name) {
		if (name == "") {
			html.setHTML("<p>Te ei ole veel hääletanud.</p>");
		}
		else {
			html.setHTML("<p>Olete hääletanud kandidaadi <a href='#'>" + name + "</a> poolt.</p>");
		}
	}
}
