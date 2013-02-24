package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends Composite {
	
	public Header(){
		HorizontalPanel headerPanel = new HorizontalPanel();
		initWidget(headerPanel);
		
		ListBox areas = getListBox(true);
		
		TextBox candidate = new TextBox();
		candidate.setText("Kandidaat");
	    
		Button search = new Button("Otsi");
		search.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Candidates());
			}
		});
		
		headerPanel.add(areas);
		headerPanel.add(candidate);
		headerPanel.add(search);

		
	}	
	
	private ListBox getListBox(boolean b)  {
		ListBox widget = new ListBox();
		widget.addItem("Tallinn");
		widget.addItem("Tartu");
		widget.addItem("Pärnu");
		widget.addItem("Narva");
		widget.addItem("Viljandi");
		return widget;
	}
}
