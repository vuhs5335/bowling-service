package com.app.bowlingservice.model;

import java.beans.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BowlingGameFrame {
	
	public static final int MAX_FRAME = 2;
	
	private int frameSequence = 0;
	
	private Roll[] rolls = {new Roll(1), new Roll(2), new Roll(3)};
	
	public BowlingGameFrame(int frameSequence) {
		
		this.frameSequence = frameSequence;
	}
	
	public boolean isComplete() {
		
		Roll roll = getNextRoll();
		
		return roll == null;
	}
	
	//TODO: figure out how to ignore this in rest response.
	@Transient
	@JsonIgnore
	public Roll getNextRoll() {

		for (int i = 0; i < rolls.length; i++) {

			Roll roll = rolls[i];
			
			if (roll.isPlayed() || frameSequence < MAX_FRAME && roll.sequence > 2) {
				
				continue;
			}

			return roll;
		}

		return null;
	}

	public int getFrameSequence() {
		return frameSequence;
	}

	public void setFrameSequence(int frameSequence) {
		this.frameSequence = frameSequence;
	}

	public Roll[] getRolls() {
		return rolls;
	}

	public void setRolls(Roll[] rolls) {
		this.rolls = rolls;
	}
}

class Roll{
	
	int sequence = 0;
	
	int score = 0;
	
	boolean played;

	public Roll(int sequence) {
		
		this.sequence = sequence;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
