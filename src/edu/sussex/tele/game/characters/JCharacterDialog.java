package edu.sussex.tele.game.characters;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

import edu.sussex.tele.ui.CharacterImagePreview;
import edu.sussex.tele.ui.GUI;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCharacterDialog extends JDialog {
	private JTextField textField;
	private CharacterImagePreview characterImagePreview;
	private Hero hero = null;
	public Hero getHero() {
		return hero;
	}
	public JCharacterDialog() {
		setResizable(false);
		setModal(true);
		
		JPanel panel = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		characterImagePreview = new CharacterImagePreview();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(characterImagePreview, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(characterImagePreview, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JButton btnNewButton_1 = new JButton("Create!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hero = new Hero(characterImagePreview.getImagePath());
				JCharacterDialog.this.setVisible(false);
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Change Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int fileChooseResult = chooser.showOpenDialog(JCharacterDialog.this);
				if(fileChooseResult == JFileChooser.APPROVE_OPTION){
					characterImagePreview.setImage(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		GroupLayout gl_characterImagePreview = new GroupLayout(characterImagePreview);
		gl_characterImagePreview.setHorizontalGroup(
			gl_characterImagePreview.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_characterImagePreview.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_characterImagePreview.setVerticalGroup(
			gl_characterImagePreview.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_characterImagePreview.createSequentialGroup()
					.addContainerGap(232, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		characterImagePreview.setLayout(gl_characterImagePreview);
		
		JLabel lblName = new JLabel("Name:");
		panel.add(lblName);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(30);
		getContentPane().setLayout(groupLayout);
		this.setSize(500, 432);
		this.setVisible(true);
	}
	public CharacterImagePreview getCharacterImagePreview() {
		return characterImagePreview;
	}
}
