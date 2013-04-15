package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
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
				History.newItem("candidates");				
			}
		});
		//candidates.getElement().setAttribute("onclick", "createCandidatesTable(0, \"\")");


		Button results = new Button("Tulemused");
		results.addStyleName("menuButton");
		results.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("results");				
			}
		});
		//results.getElement().setAttribute("onclick", "createResultsTable()");
		
		Button elections = new Button("Valimistest");
		elections.addStyleName("menuButton");
		elections.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("about");				
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
	
	public static void candidatePage(int i) {
		ContentContainer.getInstance().setCandidatePage(i);
	}
	
	public static native void exportStaticMethodPage() /*-{
	$wnd.candidatePage = $entry(@com.valimised.client.Menu::candidatePage(I));
}-*/;
}
