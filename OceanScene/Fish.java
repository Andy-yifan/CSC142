import java.awt.Color;
import uwcse.graphics.*;

/**
 * A fish in a graphics window
 */

public class Fish {

	// The graphics window the fish belongs to
	private GWindow window;

	// The location of the fish
	private int x;

	private int y;

	// Scale of the drawing of the fish
	private double scale;

	// the direction the fish is swimming
	private int direction;

	// the shapes making up the drawing of the fish
	private Oval body;
	private Triangle tail;

	// color of the fish
	private Color color;

	// possible directions
	// final means constant
	// static means only one copy (stored in the class directly,
	// not stored in every instance of the class)
	// Constant names are all uppercase
	// The value of the constant must be assigned on the line
	// of declaration of the constant.
	// To use a constant outside of the class, write
	// name of the class. name of the constant
	// e.g. Fish.LEFT_MOVING
	public static final int LEFT_MOVING = -5; // the values are irrelevant
	public static final int RIGHT_MOVING = 16;

	/**
	 * Draws a Fish in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the fish
	 * @param y
	 *            the y coordinate of the location of the fish
	 * @param scale
	 *            the scale of the drawing of the fish
	 * @param direction
	 *            the direction the fish is swimming
	 * @param window
	 *            the graphics window the fish belongs to
	 */
	public Fish(int x, int y, double scale, int direction, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		this.direction = direction;
		// color of the fish
		// random color
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		color = new Color(r, g, b);
		// Draw the fish
		this.draw();
	}

	/**
	 * Moves the fish
	 */
	public void swim() {
		// erase the current fish
		eraseFish();
		// update the location
		if (direction == RIGHT_MOVING) {
			x += 5; // same as x = x + 5;
		} else {
			x -= 5; // same as x = x - 5;
		}
		// is the fish turning around?
		if (x > window.getWindowWidth()) {
			direction = LEFT_MOVING;
		}
		if (x < 0) {
			direction = RIGHT_MOVING;
		}
		// redraw the fish
		draw();
	}

	/**
	 * Erases the fish from the window
	 */

	private void eraseFish() {
		window.remove(body);
		window.remove(tail);
	}

	/**
	 * Draws this fish
	 */
	private void draw() {
		
		// scale the fish
		int h = (int) (15 * scale);
		int w = (int) (30 * scale);
		int t = (int) (10 * scale);
		
		//draw the body
		body = new Oval(x - w / 2, y - h / 2, w, h, color, true);
		window.add(body);
		// set the direction
		if (direction == RIGHT_MOVING) { // right moving
			tail = new Triangle(x - w / 2, y, x - w / 2 - t, y - h / 2, x - w
					/ 2 - t, y + h / 2, color, true);
			window.add(tail);
		} else { // left moving
			tail = new Triangle(x + w / 2, y, x + w / 2 + t, y - h / 2, x + w
					/ 2 + t, y + h / 2, color, true);
			// add to the window
			window.add(tail);
		}
	}
}