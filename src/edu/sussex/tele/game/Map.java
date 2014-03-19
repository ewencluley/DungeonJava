package edu.sussex.tele.game;

public class Map {
	Room[][] theMap= new Room[10][10];
	Room startRoom;
	
	public void addRoom(Room room, int x, int y){
		theMap[x][y] = room;
	}

	public void setStartRoom(Room room) {
		startRoom = room;
	}

	public Room getRoomAt(int x, int y) {
		return theMap[x][y];
	}
}
