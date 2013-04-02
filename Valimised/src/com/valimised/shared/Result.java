package com.valimised.shared;

public class Result {
	
	private String area;
	private String party;
	private int votes;
	private int areaId;
	private int partyId;
	
	public Result(String area, String party, int votes, int areaId, int partyId){
		this.area = area;
		this.party = party;
		this.votes = votes;
		this.areaId = areaId;
		this.partyId = partyId;
	}
	
	public Result(String area, String party, int votes, int areaId){
		this(area, party, votes, areaId,0);
	}

}
