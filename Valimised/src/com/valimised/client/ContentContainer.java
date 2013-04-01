package com.valimised.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContentContainer {

	private static final ContentContainer INSTANCE = new ContentContainer();
	
	private boolean isChoices;
	private boolean hasVoted;
	private boolean isCandidate;
	
	public void setChoices(boolean b) {
		isChoices = b;
	}	
	public boolean getChoices() {
		return isChoices;
	}
	
	public void setVoted(boolean b) {
		hasVoted = b;
	}	
	public boolean getVoted() {
		return hasVoted;
	}
	
	public void setCandidate(boolean b) {
		isCandidate = b;
	}	
	public boolean getCandidate() {
		return isCandidate;
	}

	public static ContentContainer getInstance() {
		return INSTANCE;
	}

	public void setContent(Widget content) {
		setChoices(false);
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
