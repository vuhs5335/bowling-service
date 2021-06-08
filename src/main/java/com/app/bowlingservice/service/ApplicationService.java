package com.app.bowlingservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.app.bowlingservice.model.IGame;

@ApplicationScope
@Service
public class ApplicationService {

	Map<Long, IGame> applicationGames;
	
	public ApplicationService() {
	
		initialize();
	}
	
	private void initialize() {
		
		this.applicationGames = new HashMap<Long, IGame>();
	}
	
	public IGame getGameById(Long id) {
		
		return applicationGames.get(id);
	
	};
	
	public void addGame(IGame game) {
		
		applicationGames.put(game.getId(), game);
	}
}
