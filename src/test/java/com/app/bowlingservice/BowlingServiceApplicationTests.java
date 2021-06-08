package com.app.bowlingservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.bowlingservice.model.BowlingGame;
import com.app.bowlingservice.model.IGame;
import com.app.bowlingservice.service.BowlingGameService;
import com.app.bowlingservice.service.IGameService;

@SpringBootTest
class BowlingServiceApplicationTests {

	IGameService service = new BowlingGameService();
	
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
}
