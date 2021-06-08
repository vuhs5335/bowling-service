package com.app.bowlingservice.service;

import com.app.bowlingservice.GameNotFoundException;
import com.app.bowlingservice.model.IGame;

public interface IGameService {

	//TODO: Create repository, for now , store in application.
	
	public IGame createGame();
	
	public IGame playTurn(Long id) throws GameNotFoundException;
	
}
