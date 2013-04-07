package com.valimised.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class About extends Composite{
	
	public About() {
		VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);
		mainPanel.addStyleName("valimistest");
		
		HTML html = new HTML(
				"<h2>Valimised</h2>" +
				"<p>Riigikogu valimised toimuvad 10.juunil. " +
				"Valimistel võivad osaleda kõik vähemalt 18-aastased hääleõiguslikud Eesti kodanikud.</p>" +
				"<h2>E-hääletamine</h2>" +
				"<p>Elektrooniliselt on võimalik häält anda 28.maist kuni 6.juunini. " +
				"Selle ajavahemiku jooksul on võimalik oma juba antud häält tühistada või vahetada kandidaati," +
				"kellele hääl antakse.</p>" +
				"<p>Hääletades pärast 6.juunit valimisjaoskonnas, tühistatakse varem elektrooniliselt antud hääl." +
				"Hääled loetakse kokku 10.juuni õhtul kell 19.00." +
				"Tulemusi on võimalik jälgida reaalajas lehel <a onclick='createResultsTable()'>Tulemused</a>.</p>");

		mainPanel.add(html);
		
	}
}
