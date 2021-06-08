package com.app.bowlingservice.model;

public class Roll{
	
	int sequence = 0;
	
	int points = 0;
	
	boolean played;

	public Roll(int sequence) {
		
		this.sequence = sequence;
	}
	
	public int getPins() {
		return points;
	}

	public void setPins(int pins) {
		this.points = pins;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
