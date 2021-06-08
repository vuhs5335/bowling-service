package com.app.bowlingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bowlingservice.model.IGame;
import com.app.bowlingservice.service.IGameService;

@CrossOrigin(origins ="*", allowedHeaders ="*")
@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	IGameService service;
	
	@GetMapping("/new")
	public IGame createNewGame() {
		
		IGame game = service.createGame();
		
		return game;
	}
	
	@GetMapping("/play/{id}")
	public IGame playTurn(@PathVariable Long id) {
		
		IGame game = service.playTurn(id);
		
		return game;
	}
}
