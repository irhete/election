package com.valimised.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Valikud extends Composite {

	public Valikud() {
		VerticalPanel verticalPane = new VerticalPanel();
		initWidget(verticalPane);
		verticalPane.addStyleName("valikud");

		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.addStyleName("chosenCandidatePanel");

		InlineHTML html = new InlineHTML(
				"<p>Olete hääletanud kandidaadi <a href='#'>Andres Tamm</a> poolt.</p>");
		html.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Element element = event.getNativeEvent().getEventTarget()
						.cast();
				if (element.getTagName().equals("A")) {
					ContentContainer.getInstance().setContent(
							new Candidate("111"));
				}

			}
		}, ClickEvent.getType());
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

		mainPanel.add(html);
		mainPanel.add(cancel);

		verticalPane.add(mainPanel);
		verticalPane.add(addCandidate);
	}

}
