package edu.sussex.tele.game;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.sussex.tele.ui.GraphicsPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import edu.sussex.tele.ui.CharacterImagePreview;

public class GameGUI extends JFrame{

	GraphicsPanel graphicsPanel;
	Game theGame;
	private CharacterImagePreview characterImagePreview;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnBackwards;
	
	public GameGUI(Game game){
		setResizable(false);
		theGame = game;
		this.setTitle("My Game");
		this.setSize(816, 755);
		
		JPanel panel = new JPanel();
		graphicsPanel = new GraphicsPanel(theGame);
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
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel panel_2 = new JPanel();
		
		characterImagePreview = new CharacterImagePreview();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(characterImagePreview, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(496, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(characterImagePreview, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		btnUp = new JButton("Forward");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.move("FORWARD");
				
			}
		});
		panel_2.add(btnUp, BorderLayout.NORTH);
		
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.move("LEFT");
			}
		});
		panel_2.add(btnLeft, BorderLayout.WEST);
		
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.move("RIGHT");
			}
		});
		panel_2.add(btnRight, BorderLayout.EAST);
		
		btnBackwards = new JButton("Backwards");
		btnBackwards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theGame.move("BACKWARD");
			}
		});
		panel_2.add(btnBackwards, BorderLayout.SOUTH);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		graphicsPanel.init();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.pack();
	}
	
	public void moveToRoom(String roomName){
		
			graphicsPanel.setBackgroundImg(roomName);
	}
	
	public void runGame(){
		new Thread(theGame).start();
	}
	protected CharacterImagePreview getCharacterImagePreview() {
		return characterImagePreview;
	}
	public JButton getBtnLeft() {
		return btnLeft;
	}
	public JButton getBtnUp() {
		return btnUp;
	}
	public JButton getBtnRight() {
		return btnRight;
	}
	public JButton getBtnBackwards() {
		return btnBackwards;
	}
	
	public void disableControlls(){
		btnRight.setEnabled(false);
		btnUp.setEnabled(false);
		btnBackwards.setEnabled(false);
		btnLeft.setEnabled(false);
	}
	
	public void enableControlls(){
		btnRight.setEnabled(true);
		btnUp.setEnabled(true);
		btnBackwards.setEnabled(true);
		btnLeft.setEnabled(true);
	}
}
