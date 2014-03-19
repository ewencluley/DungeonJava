package edu.sussex.tele.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import processing.core.PApplet;
import processing.core.PImage;
import edu.sussex.tele.ui.GraphicsPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameGUI extends JFrame{

	GraphicsPanel graphicsPanel;
	Game theGame;
	
	public GameGUI(Game game){
		theGame = game;
		this.setTitle("My Game");
		this.setSize(800, 600);
		
		JPanel panel = new JPanel();
		graphicsPanel = new GraphicsPanel();
		panel.add(graphicsPanel);
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(568, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnUp = new JButton("Forward");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.move("FORWARD");
				
			}
		});
		panel_2.add(btnUp, BorderLayout.NORTH);
		
		JButton btnLeft = new JButton("Left");
		panel_2.add(btnLeft, BorderLayout.WEST);
		
		JButton btnRight = new JButton("Right");
		panel_2.add(btnRight, BorderLayout.EAST);
		
		JButton btnBackwards = new JButton("Backwards");
		panel_2.add(btnBackwards, BorderLayout.SOUTH);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		graphicsPanel.init();
		this.setVisible(true);
		
	}
	
	public void moveToRoom(String roomName){
		
			graphicsPanel.setBackgroundImg(roomName);
	}
	
	public void runGame(){
		new Thread(theGame).start();
	}
	
	protected void addCharacterToCurrentRoom(Character character){
		graphicsPanel.add(character);
		graphicsPanel.repaint();
	}
}
