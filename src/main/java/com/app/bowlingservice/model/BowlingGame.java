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
		playTurn(null);
	}
	
	@Override
	public void playTurn(Integer pins){
		
		try {
		
			BowlingGameFrame currentFrame = getCurrentFrame();
			
			Roll roll = currentFrame.getNextRoll();
			
			if (roll == null) {
				
				messages.add("You've played all " + BowlingGameFrame.MAX_FRAME +  " frames!");
				
				return;
			}
			
			int previousPins = currentFrame.totalPins;
			
			pins = pins != null ? pins : (int) (Math.random() * (BowlingGameFrame.MAX_PINS - previousPins));

			if (pins > 10) {
				
				throw new GameServiceException("Invalid pin calculation.");
			}
			
			boolean isStrike = previousPins == 0 && pins == BowlingGameFrame.MAX_PINS;
			
			boolean isSpare = !isStrike && previousPins > 0 && ((previousPins + pins) == BowlingGameFrame.MAX_PINS);
			
			// Update the roll. 
			
			roll.setStrike(isStrike);
			
			roll.setSpare(isSpare);
			
			roll.setPins(pins);
			
			roll.setPlayed(true);
			
			// Update the frame. 
			
			currentFrame.setStrike(isStrike);
			
			currentFrame.setSpare(isSpare);
			
			currentFrame.currentRoll ++;
			
			currentFrame.totalPins += pins;
			
			if (roll.isStrikeOrSpare()) {
				
				currentFrame.updateRollsAndPinsForStrikeSpare();
			}
			
			updateScore();
		
		} catch (GameServiceException e) {
			
			messages.add(e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	private void updateScore() {
		
		score = 0;
		
		for (Entry<Integer, BowlingGameFrame> entry : frames.entrySet()) {
			
			BowlingGameFrame frame = entry.getValue();

			if (frame.isScored()){

				score += frame.totalPins;

				continue;
			}

			if (!frame.isSpare() && !frame.isStrike() && frame.isComplete()){

				score += frame.totalPins;

				frame.setScored(true);
			}
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
}
