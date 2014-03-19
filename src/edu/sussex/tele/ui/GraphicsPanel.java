package edu.sussex.tele.ui;

import processing.core.PApplet;
import processing.core.PImage;

public class GraphicsPanel extends PApplet {

	PImage background;
	
	public PImage getBackgroundImg() {
		return background;
	}

	public void setBackgroundImg(String background) {
		this.background = super.loadImage(background+".jpg");
	}
	public void setup() {
	    size(800,600);
	    background(0);
	  }

	  public void draw() {
	    stroke(255);
	    if (background != null) {
	      this.image(background, 0, 0);
	      
	    }
	  }
	
}
