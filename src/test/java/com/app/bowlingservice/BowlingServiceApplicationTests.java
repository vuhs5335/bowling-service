package com.app.bowlingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.bowlingservice.model.BowlingGame;
import com.app.bowlingservice.model.IGame;
import com.app.bowlingservice.service.IGameService;

@SpringBootTest
class BowlingServiceApplicationTests {

	@Autowired
	IGameService service;
	
	@Test
	void contextLoads() {
		
	}

	@Test
	public void createGame() {
		
		IGame actual = service.createGame();
		
		assertTrue(actual instanceof BowlingGame);
	}
	
	@Test
	public void createZeroScoreGame() {
		
		int expected = 0;
		
		int actual = service.createGame().getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void rollBallSuccessfully() {
		
		long id = service.createGame().getId();
		
		BowlingGame game;
		
		try {
			game = (BowlingGame) service.playTurn(id);
			
			int expected = 2;
			
			int actual = game.getCurrentFrame().getNextRoll().getSequence();
			
			assertEquals(expected, actual);
			
		} catch (GameNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void score20GutterGame() {
		
	}
	
}
