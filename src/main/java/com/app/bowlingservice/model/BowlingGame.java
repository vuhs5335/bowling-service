package com.app.bowlingservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.app.bowlingservice.GameServiceException;

public class BowlingGame extends Game{
	
	private int currentFrameSequence = 0;
	
	private Map<Integer, BowlingGameFrame> frames;
	
	private List<String> messages;

	private int gameScore;
	
	public BowlingGame() {
		super();
		
		initFrames();
		
		messages = new ArrayList<String>();
	}
	
	private void initFrames() {
		
		frames = new HashMap<Integer, BowlingGameFrame>();
		
		for (int i = 1; i < BowlingGameFrame.MAX_FRAME + 1; i++) {
			
			frames.put(i, new BowlingGameFrame(i));
		}
		
		currentFrameSequence ++;
	}

	@Override
	public void playTurn(){
		
		try {
		
			BowlingGameFrame currentFrame = getCurrentFrame();
			
			Roll roll = currentFrame.getNextRoll();
			
			if (roll == null) {
				
				messages.add("You've palyed all " + BowlingGameFrame.MAX_FRAME +  " frames!");
				
				return;
			}
			
			int previousPins = currentFrame.getPreviousPins(roll);
			
			int pins = (int) ((Math.random() * ((BowlingGameFrame.MAX_PINS - previousPins) - 1)) + 1);
			
			if (pins > 10) {
				
				throw new GameServiceException("Invalid pin calculation.");
			}
			
			roll.setPins(pins);
			
			roll.setPlayed(true);
			
			boolean isStrike = previousPins == 0 && pins == BowlingGameFrame.MAX_PINS;
			
			currentFrame.setStrike(isStrike);
			
			boolean isSpare = !isStrike && previousPins > 0 && ((previousPins + pins) == BowlingGameFrame.MAX_PINS);
			
			currentFrame.setSpare(isSpare);
			
			if (isStrike || isSpare) {
				
				currentFrame.setAllRollsPlayed();
			}
			
			updateScore();
		
		} catch (GameServiceException e) {
			
			messages.add(e.getMessage());
			
		} catch (Exception e) {
			
		}
	}
	
	private void updateScore() {
		
		int score = 0;
		
		for (Entry<Integer, BowlingGameFrame> entry : frames.entrySet()) {
			
			BowlingGameFrame frame = entry.getValue();
			
		}
	}

	public BowlingGameFrame getCurrentFrame() {
		
		BowlingGameFrame frame = frames.get(currentFrameSequence);
		
		if (frame.isComplete()) {
			
			if (frame.getFrameSequence() < BowlingGameFrame.MAX_FRAME) {
				
				currentFrameSequence ++;
			}
			
			frame = frames.get(currentFrameSequence);
		}
		
		return frame;
	}
	
	public Map<Integer, BowlingGameFrame> getFrames() {
		
		return frames;
	}

	public void setFrames(Map<Integer, BowlingGameFrame> frames) {
		
		this.frames = frames;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}
}
