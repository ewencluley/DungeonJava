package edu.sussex.tele.game;

import java.util.ArrayList;

import javax.swing.JFrame;

import processing.core.PApplet;
import edu.sussex.tele.game.characters.Enemy;
import edu.sussex.tele.game.characters.Hero;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Battle extends JFrame{

	
	ArrayList<Hero> heros = new ArrayList<Hero>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public Battle(ArrayList<Enemy> enemies2) {
		super("Fight!");
		setResizable(false);
		this.setSize(420, 440);
		BattleGraphics battleGraphics = new BattleGraphics();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(battleGraphics, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addGap(4))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(battleGraphics, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
		);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel.add(btnNewButton_3);
		getContentPane().setLayout(groupLayout);
		battleGraphics.init();
		enemies = enemies2;
		setVisible(true);
	}
}
