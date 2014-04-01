package edu.sussex.tele.game;

import java.util.ArrayList;
import java.util.List;

import edu.sussex.tele.game.battle.Battle;
import edu.sussex.tele.game.characters.Hero;

public class Game implements Runnable{
	
	GameGUI gameGUI;
	Map theMap;
	Room currentRoom;
	SoundPlayer musicPlayer;
	
	Battle battle = null;
	
	ArrayList<Hero> heros = new ArrayList<Hero>(); 
	List<NPC> npcs = new ArrayList<NPC>(); 
	
	public Game(Map map){
		theMap = map;
		currentRoom = map.startRoom;
		currentRoom.getEvents().addGameListener(this);
	}
	
	public Battle getCurrentBattle(){
		return battle;
	}
	
	public GameGUI getGameGUI() {
		return gameGUI;
	}
	
	public void addMonster(Monster monster){
		
	}

	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
		currentRoom.setGameGUI(gameGUI);
	}

	@Override
	public void run() {
		currentRoom.setGameGUI(gameGUI);
		currentRoom.getEvents().addGameListener(this);
		new Thread(currentRoom).start();
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
			currentRoom.setGameGUI(gameGUI);
			currentRoom.getEvents().addGameListener(this);
			new Thread(currentRoom).start();
			//currentRoom.enterRoom();
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

	public ArrayList<Hero> getHeros() {
		return heros;
	}

	public void setHeros(ArrayList<Hero> heros2) {
		heros = heros2;
		
	}
	
	public void addHero(Hero h){
		heros.add(h);
	}
	
}
