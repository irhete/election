package com.valimised.shared;

public class AreaResult {
	
	private String name;
	private int candidateNumber;
	private String partyName;
	private int votes;
	
	public AreaResult(String name, int candidateNumber, String partyName, int votes){
		this.name = name;
		this.candidateNumber = candidateNumber;
		this.partyName = partyName;
		this.votes = votes;
	}
}
