package com.app.bowlingservice.model;

import java.beans.Transient;
import java.util.LinkedList;
import java.util.List;

import com.app.bowlingservice.GameServiceException;

public class BowlingGameFrame {
	
	public static final int MAX_FRAME = 10;
	
	public static final int MAX_PINS = 10;
	
	public static final int MAX_ROLL = 2;
	
	public int maxRoll = 0;
	
	private int sequence = 0;
	
	protected int totalPins = 0;
	
	protected int extraRollsToProcess;
	
	protected int extraRollsEarned;
	
	private int score;

	protected boolean isScored;
	
	private boolean isStrike;
	
	private boolean isSpare;
	
	private boolean isComplete;
	
	private List<Roll> rolls;
	
	public BowlingGameFrame(int frameSequence) {
		
		this.sequence = frameSequence;
		
		maxRoll = MAX_ROLL;
		
		rolls = new LinkedList<Roll>();
	}
	
	@Transient
	public Roll getCreateRoll() {
		
		try {
			Roll nextRoll = null;
			
			if (rolls.size() < maxRoll) {
				
				nextRoll = new Roll(rolls.size() + 1);
				
				rolls.add(nextRoll);
			}
			
			return nextRoll;
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	public void updateFrame(Roll roll) {
		
		setStrike(roll.isStrike);

		setSpare(roll.isSpare);
		
		this.totalPins += roll.pins;
		
		this.setScore(this.getScore() + roll.pins); // Keep a base score
		
		if (roll.isStrikeOrSpare()) {
			
			extraRollsEarned = (roll.isStrike ? 2 : 1);
			
			extraRollsToProcess = (roll.isStrike ? 2 : 1) + 1;
			
			if (sequence == MAX_FRAME) {
				
				extraRollsToProcess = 0;
				
				extraRollsEarned = 0;
				
				if (roll.isStrikeOrSpare()) {
					
					maxRoll = 3;
					
					totalPins = 0;
				}
				
				isComplete = rolls.size() == maxRoll;
				
				return;
			}
		}
		
		isComplete = isSpareOrStrike() || rolls.size() == maxRoll;
	}

	public void roll(Integer pins) throws GameServiceException {
		
		int previousPins = totalPins;
		
		pins = pins != null ? pins : (int) (Math.random() * (MAX_PINS - previousPins));

		if ((pins + previousPins) > 10) {
			
			throw new GameServiceException("Invalid pin calculation.");
		}
		
		boolean isStrike = previousPins == 0 && pins == MAX_PINS;
		
		boolean isSpare = !isStrike && previousPins > 0 && ((previousPins + pins) == MAX_PINS);
		
		Roll roll = getCreateRoll();
		
		roll.setStrike(isStrike);
		
		roll.setSpare(isSpare);
		
		roll.setPins(pins);
		 
		updateFrame(roll);
	}
	
	public void roll() throws GameServiceException {
		roll(null);
	}
	
	public boolean isComplete() {
		
		return isComplete;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public List<Roll> getRolls() {
		return rolls;
	}

	public void setRolls(List<Roll> rolls) {
		this.rolls = rolls;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		if (!isStrike) 
			return;
		
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		if (!isSpare) 
			return;
		
		this.isSpare = isSpare;
	}
	
	@Transient
	public boolean isSpareOrStrike() {
		
		return isSpare || isStrike;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}