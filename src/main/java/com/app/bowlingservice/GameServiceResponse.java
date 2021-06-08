package com.app.bowlingservice;

import java.util.Date;
import java.util.UUID;

import com.app.bowlingservice.model.IGame;

public class GameServiceResponse {

	private String resonseType = "com.app.bowlingservice.model.IGame.GameServiceReponse";
	
	private UUID token;
	
	private Date responseDate;
	
	private String message;
	
	private IGame gameData;
	
	public GameServiceResponse() {
		initialize();
	}
	
	private void initialize() {
		this.token = UUID.randomUUID();
		this.responseDate = new Date();
	}

	public GameServiceResponse(IGame game) {
		initialize();
		this.gameData = game;
	}

	public String getResonseType() {
		return resonseType;
	}

	public void setResonseType(String resonseType) {
		this.resonseType = resonseType;
	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IGame getGameData() {
		return gameData;
	}

	public void setGameData(IGame gameData) {
		this.gameData = gameData;
	}
}
