package com.valimised.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineHTML;

public class Valikud extends Composite {

	public Valikud() {
		FlowPanel flowPanel = new FlowPanel();
		initWidget(flowPanel);
		flowPanel.addStyleName("valikud");

		HorizontalPanel mainPanel = new HorizontalPanel();

		InlineHTML html = new InlineHTML(
				"<p>Olete hääletanud kandidaadi <a>Andres Tamm</a> poolt.</p>");
		Button cancel = new Button("Tühista");
		cancel.addStyleName("cancelButton");
		Button addCandidate = new Button("Lisa end kandidaadiks");
		addCandidate.addStyleName("addButton");
		addCandidate.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance()
						.setContent(new ApplicationForm());

			}
		});

		mainPanel.setSpacing(20);
		mainPanel.setHeight("30px");

		mainPanel.add(html);
		mainPanel.add(cancel);

		flowPanel.add(mainPanel);
		flowPanel.add(addCandidate);
	}

}
