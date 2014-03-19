package edu.sussex.tele.game;

public class Room {
	
	private RoomEvents roomEvents;
	private boolean endRoom;
	public Room(RoomEvents re){
		roomEvents = re;
	}
	public boolean isEndRoom() {
		return endRoom;
	}
	public void setEndRoom(boolean endRoom) {
		this.endRoom = endRoom;
	}
	
	public void enterRoom(){
		roomEvents.enterRoom();
	}
	
	public void exitRoom(){
		roomEvents.exitRoom();
	}
	
}
