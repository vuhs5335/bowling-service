package com.app.bowlingservice.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		
		frames = new LinkedHashMap<Integer, BowlingGameFrame>();
		
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
		
			BowlingGameFrame frame = getCurrentFrame();
			
			if (frame.isComplete()) {
				
				throw new GameServiceException("You've played all " + BowlingGameFrame.MAX_FRAME +  " frames!");
			}
			
			frame.roll(pins);
			
			updateScore(frame);
		
		} catch (GameServiceException e) {
			
			messages.add(e.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public BowlingGameFrame getCurrentFrame() {
		
		BowlingGameFrame frame = frames.get(currentFrameSequence);
		
		if (frame.isComplete()) {
			
			if (frame.getSequence() < BowlingGameFrame.MAX_FRAME) {
				
				currentFrameSequence ++;
			}
			
			frame = frames.get(currentFrameSequence);
		}
		
		return frame;
	}
	
	private void updateScore(BowlingGameFrame currentFrame) {
		
		score = 0;
		
		List<BowlingGameFrame> framesList =  frames.values().stream().collect(ArrayList::new, (list, num)-> list.add(num), ArrayList::addAll);
		
		for (int i = 0; i <= currentFrame.getSequence() - 1; i++) {	
			
			BowlingGameFrame frame = framesList.get(i);
			
			if (frame.isScored) {
				
				score += frame.getScore();
				
				continue;
			}
			
			if (!frame.isComplete()) {
				
				continue;
			}
			
			if(frame.extraRollsToProcess > 0) {
				
				frame.extraRollsToProcess --;
			}
			
			if (frame.extraRollsToProcess > 0) {
				
				continue;
			}
			
			// get next n rolls.
			int remainingRolls = frame.extraRollsEarned;
			
			int j = i + 1;
			
			while(remainingRolls > 0) {
				
				BowlingGameFrame nextFrame = framesList.get(j);
				
				for (Roll roll : nextFrame.getRolls()) {
					
					if (remainingRolls == 0) {
						
						break;
					}
					
					frame.setScore(frame.getScore() + roll.getPins());
					
					remainingRolls --;
				}
				
				j++;
			}
		
			score += frame.getScore();
			
			frame.isScored  = true;
		}
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
