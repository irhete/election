package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class ResultText extends Composite {

	private FlexTable resultsTextTable;
	
	public ResultText() {
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
				String area = resultsTextTable.getText(
						receiverRowIndex, 0);
				System.out.println(area);
				if(!area.equals(null)){
				ContentContainer.getInstance().setContent(
						new ResultArea(area));
				}
			}
		});
		
		addRow("Tallinn", "Reformierakond", "32%");
		addRow(null, "SDE", "25%");
		addRow(null, "Keskerakond", "14%");
		addRow(null, "IRL", "13%");
		addRow("Pärnu", "SDE", "35%");

		mainPanel.add(resultsTextTable);
	}
	
	
	private void createTableHeader() {
		resultsTextTable.setText(0, 0, "Piirkond");
		resultsTextTable.setText(0, 1, "Erakond");
		resultsTextTable.setText(0, 2, "Tulemus");
	}

	public void addRow(String area, String party, String result) {
		int lastRow = resultsTextTable.getRowCount();
		resultsTextTable.insertRow(lastRow);
		resultsTextTable.setText(lastRow, 0, area);
		resultsTextTable.setText(lastRow, 1, party);
		resultsTextTable.setText(lastRow, 2, result);
		if(!resultsTextTable.getText(lastRow, 0).equals(null)){
			resultsTextTable.setHTML(lastRow,0,"<a>"+resultsTextTable.getText(lastRow, 0)+"</a>");
		}
		if (lastRow == 5) {
			resultsTextTable.setHTML(lastRow,0,"<a>Pärnu</a>");
		}
	}
}
