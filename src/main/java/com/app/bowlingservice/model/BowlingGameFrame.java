package com.app.bowlingservice.model;

import java.beans.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BowlingGameFrame {
	
	public static final int MAX_FRAME = 2;
	
	public static final int MAX_PINS = 10;
	
	private int frameSequence = 0;

	private int frameScore = 0;
	
	protected int currentRoll = 0;
	
	private boolean isScored;
	
	private boolean isStrike;
	
	private boolean isSpare;
	
	private Roll[] rolls;
	
	protected int totalPins;
	
	public BowlingGameFrame(int frameSequence) {
		
		this.frameSequence = frameSequence;
		
		int maxRolls = frameSequence < MAX_FRAME ? 2 : 3;
		
		rolls = new Roll[maxRolls];
		
		for (int i = 0; i < maxRolls; i++) {
			
			rolls[i] = new Roll(i + 1);
		}
	}
	
	public boolean isComplete() {
		
		Roll roll = getNextRoll();
		
		return roll == null;
	}
	
	//TODO: figure out how to ignore this in rest response.
	@Transient
	@JsonIgnore
	public Roll getNextRoll() {
		
		try {
		
			return rolls[currentRoll];
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	public void setAllRollsPlayed() {
		
		for (int i = 0; i < rolls.length; i++) {

			Roll roll = rolls[i];
			
			roll.setPlayed(true);
		}
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

	public int getFrameScore() {
		return frameScore;
	}

	public void setFrameScore(int frameScore) {
		this.frameScore = frameScore;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean isScored) {
		this.isScored = isScored;
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

	public int getCurrentRoll() {
		return currentRoll;
	}

	public void setCurrentRoll(int currentRoll) {
		this.currentRoll = currentRoll;
	}

}