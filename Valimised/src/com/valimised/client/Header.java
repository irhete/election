package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends Composite {

	private HorizontalPanel searchPanel;
	private HorizontalPanel loginPanel;

	public Header() {

		HorizontalPanel mainPanel = new HorizontalPanel();
		initWidget(mainPanel);
		mainPanel.setStyleName("headerMainPanel");

		searchPanel = searchPanel();
		loginPanel = loginPanel();
		ContentContainer.getInstance().setElement("searchPanel", searchPanel);
		ContentContainer.getInstance().setElement("loginPanel", loginPanel);

	}

	private HorizontalPanel searchPanel() {
		HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setSpacing(5);

		ListBox areas = getListBox(true);
		TextBox candidate = new TextBox();
		candidate.setStyleName("candidateSearchBox");

		Button search = new Button("Otsi");
		search.addStyleName("searchButton");
		search.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Candidates());
			}
		});

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
				ContentContainer.getInstance().setContent(new Choices());
			}
		});
		headerPanel2.add(login);
		headerPanel2.add(idkaart);

		return headerPanel2;
	}

	private ListBox getListBox(boolean b) {
		ListBox widget = new ListBox();
		widget.addItem("Tallinn");
		widget.addItem("Tartu");
		widget.addItem("Pärnu");
		widget.addItem("Narva");
		widget.addItem("Viljandi");
		return widget;
	}
}
