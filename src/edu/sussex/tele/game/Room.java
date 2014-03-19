package edu.sussex.tele.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PConstants;
import processing.core.PImage;

public class Room {
	
	private RoomEvents roomEvents;
	private boolean endRoom;
	private boolean startRoom;
	private PImage backgroundPath;
	
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
	public boolean isStartRoom() {
		return startRoom;
	}
	public PImage getBackground() {
		return backgroundPath;
	}
	public void setBackground(String backgroundPath) {
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(backgroundPath));
			this.backgroundPath =new PImage(image.getWidth(),image.getHeight(),PConstants.ARGB);
			image.getRGB(0, 0, backgroundPath.width, backgroundPath.height, backgroundPath.pixels, 0, backgroundPath.width);
			image.updatePixels();
		}catch(IOException e){
			
		}
	}
	
	
	
}
