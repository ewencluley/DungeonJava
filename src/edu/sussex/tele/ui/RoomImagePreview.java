package edu.sussex.tele.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RoomImagePreview extends JPanel {

	BufferedImage image;
	String imagePath;
	public void setImage(String path){
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		if(image != null){
			g.drawImage(image, 0, 0, 200, 160, 0, 0, 800, 600, null);
		}
	}
	
}
