package edu.sussex.tele.game.dice;

import java.util.Random;

public class D6{

	public static int roll(){
		Random rand = new Random();
		return rand.nextInt(5)+1;
	}
}
