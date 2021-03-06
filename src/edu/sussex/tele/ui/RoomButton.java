package edu.sussex.tele.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import processing.core.PImage;

public class RoomButton extends JButton {

	public int x,y;
	private String enterRoomCode;
	private boolean startRoom;
	private String roomImage = "images/backgrounds/default.jpg";
	private String roomName = "a dungeon room";
	
	public String getRoomImage() {
		return roomImage;
	}

	public void setRoomImage(String roomImage) {
		this.roomImage = roomImage;
	}

	public boolean isStartRoom() {
		return startRoom;
	}

	public void setStartRoom(boolean startRoom) {
		this.startRoom = startRoom;
		if(startRoom){
			this.setBackground(Color.BLUE);
		}
	}

	private boolean endRoom;
	
	public String getEnterRoomCode() {
		return enterRoomCode;
	}

	public void setEnterRoomCode(String enterRoomCode) {
		this.enterRoomCode = enterRoomCode;
	}

	public String getExitRoomCode() {
		return exitRoomCode;
	}

	public void setExitRoomCode(String exitRoomCode) {
		this.exitRoomCode = exitRoomCode;
	}

	private String exitRoomCode;
	private boolean roomExists;
	private boolean selected;
	
	private Border raisedBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	private Border unselectedBorder = BorderFactory.createLineBorder(Color.darkGray, 1);
	
	public boolean isRoomExists() {
		return roomExists;
	}

	public RoomButton(int x, int y){
		super();
		this.x = x;
		this.y = y;
		this.setBackground(Color.gray);
		this.setBorder(unselectedBorder);
	}
	
	public void activate(){
		if(!roomExists){
			roomExists = true;
			this.setBackground(Color.green);
			System.out.println("room made at"+x +":"+y);
		}
		this.setBorder(raisedBorder);
		
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected){
			this.setBorder(raisedBorder);
		}else{
			this.setBorder(unselectedBorder);
		}
	}

	public boolean isEndRoom() {
		return endRoom;
	}

	public void setEndRoom(boolean endRoom) {
		this.endRoom = endRoom;
		if(endRoom){
			this.setBackground(Color.RED);
		}
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}
