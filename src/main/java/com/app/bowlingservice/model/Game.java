package com.app.bowlingservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Game implements IGame{

	private long id;
	
	protected List<String> messages;

	protected int score;
	
	private static final AtomicLong counter = new AtomicLong();
	
	public Game() {
		initialize();
	}
	
	public void playTurn(){
		
	}
	
	@Override
	public void playTurn(Integer pins) {
		
	}
	
	private void initialize() {
		
		this.setScore(0);

		this.id = counter.incrementAndGet();
		
		this.messages = new ArrayList<String>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
