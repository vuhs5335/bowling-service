package com.app.bowlingservice.model;

public interface IGame {

	int getScore();
	
	long getId();
	
	void playTurn() ;
	
	void playTurn(Integer pins) ;
}
