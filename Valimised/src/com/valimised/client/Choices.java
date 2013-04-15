package com.valimised.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.valimised.client.Candidate.MyFactory;
import com.valimised.shared.Candidate2;

public class Choices extends Composite {

	private InlineHTML html;
	private InlineHTML html2;
	Button cancelVote;
	Button toCandidates;
	Button addCandidate;
	Button cancelCandidate;
	private Candidate2 candidate;

	public Choices() {
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.addStyleName("valikud");

		HorizontalPanel votePanel = new HorizontalPanel();
		votePanel.addStyleName("chosenCandidatePanel");

		HorizontalPanel candidatePanel = new HorizontalPanel();
		candidatePanel.addStyleName("chosenCandidatePanel");

		html = new InlineHTML("");
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

		toCandidates = new Button("Vaata kandidaate");
		toCandidates.addStyleName("toCandidatesButton");
		toCandidates.getElement().setAttribute("onclick",
				"createCandidatesTable(0, \"\")");

		cancelVote = new Button("Tühista");
		cancelVote.addStyleName("cancelVoteButton");
		cancelVote.getElement().setAttribute("onclick", "cancelVote(36554657645)");

		html2 = new InlineHTML("");

		addCandidate = new Button("Lisa end kandidaadiks");
		addCandidate.addStyleName("addCandidateButton");
		addCandidate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance()
						.setContent(new ApplicationForm());
			}
		});

		cancelCandidate = new Button("Tühista kandideerimine");
		cancelCandidate.addStyleName("cancelCandidateButton");
		cancelCandidate.getElement().setAttribute("onclick", "cancelCandidate(44910220236)");

		votePanel.add(html);
		votePanel.add(toCandidates);
		votePanel.add(cancelVote);

		candidatePanel.add(html2);
		candidatePanel.add(addCandidate);
		candidatePanel.add(cancelCandidate);

		verticalPanel.add(votePanel);
		verticalPanel.add(candidatePanel);

		changeVoted();
		changeCandidate();
	}

	public void changeVoted() {
		if (!ContentContainer.getInstance().getVoted()) {
			html.setHTML("<p>Te ei ole veel hääletanud.</p>");
			if (cancelVote.isVisible()) {
				cancelVote.setVisible(false);
			}
			if (!toCandidates.isVisible()) {
				toCandidates.setVisible(true);
			}
		} else {
			requestCandidate(ContentContainer.getInstance().getCandidateNumber());
			
		}
	}

	private void requestCandidate(int candidateNumber) {
		 String requestUrl = "candidate?id="+candidateNumber;  
		 RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, requestUrl);
		 try
		    {
		        requestBuilder.sendRequest(null, new RequestCallback()
		        {	

					@Override
					public void onResponseReceived(Request request,
							Response response) {
						 if (response.getStatusCode() == 200)
			                {
							 MyFactory factory = GWT.create(MyFactory.class);
							    AutoBean<Candidate2> bean = AutoBeanCodex.decode(factory, Candidate2.class, response.getText());
							    candidate =  bean.as();
							    html.setHTML("<p>Olete hääletanud kandidaadi <a href='#'>"
										+ candidate.getFirstName() + " " + candidate.getLastName() + "</a> poolt.</p>");
								html.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										ContentContainer.getInstance().setContent(new Candidate("" + candidate.getId()));
										
									}
								});
							    if (!cancelVote.isVisible()) {
									cancelVote.setVisible(true);
								}
								if (toCandidates.isVisible()) {
									toCandidates.setVisible(false);
								}
							
			                 }else
			                {
			                    System.out.println(response.getText() + " : " + response.getStatusCode() + response.getStatusText());
			                }
						
					}

					@Override
					public void onError(Request request, Throwable exception) {
						exception.printStackTrace();
						
					}
		        });
		    } catch (com.google.gwt.http.client.RequestException e)
		    {
		        e.printStackTrace();
		    }
	}

	public void changeCandidate() {
		if (!ContentContainer.getInstance().getCandidate()) {
			html2.setHTML("");
			if (cancelCandidate.isVisible()) {
				cancelCandidate.setVisible(false);
			}
			if (!addCandidate.isVisible()) {
				addCandidate.setVisible(true);
			}
		} else {
			html2.setHTML("<p>Olete end lisanud kandidaadiks piirkonnas "
					+ "Tartu linn"
					+ ". Kandideerimist saab tühistada 1.juunini (k.a).</p>");
			if (!cancelCandidate.isVisible()) {
				cancelCandidate.setVisible(true);
			}
			if (addCandidate.isVisible()) {
				addCandidate.setVisible(false);
			}
		}
	}

	public static void voted(int candidateNumber) {
		ContentContainer.getInstance().setVoted(candidateNumber);
	}
	
	public static void added(boolean b) {
		ContentContainer.getInstance().setCandidate(b);
	}

	public static void newChoices() {
		ContentContainer.getInstance().setContent(new Choices());
	}

	public static native void exportStaticMethod2() /*-{
		$wnd.newChoices = $entry(@com.valimised.client.Choices::newChoices());
	}-*/;

	public static native void exportStaticMethod3() /*-{
		$wnd.voted = $entry(@com.valimised.client.Choices::voted(I));
	}-*/;

	public static native void exportStaticMethod4() /*-{
		$wnd.added = $entry(@com.valimised.client.Choices::added(Z));
	}-*/;
}
