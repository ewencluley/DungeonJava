package edu.sussex.tele.game.characters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PConstants;
import processing.core.PImage;

public class Weapon {
	public int damage = 1;
	private PImage image;
	
	public Weapon(String imagePath){
		setImage(imagePath);
	}
	
	public PImage getImage(){
		return image;
	}
	
	public void setImage(String path){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(path));
			this.image =new PImage(image.getWidth(),image.getHeight(),PConstants.ARGB);
			image.getRGB(0, 0, this.image.width, this.image.height, this.image.pixels, 0, this.image.width);
			this.image.updatePixels();
		}catch(IOException e){
			
		}
	}
}
