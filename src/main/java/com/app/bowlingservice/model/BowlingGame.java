package com.app.bowlingservice.model;

import java.util.HashMap;
import java.util.Map;

public class BowlingGame extends Game{
	
	private int currentFrameSequence = 0;
	
	private Map<Integer, BowlingGameFrame> frames;
	
	public BowlingGame() {
		super();
		
		initFrames();
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
			
			System.err.println("You've palyed all " + BowlingGameFrame.MAX_FRAME +  " frames!");
			
			return;
		}
		
		roll.setScore(8);
		
		roll.setPlayed(true);
		
		System.err.println("I played a turn!!!");
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
}
