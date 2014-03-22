package edu.sussex.tele.game;

public abstract class RoomEvents {
	public abstract void enterRoom();
	public abstract void exitRoom();
	
	Game game;
	Room room;
	
	public void music(String path){
		game.music(path);
	}
	
	public void pause(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addGameListener(Game game){
		this.game = game;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}
