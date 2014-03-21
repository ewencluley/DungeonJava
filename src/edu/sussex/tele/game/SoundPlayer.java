package edu.sussex.tele.game;
import java.io.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	
	  public static void play(String path) 
	  {
	    Media hit = new Media(path);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	  }
}
