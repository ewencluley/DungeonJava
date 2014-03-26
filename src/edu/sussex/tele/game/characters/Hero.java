package edu.sussex.tele.game.characters;

public class Hero extends Character{

	public static Hero PlayerMakesHero(){
		JCharacterDialog charDialog = new JCharacterDialog();
		Hero character = charDialog.getHero();
		return character;
	}
	public Hero(String imagePath) {
		super(imagePath);
	}

}
