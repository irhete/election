package com.valimised.shared;


public class Candidate {
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String party;
	
	private String area;
	
	private String description;
	
	private String image;
	
	private int votes;
	
	public Candidate(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Candidate(String lastName, String firstName, int id, String area,
			String party) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.area = area;
		this.party = party;
	}
	
	public Candidate(String lastName, String firstName, int id, String area,
			String party, String description, String image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.area = area;
		this.party = party;
		this.description = description;
		this.image = image;
	}
}
