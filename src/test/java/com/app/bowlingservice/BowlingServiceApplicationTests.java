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
			
			int actual = game.getCurrentFrame().getCreateRoll().getSequence();
			
			assertEquals(expected, actual);
			
		} catch (GameNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void score20GutterGame() {
		
		BowlingGame game = (BowlingGame) service.createGame();
			
		int expected = 0;
		
		for (int i = 0; i < 20; i++) {
			
			game.playTurn(0);
		}
		
		int actual = game.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void score20PointGame() {
		
		BowlingGame game = (BowlingGame) service.createGame();
		
		int expected = 20;
		
		for (int i = 0; i < 20; i++) {
			
			game.playTurn(1);
		}
		
		int actual = game.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void run2Spares17Gutters() {
		
		BowlingGame game = (BowlingGame) service.createGame();
		
		int expected = 19;
		
		int[] rolls = {5,5,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		for (int i = 0; i < rolls.length; i++) {
			
			game.playTurn(rolls[i]);
		}
		
		int actual = game.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void run1Strike16Gutters() {
		
		BowlingGame game = (BowlingGame) service.createGame();
		
		int expected = 22;
		
		int[] rolls = {10,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		for (int i = 0; i < rolls.length; i++) {
			
			game.playTurn(rolls[i]);
		}
		
		int actual = game.getScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void perfectGame() {
		
		BowlingGame game = (BowlingGame) service.createGame();
		
		int expected = 300;
		
		int[] rolls = {10,10,10,10,10,10,10,10,10,10,10,10};
		
		for (int i = 0; i < rolls.length; i++) {
			
			game.playTurn(rolls[i]);
		}
		
		int actual = game.getScore();
		
		assertEquals(expected, actual);
	}
}
