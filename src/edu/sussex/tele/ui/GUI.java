package edu.sussex.tele.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.tools.ToolProvider;

import edu.sussex.tele.compiler.AgentClassLoader;
import edu.sussex.tele.compiler.BuildGame;
import edu.sussex.tele.compiler.ClassInfo;
import edu.sussex.tele.compiler.Javac;
import edu.sussex.tele.compiler.SyntaxErrorException;
import edu.sussex.tele.game.Game;
import edu.sussex.tele.game.GameGUI;
import edu.sussex.tele.game.Map;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JEditorPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_2;
	private JTextField roomName;
	private RoomImagePreview roomImagePreview;
	private MapViewer mapViewer;
	private CodePanel enterRoomScript;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getEnterRoomScript(){
		return enterRoomScript.getText();
	}
	
	public String getExitRoomScript(){
		return "";
		//return exitRoomScript.getText();
	}
	
	public void setEnterRoomScript(String code){
		enterRoomScript.setText(code);
	}
	
	public void setExitRoomScript(String code){
		//exitRoomScript.setText(code);
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
	            e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		mapViewer = new MapViewer(this);
		
		panel_2 = new JPanel();
		
		JButton btnChangeImage = new JButton("Change Picture");
		btnChangeImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(Paths.get("").toAbsolutePath().toString());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
				chooser.setFileFilter(filter);
				int fileChooseResult = chooser.showOpenDialog(GUI.this);
				if(fileChooseResult == JFileChooser.APPROVE_OPTION){
					mapViewer.setCurrentRoomImage(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		roomImagePreview = new RoomImagePreview();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(mapViewer, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(76)
									.addComponent(btnChangeImage))
								.addComponent(roomImagePreview, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(mapViewer, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(roomImagePreview, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnChangeImage))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		VideoPanel vid_panel = new VideoPanel();
		tabbedPane.addTab("Video Tutorial", null, vid_panel, null);
		
		JEditorPane editorPane = new JEditorPane();
		tabbedPane.addTab("Hints", null, editorPane, null);
		java.net.URL url = null;
		try {
			url = new File("hints.html").toURI().toURL();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

;
		try {
			editorPane.setPage(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		enterRoomScript = new CodePanel();
		//TextLineNumber lineNumbers = new TextLineNumber(enterRoomScript);
		//scrollPane.setRowHeaderView( lineNumbers );
		
		scrollPane.setViewportView(enterRoomScript);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapViewer.deleteCurrentRoom();
			}
		});
		panel_2.add(btnDeleteRoom);
		
		JButton btnMakeStart = new JButton("Make this room the start Room");
		btnMakeStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapViewer.makeCurrentRoomStart();
			}
		});
		panel_2.add(btnMakeStart);
		
		JButton btnMakeEnd = new JButton("Make this room the end Room");
		btnMakeEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapViewer.makeCurrentRoomEnd();
			}
		});
		panel_2.add(btnMakeEnd);
		
		JPanel panel_3 = new JPanel();
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.LINE_AXIS));
		
		JLabel lblRoomName = new JLabel("Room Name");
		panel_3.add(lblRoomName);
		
		roomName = new JTextField();
		panel_3.add(roomName);
		roomName.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_4.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapViewer.save();
				if(mapViewer.startSet){
					try {
						Map theMap = BuildGame.buildMap(mapViewer);
						Game theGame = new Game(theMap);
						GameGUI gameGUI = new GameGUI(theGame);
						theGame.setGameGUI(gameGUI);
						gameGUI.runGame();
					} catch (SyntaxErrorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					JOptionPane.showMessageDialog(GUI.this, "Your dungeon must have a start room to be playable, please set one.");
				}
			}
		});
		panel.add(btnRun);
		contentPane.setLayout(gl_contentPane);
		enterRoomScript.setEnabled(false);
	}
	
	public void setRoomImage(String path){
		roomImagePreview.setImage(path);
	}
	public JTextField getRoomName() {
		return roomName;
	}
	public RoomImagePreview getRoomImagePreview() {
		return roomImagePreview;
	}
	public MapViewer getMapViewer() {
		return mapViewer;
	}

	public void setRoomName(String roomName2) {
		roomName.setText(roomName2);
	}
	public CodePanel getCodePanel() {
		return enterRoomScript;
	}
}
