package com.app.bowlingservice.model;

public class Roll{
	
	int sequence = 0;
	
	int pins = 0;
	
	boolean played;
	
	boolean isStrike;
	
	boolean isSpare;
	
	public Roll(int sequence) {
		
		this.sequence = sequence;
	}
	
	public int getPins() {
		return pins;
	}

	public void setPins(int pins) {
		this.pins = pins;
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

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}
	
	public boolean isStrikeOrSpare() {
		return isStrike || isSpare;
	}
}
