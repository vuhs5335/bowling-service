package com.app.bowlingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bowlingservice.GameNotFoundException;
import com.app.bowlingservice.model.BowlingGame;
import com.app.bowlingservice.model.IGame;

@Service
public class BowlingGameService implements IGameService {

	@Autowired
	ApplicationService appService;
	
	@Override
	public IGame createGame() {
		
		IGame game = new BowlingGame();
		
		appService.addGame(game);
		
		return game;
	}

	@Override
	public IGame playTurn(Long id) {
		
		IGame game = appService.getGameById(id);
		
		try {
			
			game.playTurn();
		
		} catch (GameNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return game;
	}
}
