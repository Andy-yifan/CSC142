import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;

/**
 * The representation and display of an Alien
 */

public class Alien3 extends MovingObject {
	// Size of an Alien
	public static  int RADIUS = 30;

	// Number of lives in this Alien
	// When 0, this Alien is dead
	private int lives;

	private static Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.orange, Color.CYAN};

	/**
	 * Create an alien in the graphics window
	 * 
	 * @param window
	 *            the GWindow this Alien belongs to
	 * @param center
	 *            the center Point of this Alien
	 */
	public Alien3(GWindow window, Point center) {
		super(window, center);
		this.lives = 30 ;

		// Display this Alien
		this.draw();
	}

	

	



	/**
	 * The alien is being shot Decrement its number of lives and erase it from
	 * the graphics window if it is dead.
	 */
	public void isShot() {
		lives--;
		RADIUS = RADIUS-1;
		if (lives == 0) {
			erase();
		}
		if(RADIUS <2){
			RADIUS = 2;
		}
	}

	/**
	 * Is this Alien dead?
	 */
	public boolean isDead() {
		return this.lives == 0;
	}

	/**
	 * Return the location of this Alien
	 */
	public Point getLocation() {
		return this.center;
	}

	/**
	 * Move this Alien As a start make all of the aliens move downward. If an
	 * alien reaches the bottom of the screen, it reappears at the top.
	 */
	public void move() {
		Random rnd = new Random();
		// update the location
		center.y += rnd.nextInt(20) ;
		if(center.y % 2 == 0){
			center.x += rnd.nextInt(20) ;
		}else if (center.y % 2 == 1){
			center.x -= rnd.nextInt(20) ;
		}
		// if past the bottom of the window, bring it back to the
		// top
		if (center.y > window.getWindowHeight()) {
			center.y = 0;
			if(lives >= 55){
				lives -= 4;
			}
			lives+= 4;
		}
		if (center.x > window.getWindowWidth()){
			center.x = 10;
		}else if (center.x < 0){
			center.x =  window.getWindowWidth() - 10;
		}

		// to move the alien
		erase();
		draw();
	}

	/**
	 * Display this Alien in the graphics window
	 */
	protected void draw() {
		// pick the color (according to the number of lives left)
		
		Color color = colors[lives/10];

		// Graphics elements for the display of this Alien
		// A circle on top of an X
		this.shapes = new Shape[2];
		this.shapes[0] = new Oval(this.center.x - 2 * RADIUS, this.center.y
				- RADIUS, 4 * RADIUS, 2 * RADIUS, color, false);
		this.shapes[1] = new Oval(this.center.x - 4 * RADIUS, this.center.y
				, 8 * RADIUS, RADIUS, color, true);

		for (int i = 0; i < this.shapes.length; i++)
			this.window.add(this.shapes[i]);

		// Bounding box of this Alien
		this.boundingBox = new Rectangle(this.center.x - 4 * RADIUS,
				this.center.y - RADIUS, 8 * RADIUS, 2 * RADIUS);

		this.window.doRepaint();
	}
}