package edu.sussex.tele.game.battle;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class BattleGraphics extends PApplet {

	PImage battleBackground;
	PImage fightBackground;
	PImage deathBackground;
	Battle battle;
	HashMap<Rectangle, Enemy> enemyPos = new HashMap<Rectangle, Enemy>();
	private static final int  width = 800;
	private static final int  height = 600;
	
	private int damageY = 0;
	
	PFont font = createFont("Tahmola", 32);
	
	Enemy mouseOverEnemy =null;
	
	public BattleGraphics(Battle battle) {
		this.battle = battle;
	}

	public void setup(){
		size(width,height);
		battleBackground = this.loadImage("battle.jpg");
		fightBackground = this.loadImage("battleFight.jpg");
	}
	
	public void draw(){
		tint(255,255);
		switch(battle.getPhase()){
		case START_BATTLE:
			damageY=0;
			image(battleBackground,0,0);
			startBattle();
			break;
		case START_TURN:
			damageY=0;
			image(battleBackground,0,0);
			player_target();
			break;
		case PLAYER_TARGETS:
			damageY=0;
			image(battleBackground,0,0);
			player_target();
			break;
		case PLAYER_ATTACKS:
			image(fightBackground,0,0);
			player_fight();
			break;
		case NPC_TARGETS:
			damageY=0;
			image(battleBackground,0,0);
			npc_target();
			break;
		case NPC_ATTACKS:
			image(fightBackground,0,0);
			npc_fight();
			break;
		case END_BATTLE:
			break;
		case END_TURN:
			break;
		case TARGET_DEAD:
			image(battleBackground,0,0);
			break;
		}
		
	}
	
	private void npc_fight() {
		// TODO Auto-generated method stub
		
	}

	private void npc_target() {
		int enemyNo =1;
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Enemy is targeting your party!", width/2, 50);
		for(Hero e: battle.heros){
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			if(e == battle.currentTarget){
				tint(255,255);
			}
			else{
				tint(255,128);
			}
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	private void startBattle() {
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("You are fighting "+battle.enemies.size()+" enemies!", width/2, 50);
		int enemyNo =1;
		for(Enemy e: battle.enemies){
			if(battle.currentTarget == null){
				noTint();
			}
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	public void player_target(){
		mouseOverEnemy();
		int enemyNo =1;
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Select a target to attack", width/2, 50);
		for(Enemy e: battle.enemies){
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			if(e == battle.currentTarget){
				tint(255,255);
			}else if(e == mouseOverEnemy){
				text("Strength:"+e.strength, position.x+eImg.width/2, position.y-5);
				tint(100, 255);
			}
			else{
				tint(255,128);
			}
			image(eImg,position.x ,position.y);
			
			enemyPos.put(position, e);
			enemyNo++;
		}
	}
	
	public void player_fight(){
		PImage eImg = battle.currentTarget.getImage();
		PImage hImg = battle.currentCombatant.getImage();
		Rectangle ePosition = new Rectangle(width/4*3 - eImg.width/2, height - eImg.height, eImg.width, eImg.height);
		image(eImg,ePosition.x ,ePosition.y);
		Rectangle hPosition = new Rectangle(width/4 - hImg.width/2, height - hImg.height, hImg.width, hImg.height);
		image(hImg,hPosition.x ,hPosition.y);
		if(battle.damage>0){
			loseHP(eImg);
		}
	}
	
	public void loseHP(PImage eImg){
		Rectangle ePosition = new Rectangle(width/4*3 - eImg.width/2, height - eImg.height - damageY, eImg.width, eImg.height);
		
		text("-"+battle.damage,ePosition.x ,ePosition.y);
		damageY += 10;
	}

	public void mousePressed() {
		if(battle.getPhase()==BattlePhase.PLAYER_TARGETS){
			Point mousePos = new Point(mouseX, mouseY);
			  for(Rectangle r:enemyPos.keySet()){
				  if(r.contains(mousePos)){
					  battle.setCurrentTarget(enemyPos.get(r));
					  battle.setPhase(BattlePhase.PLAYER_ATTACKS);
					  
				  }
			  }
		}
	}
	
	private void mouseOverEnemy() {
		Point mousePos = new Point(mouseX, mouseY);
		mouseOverEnemy = null;
		for(Rectangle r:enemyPos.keySet()){
			if(r.contains(mousePos)){
				mouseOverEnemy = enemyPos.get(r);
			}
		}
	}

}
