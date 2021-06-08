package com.app.bowlingservice.model;

import com.app.bowlingservice.GameNotFoundException;

public interface IGame {

	int getScore();
	
	long getId();
	
	void playTurn() throws GameNotFoundException ;
}
