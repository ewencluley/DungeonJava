package edu.sussex.tele.game;

import java.util.ArrayList;
import java.util.List;

import processing.core.PImage;

public class Game implements Runnable{
	
	GameGUI gameGUI;
	Map theMap;
	Room currentRoom;
	SoundPlayer musicPlayer;
	
	List<Monster> monsters = new ArrayList<Monster>(); 
	List<NPC> npcs = new ArrayList<NPC>(); 
	
	public Game(Map map){
		theMap = map;
		currentRoom = map.startRoom;
		currentRoom.getEvents().addGameListener(this);
	}
	
	public GameGUI getGameGUI() {
		return gameGUI;
	}
	
	public void addMonster(Monster monster){
		
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}

	@Override
	public void run() {
		currentRoom.enterRoom();
		
	}

	public void move(String direction) {
		Room nextRoom = null;
		switch(direction){
		case "FORWARD":
			nextRoom = theMap.getRoomAt(currentRoom.getX(), currentRoom.getY()-1);
			break;
		case "LEFT":
			nextRoom = theMap.getRoomAt(currentRoom.getX()-1, currentRoom.getY());
			break;
		case "RIGHT":
			nextRoom = theMap.getRoomAt(currentRoom.getX()+1, currentRoom.getY());
			break;
		case "BACKWARD":
			nextRoom = theMap.getRoomAt(currentRoom.getX(), currentRoom.getY()+1);
			break;
		}
		if(nextRoom != null){
			currentRoom.exitRoom();
			currentRoom = nextRoom;
			currentRoom.getEvents().addGameListener(this);
			currentRoom.enterRoom();
		}
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void music(String path){
		if(musicPlayer != null){
			musicPlayer.stopSound();
		}
		musicPlayer = new SoundPlayer(path);
		Thread musicThread = new Thread(musicPlayer);
		musicThread.start();
	}

	public void repaintGUI() {
		gameGUI.pack();
	}
	
}
