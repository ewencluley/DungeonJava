package edu.sussex.tele.ui;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MapViewer extends JPanel {
	
	RoomButton currentRoom;
	GUI gui;
	boolean startSet = false;
	public MapViewer(GUI gui) {
		this.gui = gui;
		setLayout(new GridLayout(10, 10, 0, 0));
		for(int y=0; y<10;y++){
			for(int x=0; x<10;x++){
				RoomButton btnNewButton = new RoomButton(x,y);
				btnNewButton.addActionListener(new MapButtonClickedListener());
				add(btnNewButton);
			}
		}
	}
	
	public class MapButtonClickedListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			RoomButton rb = (RoomButton)e.getSource();
			desectAll();
			rb.activate();
			rb.setSelected(true);
			//save the code
			save();
			currentRoom = rb;
			gui.setEnterRoomScript(currentRoom.getEnterRoomCode());
			gui.setExitRoomScript(currentRoom.getExitRoomCode());
			gui.setRoomImage(currentRoom.getRoomImage());
			gui.setRoomName(currentRoom.getRoomName());
			gui.getCodePanel().setEnabled(true);
			gui.repaint();
		}
	}

	public void setCurrentRoomImage(String path) {
		if(currentRoom != null){
			currentRoom.setRoomImage(path);
			gui.setRoomImage(path);
			gui.repaint();
		}
		
	}
	
	private void desectAll() {
		for(Component c:this.getComponents()){
			((RoomButton)c).setSelected(false);
		}
	}

	public void makeCurrentRoomStart(){
		for(Component button: this.getComponents()){
			((RoomButton) button).setStartRoom(false);
		}
		currentRoom.setStartRoom(true);
		currentRoom.setEndRoom(false);
		this.repaint();
		startSet = true;
	}
	
	public void makeCurrentRoomEnd(){
		for(Component button: this.getComponents()){
			((RoomButton) button).setEndRoom(false);
		}
		currentRoom.setEndRoom(true);
		currentRoom.setStartRoom(false);
		this.repaint();
	}

	public void deleteCurrentRoom() {
	
	}

	public void save() {
		if(currentRoom != null){
			currentRoom.setEnterRoomCode(gui.getEnterRoomScript());
			currentRoom.setExitRoomCode(gui.getExitRoomScript());
			currentRoom.setRoomName(gui.getRoomName().getText());
		}
	}

}
