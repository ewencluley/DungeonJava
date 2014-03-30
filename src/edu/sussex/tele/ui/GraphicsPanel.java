package edu.sussex.tele.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

import edu.sussex.tele.game.Game;
import edu.sussex.tele.game.battle.Battle;
import edu.sussex.tele.game.battle.BattlePhase;
import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class GraphicsPanel extends PApplet {

	PImage background;
	Game game;
	private PImage battleBackground;
	private PImage fightBackground;
	private PImage deathBackground;
	private int damageY;
	PFont font = createFont("Tahmola", 32);
	HashMap<Rectangle, Enemy> enemyPos = new HashMap<Rectangle, Enemy>();
	private static final int  width = 800;
	private static final int  height = 600;
	
	Enemy mouseOverEnemy =null;
	
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
	    battleBackground = this.loadImage(Paths.get("").toAbsolutePath().toString()+"/battle.jpg");
		fightBackground = this.loadImage(Paths.get("").toAbsolutePath().toString()+"/battleFight.jpg");
		deathBackground = this.loadImage(Paths.get("").toAbsolutePath().toString()+"/battleDeath.jpg");
	}

	public void draw() {
		this.focused = true;
		if(game.getCurrentBattle() == null){
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
		}else{
			tint(255,255);
			Battle battle = game.getCurrentBattle();
			switch(battle.getPhase()){
			case START_BATTLE:
				damageY=0;
				image(battleBackground,0,0);
				startBattle(battle);
				break;
			case START_TURN:
				damageY=0;
				image(battleBackground,0,0);
				player_target(battle);
				break;
			case PLAYER_TARGETS:
				damageY=0;
				image(battleBackground,0,0);
				player_target(battle);
				break;
			case PLAYER_ATTACKS:
				image(fightBackground,0,0);
				fight(battle);
				break;
			case NPC_TARGETS:
				damageY=0;
				image(battleBackground,0,0);
				npc_target(battle);
				break;
			case NPC_ATTACKS:
				image(fightBackground,0,0);
				fight(battle);
				break;
			case BATTLE_WON:
				image(battleBackground,0,0);
				won(battle);
				break;
			case BATTLE_LOST:
				lost(battle);
				break;
			case TARGET_DEAD:
				image(deathBackground,0,0);
				targetDead(battle);
				break;
			}
		}
		
	}
	
	private void won(Battle battle) {
		int enemyNo =1;
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Victory!", width/2, 50);
		for(Hero h: battle.getHeros()){
			PImage eImg = h.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	private void lost(Battle battle) {
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Defeat!", width/2, 50);
		int enemyNo =1;
		for(Enemy e: battle.getEnemies()){
			if(battle.getCurrentTarget() == null){
				noTint();
			}
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	private void targetDead(Battle battle) {
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Killed!", width/2, 50);
		PImage eImg = battle.getCurrentTarget().getImage();
		Rectangle position = new Rectangle(width/2 - eImg.width/2, height - eImg.height, eImg.width, eImg.height);
		image(eImg,position.x ,position.y);
	}

	private void npc_target(Battle battle) {
		int enemyNo =1;
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Enemy is targeting your party!", width/2, 50);
		for(Hero e: battle.getHeros()){
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			if(e == battle.getCurrentTarget()){
				tint(255,255);
			}
			else{
				tint(255,128);
			}
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	private void startBattle(Battle battle) {
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("You are fighting "+battle.getEnemies().size()+" enemies!", width/2, 50);
		int enemyNo =1;
		for(Enemy e: battle.getEnemies()){
			if(battle.getCurrentTarget() == null){
				noTint();
			}
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			image(eImg,position.x ,position.y);
			enemyNo++;
		}
	}

	public void player_target(Battle battle){
		mouseOverEnemy();
		int enemyNo =1;
		textAlign(CENTER);
		super.textFont(font, 32);
		this.text("Select a target to attack", width/2, 50);
		for(Enemy e: battle.getEnemies()){
			PImage eImg = e.getImage();
			Rectangle position = new Rectangle(width - eImg.width*enemyNo, height - eImg.height, eImg.width, eImg.height);
			if(e == battle.getCurrentTarget()){
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
	
	public void fight(Battle battle){
		PImage eImg = battle.getCurrentTarget().getImage();
		PImage hImg = battle.getCurrentCombatant().getImage();
		Rectangle ePosition = new Rectangle(width/4*3 - eImg.width/2, height - eImg.height, eImg.width, eImg.height);
		image(eImg,ePosition.x ,ePosition.y);
		Rectangle hPosition = new Rectangle(width/4 - hImg.width/2, height - hImg.height, hImg.width, hImg.height);
		image(hImg,hPosition.x ,hPosition.y);
		if(battle.getDamage()>0){
			loseHP(eImg);
		}
	}
	
	public void loseHP(PImage eImg){
		Rectangle ePosition = new Rectangle(width/4*3 - eImg.width/2, height - eImg.height - damageY, eImg.width, eImg.height);
		
		text("-"+game.getCurrentBattle().getDamage(),ePosition.x ,ePosition.y);
		damageY += 10;
	}

	public void mousePressed() {
		if(game.getCurrentBattle().getPhase()==BattlePhase.PLAYER_TARGETS){
			Point mousePos = new Point(mouseX, mouseY);
			  for(Rectangle r:enemyPos.keySet()){
				  if(r.contains(mousePos)){
					  game.getCurrentBattle().setCurrentTarget(enemyPos.get(r));
					  game.getCurrentBattle().setPhase(BattlePhase.PLAYER_ATTACKS);
					  
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
