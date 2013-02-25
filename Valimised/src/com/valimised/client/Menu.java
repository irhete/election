package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu extends Composite {

	public Menu() {
		VerticalPanel menuPanel = new VerticalPanel();
		initWidget(menuPanel);
		menuPanel.addStyleName("menuPanel");

		Button candidates = new Button("Kandidaadid");
		candidates.addStyleName("menuButton");
		candidates.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Candidates());
			}
		});
		Button results = new Button("Tulemused");
		results.addStyleName("menuButton");
		results.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new TextVersion());
			}
		});
		Button elections = new Button("Valimistest");
		elections.addStyleName("menuButton");
		elections.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Valimistest());
			}
		});

		ContentContainer.getInstance().setElement("candidatesButton",
				candidates);
		ContentContainer.getInstance().setElement("resultsButton", results);
		ContentContainer.getInstance().setElement("electionsButton", elections);
		// menuPanel.add(candidates);
		// menuPanel.add(results);
		// menuPanel.add(elections);
	}
}