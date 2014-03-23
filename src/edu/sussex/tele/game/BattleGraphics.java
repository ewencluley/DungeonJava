package edu.sussex.tele.game;

import processing.core.PApplet;
import processing.core.PImage;

public class BattleGraphics extends PApplet {

	PImage battleBackground;
	
	public void setup(){
		size(400,300);
		battleBackground = this.loadImage("battle.jpg");
	}
	
	public void draw(){
		image(battleBackground,0,0);
	}
}
