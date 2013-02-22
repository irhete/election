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
		RootPanel.get("content").add(content);
	}

}
