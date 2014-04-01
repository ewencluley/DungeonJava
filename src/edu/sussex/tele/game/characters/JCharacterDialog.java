package edu.sussex.tele.game.characters;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;

import edu.sussex.tele.ui.CharacterImagePreview;
import edu.sussex.tele.ui.GUI;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.nio.file.Paths;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class JCharacterDialog extends JDialog {
	private JTextField name;
	private CharacterImagePreview characterImagePreview;
	private Hero hero = null;
	private JTextField strength;
	private JTextField initiative;
	private JTextField defence;
	private JTextField totPoints;
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
					.addComponent(characterImagePreview, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(characterImagePreview, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(107)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel lblPointsToAllocate = new JLabel("Points to allocate");
		panel_6.add(lblPointsToAllocate);
		
		totPoints = new JTextField();
		totPoints.setHorizontalAlignment(SwingConstants.CENTER);
		totPoints.setText("10");
		totPoints.setColumns(10);
		panel_6.add(totPoints);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Strength");
		panel_3.add(lblNewLabel);
		
		strength = new JTextField();
		panel_3.add(strength);
		strength.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("-");
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("+");
		panel_3.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Initiative");
		panel_4.add(lblNewLabel_1);
		
		initiative = new JTextField();
		panel_4.add(initiative);
		initiative.setColumns(10);
		
		JButton button_1 = new JButton("-");
		panel_4.add(button_1);
		
		JButton button = new JButton("+");
		panel_4.add(button);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Defence");
		panel_5.add(lblNewLabel_2);
		
		defence = new JTextField();
		panel_5.add(defence);
		defence.setColumns(10);
		
		JButton button_3 = new JButton("-");
		panel_5.add(button_3);
		
		JButton button_2 = new JButton("+");
		panel_5.add(button_2);
		
		JButton btnNewButton_1 = new JButton("Create!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hero = new Hero(characterImagePreview.getImagePath());
				hero.strength = Integer.parseInt(strength.getText());
				hero.defence = Integer.parseInt(defence.getText());
				hero.initiative = Integer.parseInt(initiative.getText());
				hero.name = name.getText();
				JCharacterDialog.this.setVisible(false);
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Change Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
				chooser.setFileFilter(filter);
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
		
		name = new JTextField();
		panel.add(name);
		name.setColumns(30);
		getContentPane().setLayout(groupLayout);
		this.setSize(500, 432);
		this.setVisible(true);
	}
	public CharacterImagePreview getCharacterImagePreview() {
		return characterImagePreview;
	}
	public JTextField getTotPoints() {
		return totPoints;
	}
}
