package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class TextVersion extends Composite{
	
	private FlexTable statTable;
	
	public TextVersion() {
		
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		statTable = new FlexTable();
		statTable.setStyleName("statTable");
		createTableHeader();

		addRow("Tartu", "Reformierakond", "32%");
		addRow("", "Keskerakond", "25%");
		addRow("", "SDE", "14%");
		addRow("", "IRL", "13%");
		addRow("", "...", "...");
		addRow("Pärnu", "SDE", "34%");
		addRow("", "Üksikkandidaadid", "25%%");
		addRow("", "...", "...");

		mainPanel.add(statTable);
	}

	private void createTableHeader() {
		statTable.setText(0, 0, "Piirkond");
		statTable.setText(0, 1, "Erakond");
		statTable.setText(0, 2, "Tulemus");
	}

	public void addRow(String area, String party, String result) {
		int lastRow = statTable.getRowCount();
		statTable.insertRow(lastRow);
		statTable.setText(lastRow, 0, area);
		statTable.setText(lastRow, 1, party);
		statTable.setText(lastRow, 2, result);
		if (area != "") {

		}
	}	


}
