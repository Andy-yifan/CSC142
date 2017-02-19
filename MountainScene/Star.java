import java.awt.Color;
import uwcse.graphics.*;

/**
 * A star in a graphics window
 */

public class Star {

	// The graphics window the star belongs to
	private GWindow window;

	// The location of the center of the star
	private int x;

	private int y;

	// Scale of the drawing of the star
	private double scale;
	
	//  color of the star
	private Color color;
	
	// parts of star
	private Line line1,line2,line3,line4;

	/**
	 * Draws a star in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the center of the star
	 * @param y
	 *            the y coordinate of the center of the star
	 * @param scale
	 *            the scale of the drawing of the star
	 * @param window
	 *            the graphics window the star belongs to
	 */
	public Star(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		this.scale = scale;

		// Draw the star
		this.draw();
	}
	
	// makes the star twinkle
	public void twinkle(){
		// using Math.random to random the color
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		color = new Color(r, g, b);
		
		// erase the star
		eraseStar();
		
		// redraw the star
		draw();
		
	}
	
	// erases the star
	private void eraseStar(){
		window.remove(line1);
		window.remove(line2);
		window.remove(line3);
		window.remove(line4);
	}

	/**
	 * Draws this star (complete this method to make a better star)
	 */
	public void draw() {

		// Size of the star
		int starSize = (int) (this.scale * 10);
		// Draw the lines making up the star
		line1 = new Line(this.x, this.y - starSize / 2, this.x, this.y
				+ starSize / 2, color);
		line2 = new Line(this.x - starSize / 2, this.y, this.x + starSize
				/ 2, this.y, color);
		line3 = new Line(this.x - starSize / 4, this.y - starSize /4,this.x + starSize / 4, this.y + starSize /4,color);
		line4 = new Line(this.x + starSize / 4, this.y - starSize /4,this.x - starSize / 4, this.y + starSize /4,color);
		// draw the star
		this.window.add(line1);
		this.window.add(line2);
		this.window.add(line3);
		this.window.add(line4);
	}
}