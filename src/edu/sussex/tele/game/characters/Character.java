package edu.sussex.tele.game.characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PConstants;
import processing.core.PImage;

public class Character {
	public int initiative = 1;
	public int strength = 1;
	public int hp = 1;
	public int defence= 1;
	
	public String name = "";
	public Weapon weapon;
	
	private PImage charImage;
	
	public Character(String imagePath){
		setImage(imagePath);
	}
	
	public PImage getImage(){
		return charImage;
	}
	
	public void setImage(String path){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(path));
			this.charImage =new PImage(image.getWidth(),image.getHeight(),PConstants.ARGB);
			image.getRGB(0, 0, charImage.width, charImage.height, charImage.pixels, 0, charImage.width);
			charImage.updatePixels();
		}catch(IOException e){
			
		}
	}
}
