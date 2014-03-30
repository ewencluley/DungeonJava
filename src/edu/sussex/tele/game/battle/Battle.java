package edu.sussex.tele.game.battle;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;
import edu.sussex.tele.game.characters.Character;

import java.util.Comparator;

public class Battle{

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Hero> heros = new ArrayList<Hero>();
	
	PriorityQueue<Character> combatants = new PriorityQueue<Character>(50, new InitiativeComparator());
	
	Hero currentHero;
	Enemy currentEnemy;
	private Character currentTarget;
	private Character currentCombatant;
	
	boolean battleInProgress = true;
	BattlePhase phase = BattlePhase.START_BATTLE;
	
	private int damage = 0;
	public ArrayList<Enemy> returnEnemies =null;
	public ArrayList<Hero> returnHeros =null;
	
	
	public BattlePhase getPhase() {
		return phase;
	}

	public void setPhase(BattlePhase phase) {
		this.phase = phase;
	}

	public Battle(ArrayList<Enemy> enemies, ArrayList<Hero> heros) {
		this.enemies.addAll(enemies);
		this.heros.addAll(heros);
		combatants.addAll(heros);combatants.addAll(enemies);
	}
	
	public void run() {
		try {
			Thread.sleep(2000);//wait to display battle intro screen for 2 sec
			Random random = new Random();
			while(battleInProgress){
				while(!combatants.isEmpty() && !enemies.isEmpty() && !heros.isEmpty()){
					phase = BattlePhase.START_TURN;
					currentTarget = null;
					currentCombatant = combatants.poll();
					if(currentCombatant instanceof Enemy){
						phase = BattlePhase.NPC_TARGETS;
						int heroToAttack = (int) random.nextInt(heros.size()-1);
						Thread.sleep(1000);
						currentTarget = heros.get(heroToAttack);
						Thread.sleep(1000);
						phase = BattlePhase.NPC_ATTACKS;
						Thread.sleep(500);
						//boolean hits = 
						damage = currentCombatant.strength +  ((currentCombatant.weapon != null)? currentCombatant.weapon.damage : 0);
						Thread.sleep(2000);
						currentTarget.hp -= damage;
						damage =0;
					}else{
						phase = BattlePhase.PLAYER_TARGETS;
						while(currentTarget == null){
							Thread.sleep(10);
						}
						phase = BattlePhase.PLAYER_ATTACKS;
						Thread.sleep(500);
						//boolean hits = 
						damage = currentCombatant.strength +  ((currentCombatant.weapon != null)? currentCombatant.weapon.damage : 0);
						Thread.sleep(2000);
						currentTarget.hp -= damage;
						damage =0;
					}
					//resolve combat
					if(currentTarget.hp <=0){
						phase = BattlePhase.TARGET_DEAD;
						heros.remove(currentTarget);
						enemies.remove(currentTarget);
						combatants.remove(currentTarget);
						Thread.sleep(1500);
					}
				}
				if(!heros.isEmpty() && !enemies.isEmpty()){
					combatants.addAll(heros);combatants.addAll(enemies);
				}else if(heros.isEmpty()){
					battleInProgress = false;
					phase = BattlePhase.BATTLE_LOST;
					Thread.sleep(1500);
					returnHeros = heros;
					returnEnemies = new ArrayList<Enemy>();
				}else if(enemies.isEmpty()){
					battleInProgress = false;
					phase = BattlePhase.BATTLE_WON;
					Thread.sleep(1500);
					returnHeros = heros;
					returnEnemies = enemies;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void setCurrentTarget(Enemy enemy) {
		currentTarget = enemy;
	}

	public Character getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(Character currentTarget) {
		this.currentTarget = currentTarget;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Hero> getHeros() {
		return heros;
	}

	public void setHeros(ArrayList<Hero> heros) {
		this.heros = heros;
	}

	public Character getCurrentCombatant() {
		return currentCombatant;
	}

	public void setCurrentCombatant(Character currentCombatant) {
		this.currentCombatant = currentCombatant;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	private class InitiativeComparator implements Comparator<Character>
	{
	    @Override
	    public int compare(Character x, Character y)
	    {
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        if (x.initiative < y.initiative)
	        {
	            return -1;
	        }
	        if (x.initiative > y.initiative)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}

	
}
