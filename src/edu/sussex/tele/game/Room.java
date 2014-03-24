package edu.sussex.tele.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.sussex.tele.game.battle.BattleDialog;
import edu.sussex.tele.game.characters.Enemy;
import processing.core.PConstants;
import processing.core.PImage;

public class Room {
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	private RoomEvents roomEvents;
	private boolean endRoom;
	private boolean startRoom;
	private PImage backgroundPImage;
	private GameGUI gameGUI;
	
	private int x,y;
	
	public Room(int x, int y, RoomEvents re){
		this.x =x;
		this.y =y;
		roomEvents = re;
		roomEvents.setRoom(this);
	}
	public boolean isEndRoom() {
		return endRoom;
	}
	public void setEndRoom(boolean endRoom) {
		this.endRoom = endRoom;
	}
	
	public void enterRoom(){
		roomEvents.enterRoom();
		if(!enemies.isEmpty()){
			new BattleDialog(gameGUI, enemies, roomEvents.getGame().getHeros());
		}
	}
	
	public void exitRoom(){
		roomEvents.exitRoom();
	}
	public boolean isStartRoom() {
		return startRoom;
	}
	public PImage getBackground() {
		return backgroundPImage;
	}
	public void setBackground(String backgroundPath) {
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(backgroundPath));
			this.backgroundPImage =new PImage(image.getWidth(),image.getHeight(),PConstants.ARGB);
			image.getRGB(0, 0, backgroundPImage.width, backgroundPImage.height, backgroundPImage.pixels, 0, backgroundPImage.width);
			backgroundPImage.updatePixels();
		}catch(IOException e){
			
		}
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public RoomEvents getEvents() {
		return roomEvents;
	}
	public GameGUI getGameGUI() {
		return gameGUI;
	}
	public void setGameGUI(GameGUI gameGUI) {
		this.gameGUI = gameGUI;
	}
}
