package edu.sussex.tele.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CharacterImagePreview extends JPanel {

	BufferedImage image;
	String imagePath;
	public void setImage(String path){
		try {
			imagePath = path;
			image = ImageIO.read(new File(path));
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		if(image != null){
			int imgW = image.getWidth();
			int imgH = image.getHeight();
			float ratio = (float) (300.0 / imgH);
			g.drawImage(image, 0, 0, (int)(ratio*imgW), (int)(ratio*imgH), 0, 0, imgW, imgH, null);
		}
	}

	public String getImagePath() {
		return imagePath;
	}
	
}
