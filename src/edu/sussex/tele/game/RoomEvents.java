package edu.sussex.tele.game;

import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;

public abstract class RoomEvents {
	public abstract void enterRoom();
	public abstract void exitRoom();
	
	public boolean visited = false;
	
	Game game;
	SoundPlayer sp;
	public Game getGame() {
		return game;
	}

	Room room;
	
	public void music(String path){
		game.music(path);
	}
	
	public void sound(String path){
		sp = new SoundPlayer(path);
		Thread musicThread = new Thread(sp);
		musicThread.start();
	}
	
	public void pause(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEnemy(Enemy e ){
		room.enemies.add(e);
	}
	
	public void addGameListener(Game game){
		this.game = game;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public void playerMakeHero(){
		game.addHero(Hero.PlayerMakesHero());
		
	}
}
