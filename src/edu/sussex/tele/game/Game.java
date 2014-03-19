package edu.sussex.tele.game;

import processing.core.PImage;

public class Game implements Runnable{
	
	GameGUI gameGUI;
	Map theMap;
	Room currentRoom;
	
	public Game(Map map){
		theMap = map;
		currentRoom = map.startRoom;
	}
	
	public GameGUI getGameGUI() {
		return gameGUI;
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}

	@Override
	public void run() {
		currentRoom.enterRoom();
		
	}

	public void move(String direction) {
		switch(direction){
		case "FORWARD":
			//currentRoom = theMap.getRoomAt(currentRoom.x-1, currentRoom.y);
		}
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
