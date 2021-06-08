package com.app.bowlingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bowlingservice.GameNotFoundException;
import com.app.bowlingservice.GameServiceException;
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
	public IGame playTurn(Long id) throws GameNotFoundException{
		
		IGame game = null;
		
		try {
			
			game = appService.getGameById(id);
			
			if (game == null) {
				
				throw new GameNotFoundException();
			}
			
			game.playTurn();
		
		} catch (GameNotFoundException e) {
			
			throw e;
		}
		
		return game;
	}
}
