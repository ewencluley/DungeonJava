package edu.sussex.tele.ui;

import java.util.ConcurrentModificationException;

import edu.sussex.tele.game.Game;
import edu.sussex.tele.game.characters.Enemy;
import processing.core.PApplet;
import processing.core.PImage;

public class GraphicsPanel extends PApplet {

	PImage background;
	Game game;
	
	public PImage getBackgroundImg() {
		return background;
	}

	public void setBackgroundImg(String background) {
		this.background = super.loadImage(background+".jpg");
	}
	
	public GraphicsPanel(Game game){
		this.game = game;
	}
	
	public void setup() {
	    size(800,600);
	    background(0);
	}

	public void draw() {
		background = game.getCurrentRoom().getBackground();
	    stroke(255);
	    if (background != null) {
	    	this.image(background, 0, 0);
	    }
	    int size = game.getCurrentRoom().getEnemies().size();
	    int enemyNo =1;
	    try{
		    for(Enemy e: game.getCurrentRoom().getEnemies()){
		    	int spacing = 800 /size;
		    	this.image(e.getImage(), (spacing * enemyNo)-e.getImage().width, 590 - e.getImage().height);
		    	enemyNo ++;
		    }
	    }catch(ConcurrentModificationException e){}
	}
	
}
