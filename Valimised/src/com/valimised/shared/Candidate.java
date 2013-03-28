package com.valimised.shared;


public class Candidate {
	
	private int id;
	
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

	public Candidate(String lastName, String firstName, int id, int area,
			int party) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.area = area;
		this.party = party;
	}
}
