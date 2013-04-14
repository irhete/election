package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.valimised.shared.Data;

public class ApplicationForm extends Composite {
	FlexTable table;
	Label firstNameLabel;
	TextBox firstNameBox;
	Label lastNameLabel;
	TextBox lastNameBox;
	Label personalCodeLabel;
	TextBox personalCodeBox;
	Label partyLabel;
	ListBox partyBox;
	Label areaLabel;
	ListBox areaBox;
	Label addressLabel;
	TextArea addressArea;
	Label phoneLabel;
	TextBox phoneBox;
	Button nextButton;
	Label badPartyInput;
	Label badAreaInput;
	Label invalidPhoneNr;

	public ApplicationForm() {
		exportGWTMethod();
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		table = new FlexTable();
		table.getFlexCellFormatter().setColSpan(7, 0, 2);

		createWidgets();

		setStyleNames();

		addLabelsToTable();
		addWidgetsToTable();
		addBadInputLabels();

		partyBox.addItem("Vali kuuluvus");
		for (String party : Data.parties) {
			partyBox.addItem(party);
		}

		areaBox.addItem("Vali linn");
		for (String area : Data.areas) {
			areaBox.addItem(area);
		}

		firstNameBox.setText("Mari");
		lastNameBox.setText("Maripuu");
		personalCodeBox.setText("44910220236");

		setDisabled();

		mainPanel.add(table);
	}

	private void setDisabled() {
		firstNameBox.setEnabled(false);
		lastNameBox.setEnabled(false);
		personalCodeBox.setEnabled(false);
	}

	private void addLabelsToTable() {
		table.setWidget(0, 0, firstNameLabel);
		table.setWidget(1, 0, lastNameLabel);
		table.setWidget(2, 0, personalCodeLabel);
		table.setWidget(3, 0, partyLabel);
		table.setWidget(4, 0, areaLabel);
		table.setWidget(5, 0, addressLabel);
		table.setWidget(6, 0, phoneLabel);
	}

	private void addWidgetsToTable() {
		table.setWidget(0, 1, firstNameBox);
		table.setWidget(1, 1, lastNameBox);
		table.setWidget(2, 1, personalCodeBox);
		table.setWidget(3, 1, partyBox);
		table.setWidget(4, 1, areaBox);
		table.setWidget(5, 1, addressArea);
		table.setWidget(6, 1, phoneBox);
		table.setWidget(7, 0, nextButton);
	}

	private void addBadInputLabels() {
		badAreaInput = new Label("Pead siit ühe valima!");
		badPartyInput = new Label("Pead siit ühe valima!");
		invalidPhoneNr = new Label("Sisestatud number on vigane!");
		badAreaInput.getElement().setId("areaFieldRequired");
		badPartyInput.getElement().setId("partyFieldRequired");
		invalidPhoneNr.getElement().setId("invalidPhoneNr");
		table.setWidget(3, 2, badPartyInput);
		table.setWidget(4, 2, badAreaInput);
		table.setWidget(6, 2, invalidPhoneNr);
	}

	private void setStyleNames() {
		firstNameBox.setStyleName("applicationForm");
		lastNameBox.setStyleName("applicationForm");
		personalCodeBox.setStyleName("applicationForm");
		partyBox.setStyleName("applicationForm");
		areaBox.setStyleName("applicationForm");
		addressArea.setStyleName("applicationForm");
		phoneBox.setStyleName("applicationForm");
		nextButton.setStyleName("applicationFormRightButton");
	}

	private void createWidgets() {
		firstNameLabel = new Label("Eesnimi");
		firstNameBox = new TextBox();
		firstNameBox.getElement().setId("firstNameBox");
		lastNameLabel = new Label("Perekonnanimi");
		lastNameBox = new TextBox();
		lastNameBox.getElement().setId("lastNameBox");
		personalCodeLabel = new Label("Isikukood");
		personalCodeBox = new TextBox();
		partyLabel = new Label("Erakondlik kuuluvus");
		partyBox = new ListBox();
		partyBox.getElement().setId("partyBox");
		areaLabel = new Label("Piirkond");
		areaBox = new ListBox();
		areaBox.getElement().setId("areaBox");
		addressLabel = new Label("Aadress");
		addressArea = new TextArea();
		addressArea.getElement().setId("addressArea");
		phoneLabel = new Label("Telefon");
		phoneBox = new TextBox();
		phoneBox.getElement().setId("phoneBox");
		nextButton = new Button("Edasi");
		nextButton.addStyleName("nextButton");
		nextButton.getElement().setId("nextButton");
		nextButton.getElement().setAttribute("onclick", "validateForm()");
		// nextButton.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// setToLabels();
		// }
		// });
	}

	public void setToLabels() {
		table.setWidget(0, 1, new Label(firstNameBox.getText()));
		table.setWidget(1, 1, new Label(lastNameBox.getText()));
		table.setWidget(2, 1, new Label(personalCodeBox.getText()));
		table.setWidget(3, 1,
				new Label(partyBox.getItemText(partyBox.getSelectedIndex())));
		table.setWidget(4, 1,
				new Label(areaBox.getItemText(areaBox.getSelectedIndex())));
		table.setWidget(5, 1, new Label(addressArea.getText()));
		table.setWidget(6, 1, new Label(phoneBox.getText()));

		CheckBox confirmBox = new CheckBox(
				"Kinnitan, et soovin kandideerida kohalikel valimistel");
		table.setWidget(7, 0, confirmBox);

		Button previousButton = new Button("Tagasi");
		previousButton.addStyleName("previousButton");
		previousButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addWidgetsToTable();
				table.removeRow(8);
			}
		});
		Button signButton = new Button("Allkirjastama");
		signButton.setStyleName("applicationFormRightButton");
		String calling = "createCandidate(\"" + firstNameBox.getText() + "\", \""
				+ lastNameBox.getText() + "\", " + areaBox.getSelectedIndex()
				+ ", " + partyBox.getSelectedIndex() + ", \"" + personalCodeBox.getText() + "\")";
		signButton.getElement().setAttribute("onclick", calling);

		// signButton.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// ContentContainer.getInstance().setCandidate(true);
		// ContentContainer.getInstance().setContent(new Choices());
		// }
		// });
		table.setWidget(8, 0, previousButton);
		table.setWidget(8, 1, signButton);
	}

	public native void exportGWTMethod() /*-{
		var that = this;
		$wnd.setToLabels = $entry(function() {
			return that.@com.valimised.client.ApplicationForm::setToLabels()()
		});
	}-*/;

}
