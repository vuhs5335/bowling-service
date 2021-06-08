package com.app.bowlingservice.model;

import java.util.concurrent.atomic.AtomicLong;

public class Game implements IGame{

	private long id;
	
	private int score;
	
	private static final AtomicLong counter = new AtomicLong();
	
	public Game() {
		initialize();
	}
	
	public void playTurn() {
		
	}
	
	private void initialize() {
		this.setScore(0);
		this.id = counter.incrementAndGet();
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
}
