package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.valimised.shared.Data;

public class Header extends Composite {

	private HorizontalPanel searchPanel;
	private HorizontalPanel loginPanel;
	private VerticalPanel loginPanel2;

	public Header() {

		HorizontalPanel mainPanel = new HorizontalPanel();
		initWidget(mainPanel);
		mainPanel.setStyleName("headerMainPanel");

		searchPanel = searchPanel();
		loginPanel = loginPanel();
		loginPanel2 = loginPanel2("Mari Maripuu");
		ContentContainer.getInstance().setElement("searchPanel", searchPanel);
		ContentContainer.getInstance().setElement("loginPanel", loginPanel);

	}
	
	private void loggedIn(){
		ContentContainer.getInstance().setElement("loginPanel", loginPanel2);
	}
	private void loggedOut(){
		ContentContainer.getInstance().setElement("loginPanel", loginPanel);
	}

	private HorizontalPanel searchPanel() {
		HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setSpacing(5);

		ListBox areas = getListBox(true);
		areas.getElement().setId("areaList");

		TextBox candidate = new TextBox();
		candidate.setStyleName("candidateSearchBox");
		candidate.getElement().setId("candidateSearchBox");
		candidate.getElement().setAttribute("onclick", "keywordSuggest()");

		Button search = new Button("Otsi");
		search.addStyleName("searchButton");
		search.getElement().setId("searchButton");
//		search.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				ContentContainer.getInstance().setContent(new Candidates());
//			}
//		});
		search.getElement().setAttribute("onclick", "search()");
		headerPanel.add(areas);
		headerPanel.add(candidate);
		headerPanel.add(search);

		return headerPanel;
	}

	private HorizontalPanel loginPanel() {
		HorizontalPanel headerPanel2 = new HorizontalPanel();
		headerPanel2.setSpacing(5);

		Label login = new Label("Logi sisse:");
		login.addStyleName("login");
		Button idkaart = new Button();
		idkaart.setStyleName("idkaart");
		idkaart.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loggedIn();
			}
		});
		headerPanel2.add(login);
		headerPanel2.add(idkaart);

		return headerPanel2;
	}
	
	private VerticalPanel loginPanel2(String name) {
		VerticalPanel headerPanel2 = new VerticalPanel();
		headerPanel2.setSpacing(5);

		Label loginname = new Label(name);
		loginname.addStyleName("loginname");
		Button choices = new Button("Valikud");
		choices.setStyleName("choices");
		choices.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Choices());
			}
		});
		Button logout = new Button("Logi välja");
		logout.setStyleName("logout");
		logout.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loggedOut();
			}
		});
		headerPanel2.add(loginname);
		HorizontalPanel horPanel = new HorizontalPanel();
		horPanel.setStyleName("login2");
		horPanel.add(choices);
		horPanel.add(logout);
		headerPanel2.add(horPanel);

		return headerPanel2;
	}

	private ListBox getListBox(boolean b) {
		ListBox widget = new ListBox();
		for (String area : Data.areas) {
		widget.addItem(area);
		}
		return widget;
	}
}
