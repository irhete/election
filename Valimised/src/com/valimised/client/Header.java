package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Header extends Composite {
	
	public Header(){
		
		FlowPanel flowPanel = new FlowPanel();
		initWidget(flowPanel);
		HorizontalPanel headerPanel = new HorizontalPanel();
//		initWidget(headerPanel);
		headerPanel.addStyleName("headerPanel1");
		headerPanel.setSpacing(5);
		
		HorizontalPanel headerPanel2 = new HorizontalPanel();
//		initWidget(headerPanel2);
		headerPanel2.addStyleName("headerPanel2");
		headerPanel2.setSpacing(5);
		
		ListBox areas = getListBox(true);
		
		TextBox candidate = new TextBox();
//		candidate.setText("Kandidaat");
	    
		Button search = new Button("Otsi");
		search.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Candidates());
			}
		});
		
		
		Label login = new Label("Logi sisse:");
		login.addStyleName("login");
		Button idkaart = new Button();
		idkaart.setStyleName("idkaart");
		
		headerPanel.add(areas);
		headerPanel.add(candidate);
		headerPanel.add(search);
		
		headerPanel2.add(login);
		headerPanel2.add(idkaart);
		
		flowPanel.add(headerPanel);
		flowPanel.add(headerPanel2);

		
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
