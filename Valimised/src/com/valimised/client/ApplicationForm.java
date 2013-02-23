package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ApplicationForm extends Composite {

	public ApplicationForm() {
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		FlexTable table = new FlexTable();
		Label firstNameLabel = new Label("Eesnimi");
		TextBox firstNameBox = new TextBox();
		Label lastNameLabel = new Label("Perekonnanimi");
		TextBox lastNameBox = new TextBox();
		Label personalCodeLabel = new Label("Isikukood");
		TextBox personalCodeBox = new TextBox();

		table.setWidget(0, 0, firstNameLabel);
		table.setWidget(0, 1, firstNameBox);
		table.setWidget(1, 0, lastNameLabel);
		table.setWidget(1, 1, lastNameBox);
		table.setWidget(2, 0, personalCodeLabel);
		table.setWidget(2, 1, personalCodeBox);

		Button candidates = new Button("Kandidaadid");
		candidates.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Candidates());
			}
		});

		mainPanel.add(table);
		mainPanel.add(candidates);
	}

}
