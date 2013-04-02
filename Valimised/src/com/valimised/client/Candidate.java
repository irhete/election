package com.valimised.client;

import com.google.gwt.core.client.GWT;
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
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.valimised.shared.Candidate2;

public class Candidate extends Composite {
	
	private Candidate2 candidate;
	
	
	interface MyFactory extends AutoBeanFactory {
		  AutoBean<Candidate2> address();
		}
	
	public Candidate(String candidateNumber) {
		final VerticalPanel mainPanel = new VerticalPanel();
		initWidget(mainPanel);
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
								

								HorizontalPanel topPanel = new HorizontalPanel();
								topPanel.setStyleName("candidatePagePanel");

								Image candidatePicture = new Image("images/" + candidate.getImage());
								candidatePicture.setStyleName("candidatePicture");

								VerticalPanel credentialsPanel = new VerticalPanel();
								InlineHTML nameLabel = new InlineHTML("<b>" + candidate.getFirstName() + " " + candidate.getLastName() + "</b>, "
										+ candidate.getId());
								Label partyLabel = new Label(candidate.getParty());
								Label areaLabel = new Label(candidate.getArea());
								credentialsPanel.add(nameLabel);
								credentialsPanel.add(partyLabel);
								credentialsPanel.add(areaLabel);

								Button votingButton = new Button("Hääleta");
								votingButton.addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										ContentContainer.getInstance().setContent(new Choices());
									}
								});
								votingButton.addStyleName("candidatePageVotingButton");

								topPanel.add(candidatePicture);
								topPanel.add(credentialsPanel);
								topPanel.add(votingButton);

								Label descriptionLabel = new Label(candidate.getDescription());

								mainPanel.add(topPanel);
								mainPanel.add(descriptionLabel);
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
	
	public static void newCandidate(int candidateNumber) {
		ContentContainer.getInstance().setContent(new Candidate("" + candidateNumber));
	}
	
	public static native void exportStaticMethod() /*-{
    	$wnd.newCandidate = $entry(@com.valimised.client.Candidate::newCandidate(I));
 }-*/;
	
}