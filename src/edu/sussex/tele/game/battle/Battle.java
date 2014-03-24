package edu.sussex.tele.game.battle;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;
import edu.sussex.tele.game.characters.Character;

import java.util.Comparator;

public class Battle implements Runnable{

	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Hero> heros = new ArrayList<Hero>();
	
	PriorityQueue<Character> combatants = new PriorityQueue<Character>(50, new InitiativeComparator());
	
	Hero currentHero;
	Enemy currentEnemy;
	Character currentTarget;
	Character currentCombatant;
	
	boolean battleInProgress = true;
	BattlePhase phase = BattlePhase.START_BATTLE;
	
	int damage = 0;
	
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
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);//wait to display battle intro screen for 2 sec
			Random random = new Random();
			while(battleInProgress){
				while(!combatants.isEmpty()){
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
					}
				}
				if(!heros.isEmpty() && !enemies.isEmpty()){
					combatants.addAll(heros);combatants.addAll(enemies);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setupBattleTurn(){
		//currentHero = heros.poll();
		//currentEnemy = enemies.poll();
	}
	
	public void setCurrentTarget(Enemy enemy) {
		currentTarget = enemy;
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
