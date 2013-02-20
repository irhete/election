package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Kandidaadid extends Composite {

	private FlexTable candidatesTable;

	public Kandidaadid() {
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		candidatesTable = new FlexTable();
		createTableHeader();

		addRow("Mari Mets", "Üksikkandidaat", "Tallinn", "110");
		addRow("Mart Mets", "SDE", "Tartu", "111");
		addRow("Andres Tamm", "Keskerakond", "Tartu", "112");
		addRow("Peeter Kask", "Reformierakond", "Pärnu", "113");
		addRow("Ene Eha", "IRL", "Narva", "114");

		Button kandideeri = new Button("Kandideeri");
		kandideeri.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Kandideerimine());
			}
		});

		mainPanel.add(candidatesTable);
		mainPanel.add(kandideeri);
	}

	private void createTableHeader() {
		candidatesTable.setText(0, 0, "Nimi");
		candidatesTable.setText(0, 1, "Erakond");
		candidatesTable.setText(0, 2, "Piirkond");
		candidatesTable.setText(0, 3, "Number");
		candidatesTable.setText(0, 4, "");
	}

	public void addRow(String name, String party, String area, String number) {
		int lastRow = candidatesTable.getRowCount();
		candidatesTable.insertRow(lastRow);
		candidatesTable.setText(lastRow, 0, name);
		candidatesTable.setText(lastRow, 1, party);
		candidatesTable.setText(lastRow, 2, area);
		candidatesTable.setText(lastRow, 3, number);
		candidatesTable.setText(lastRow, 4, "Hääleta");
	}

}
