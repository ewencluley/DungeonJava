package edu.sussex.tele.game;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends Component{

	int strength;
	int intelligence;
	
	BufferedImage torso, head, leftArm, rightArm, leftLeg, rightLeg;
	
	public Character(){
		String charFolder = "character";
		try {
			torso =ImageIO.read(new File(charFolder + "\\torso.png"));
			leftLeg =ImageIO.read(new File(charFolder + "\\leftLeg.png"));
			rightLeg =ImageIO.read(new File(charFolder + "\\rightLeg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getStrength() {
		return strength;
	}
	public void setStrengthTo(int strength) {
		this.strength = strength;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligenceTo(int intelligence) {
		this.intelligence = intelligence;
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(torso,0,0,null);
	}
	
	
	
}
