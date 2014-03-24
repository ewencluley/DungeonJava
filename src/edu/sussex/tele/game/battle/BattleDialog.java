package edu.sussex.tele.game.battle;

import java.util.ArrayList;

import javax.swing.JFrame;

import processing.core.PApplet;
import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;

public class BattleDialog extends JDialog{

	Battle battle;
	
	public BattleDialog(JFrame owner, ArrayList<Enemy> enemies2, ArrayList<Hero> heros) {
		super(owner, "Fight!", true);
		setResizable(false);
		this.setSize(820, 740);
		
		startBattle(enemies2, heros);
		BattleGraphics battleGraphics = new BattleGraphics(battle);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(battleGraphics, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(battleGraphics, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
		);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton_3);
		getContentPane().setLayout(groupLayout);
		battleGraphics.init();
		this.setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	private void startBattle(ArrayList<Enemy> enemies, ArrayList<Hero> heros){
		battle = new Battle(enemies, heros);
		new Thread(battle).start();
	}
}