package edu.sussex.tele.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class CodePanel extends JTextPane {

	private HashMap<Rectangle, Integer> clickToAdds = new HashMap<Rectangle, Integer>();
	private BufferedImage addPathImg;
	
	public CodePanel(){
		try {
			addPathImg = ImageIO.read(new File("editor\\addImg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AddClickListener mouseListener = new AddClickListener();
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		super.getDocument().addDocumentListener(new EditListener());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(Rectangle rect: clickToAdds.keySet()){
			g.setColor(Color.GREEN);
			//g.fillRect(rect.x, rect.y, 2, rect.height);
			g.drawImage(addPathImg, rect.x, rect.y, null);
			g.setColor(Color.BLACK);
			g.setFont(Font.decode("Arial-bold-10"));
			g.drawString("+ insert file", rect.x+20, rect.y+12);
			
		}
		
	}
	
	private void findClickToAdds(){
		try {
			String text = this.getDocument().getText(0, this.getDocument().getLength());
			Pattern pattern = Pattern.compile("((new Enemy)|music|sound|(new Hero))\\((\\s)*\\)");
		    Matcher matcher = pattern.matcher(text);
		    // Check all occurrences
		    while (matcher.find()) {
		    	final int index = matcher.end();
		    	Rectangle rect = this.modelToView(index-1);
				Rectangle diaplayRect = new Rectangle(rect.x-3, rect.y+3, 100, rect.height);
				clickToAdds.put(diaplayRect, index-1);
		    }
		    this.repaint();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class EditListener implements DocumentListener{

		private void textValueChanged() {
			clickToAdds = new HashMap<Rectangle, Integer>();
			findClickToAdds();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			textValueChanged();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			textValueChanged();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			textValueChanged();
		}
		
	}
	
	private class AddClickListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			for(Rectangle r:clickToAdds.keySet()){
				if(r.contains(new Point(e.getX(), e.getY()))){
					try {
						JFileChooser fileChooser = new JFileChooser();
						int fileChooseResult = fileChooser.showOpenDialog(CodePanel.this);
						if(fileChooseResult == JFileChooser.APPROVE_OPTION){
							CodePanel.this.getDocument().insertString(clickToAdds.get(r), "\""+fileChooser.getSelectedFile().getAbsolutePath().replace("\\", "\\\\")+"\"", null);
						}
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e){
			boolean hand = false;
			for(Rectangle r:clickToAdds.keySet()){
				if(r.contains(new Point(e.getX(), e.getY()))){
					hand = true;
				}
			}
			if(hand){
				CodePanel.this.setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}else{
				CodePanel.this.setCursor (Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
			}
		}
	}
}
