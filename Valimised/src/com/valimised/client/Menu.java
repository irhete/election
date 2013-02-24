package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu extends Composite {
	
	public Menu(){
	VerticalPanel menuPanel = new VerticalPanel();
	initWidget(menuPanel);
	
	Button candidates = new Button("Kandidaadid");
	candidates.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			ContentContainer.getInstance().setContent(new Candidates());
		}
	});
	Button results = new Button("Tulemused");
	/*results.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			ContentContainer.getInstance().setContent(new Tulemused());
		}
	});*/
	Button elections = new Button("Valimistest");
	/*elections.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			ContentContainer.getInstance().setContent(new Valimistest());
		}
	});*/
	
	menuPanel.add(candidates);
	menuPanel.add(results);
	menuPanel.add(elections);
	
	}
}

