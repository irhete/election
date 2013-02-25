package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class ResultArea extends Composite {
	
	private FlexTable resultsTextTable;
	
	public ResultArea(String area){
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		resultsTextTable = new FlexTable();
		resultsTextTable.setStyleName("candidatesTable");
		createTableHeader();
		resultsTextTable.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cell cell = resultsTextTable.getCellForEvent(event);
				int receiverRowIndex = cell.getRowIndex();
				String nr = resultsTextTable.getText(
						receiverRowIndex, 2);
				ContentContainer.getInstance().setContent(
						new Candidate(nr));
			}
		});
		
		addRow("Jaan Nuude", "Reformierakond","111", "32%");
		addRow("Ants Kantoss", "SDE","112", "25%");
		addRow("Andres Tamm", "Keskerakond","113", "14%");
		addRow("Kadri Simson", "IRL","114", "13%");
		addRow("Pärnu Linnapea", "SDE","115", "35%");

		InlineHTML nameLabel = new InlineHTML("<b>" + area +"</b>");
		mainPanel.add(nameLabel);
		mainPanel.add(resultsTextTable);
	}
	
	private void createTableHeader() {
		resultsTextTable.setText(0, 0, "Isik");
		resultsTextTable.setText(0, 1, "Erakond");
		resultsTextTable.setText(0, 2, "Number");
		resultsTextTable.setText(0, 3, "Tulem");
	}
	
	public void addRow(String name, String party, String nr, String result) {
		int lastRow = resultsTextTable.getRowCount();
		resultsTextTable.insertRow(lastRow);
		resultsTextTable.setText(lastRow, 0, name);
		resultsTextTable.setText(lastRow, 1, party);
		resultsTextTable.setText(lastRow, 2, nr);
		resultsTextTable.setText(lastRow, 3, result);
	}

}
