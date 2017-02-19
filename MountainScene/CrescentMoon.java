import java.awt.Color;
import uwcse.graphics.*;

public class CrescentMoon {

	// The graphics window the CrescentMoon belongs to
	private GWindow window;
	// The location of the CrescentMoon
	private int x;
	private int y;
	private Oval whiteOval,blackOval,smileOvalBlack,smileOvalWhite,smilingeyes,smilingeyes2;
	//private Color color;
	public CrescentMoon(int x, int y, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		// Draw the CrescentMoon
		this.drawWhiteOval();
		this.draw();
		
		
	}
	// make color change
//	public void twinkle(){
//		int r = (int) (Math.random() * 256);
//		int g = (int) (Math.random() * 256);
//		int b = (int) (Math.random() * 256);
//		color = new Color(r, g, b);
//		eraseMoon();
//		draw();
//	}
	
	// Makes the moon move
	public void doAction(int dx,int dy){
		// using moveBy
		x += dx; // same as x = x + dx;
		y += dy;
		
		// erase the moon
		eraseMoon();
		
		// redraw it
		draw();
	}    
	
	// erase the moon
	private void eraseMoon(){
		window.remove(smileOvalBlack);
		window.remove(smileOvalWhite);
		window.remove(blackOval);
		window.remove(smilingeyes);
		window.remove(smilingeyes2);
	}
	// draw the smiling moon
	private void draw() {
		// Draw two oval and yellow circle is on the right and down of black oval.
		// Draw a crescent moon
		smileOvalBlack = new Oval(x+10,y+20,30,30,Color.BLACK,true);
		smileOvalWhite = new Oval(x+17,y+18,30,30,Color.WHITE,true);
		blackOval = new Oval(x-15,y-15,60,60,Color.BLACK,true);
		// Making a smiling eyes
		smilingeyes = new Oval(x+40,y+20,10,10,Color.BLACK,true);
		smilingeyes2 = new Oval(x+40,y+22,10,10,Color.WHITE,true);
		
		//add the moon into the window
		window.add(smileOvalBlack);
		window.add(smileOvalWhite);
		window.add(smilingeyes);
		window.add(smilingeyes2);
		window.add(blackOval);
	}
	// draw the full moon
	private void drawWhiteOval(){
		whiteOval = new Oval(x,y,60,60,Color.WHITE,true);
		window.add(whiteOval);
	}
}