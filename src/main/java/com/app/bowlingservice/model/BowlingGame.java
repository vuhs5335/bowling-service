package com.app.bowlingservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void playTurn() {
		
		BowlingGameFrame currentFrame = getCurrentFrame();
		
		Roll roll = currentFrame.getNextRoll();
		
		if (roll == null) {
			
			messages.add("You've palyed all " + BowlingGameFrame.MAX_FRAME +  " frames!");
			
			return;
		}
		
		int maxPins = 10;
		
		int previousPins = currentFrame.getPreviousPins(roll);
		
		if (previousPins == maxPins) {
			
			roll.setPlayed(true);
			
			return;
		}
		
		int points = (int) ((Math.random() * ((maxPins - previousPins) - 1)) + 1);
		
		roll.setScore(points);
		
		roll.setPlayed(true);
		
		updateScore();
	}
	
	private void updateScore() {
		
	}

	private BowlingGameFrame getCurrentFrame() {
		
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
