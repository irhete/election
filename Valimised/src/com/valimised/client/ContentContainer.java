package com.valimised.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContentContainer {

	private static final ContentContainer INSTANCE = new ContentContainer();

	public static ContentContainer getInstance() {
		return INSTANCE;
	}

	public void setContent(Widget content) {
		RootPanel.get("content").clear();
		RootPanel.get("content").getElement().setInnerHTML("");
		RootPanel.get("content").add(content);
	}

	public void setMenu(Widget content) {
		RootPanel.get("leftMenu").clear();
		RootPanel.get("leftMenu").add(content);
	}

	public void setHeader(Widget content) {
		RootPanel.get("header").clear();
		RootPanel.get("header").add(content);
	}

	public void setElement(String elementName, Widget widget) {
		RootPanel.get(elementName).clear();
		RootPanel.get(elementName).add(widget);
	}

}
