package edu.sussex.tele.game;

public abstract class RoomEvents {
	public abstract void enterRoom();
	public abstract void exitRoom();
	
	public void pause(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMonster(){
		
	}
}
