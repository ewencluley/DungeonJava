package edu.sussex.tele.ui;

import processing.core.PApplet;
import processing.video.*;

public class VideoPanel extends PApplet {

	Movie tutorialMov;

	public void setup() {
	  size(200, 200);
	  tutorialMov = new Movie(this, "transit.mov");
	  //myMovie.loop();
	}
	
	public void draw() {
	  image(tutorialMov, 0,0);
	}
	
	// Called every time a new frame is available to read
	void movieEvent(Movie m) {
	  m.read();
	}


	
}
