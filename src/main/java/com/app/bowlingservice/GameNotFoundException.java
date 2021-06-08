package com.app.bowlingservice;

public class GameNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameNotFoundException() {

		super("Game not found.");
	}
	
}
