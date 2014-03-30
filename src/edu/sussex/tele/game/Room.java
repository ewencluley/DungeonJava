package edu.sussex.tele.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.sussex.tele.game.battle.Battle;
import edu.sussex.tele.game.battle.BattleResult;
import edu.sussex.tele.game.characters.Enemy;
import processing.core.PConstants;
import processing.core.PImage;

public class Room implements Runnable{
	
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
	
	@Override
	public void run(){
		roomEvents.enterRoom();
		if(!enemies.isEmpty()){
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			roomEvents.game.battle = new Battle(enemies, roomEvents.game.heros);
			roomEvents.game.battle.run();
			roomEvents.getGame().setHeros(roomEvents.game.battle.returnHeros);
			this.setEnemies(roomEvents.game.battle.returnEnemies);
			roomEvents.game.battle = null;
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
	public void setEnemies(ArrayList<Enemy> returnEnemies) {
		enemies = returnEnemies;
		
	}
}
