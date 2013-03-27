package com.valimised.shared;


public class Candidate {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private int party;
	
	private int area;
	
	private String description;
	
	private String image;
	
	private int votes;
	
	public Candidate(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
