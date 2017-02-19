import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;


public class Alien2 extends MovingObject {

	public static final int RADIUS = 6;

	// Number of lives in this Alien
	// When 0, this Alien is dead
	private int lives;
	
	/**
	 * Create an alien in the graphics window
	 * 
	 * @param window
	 *            the GWindow this Alien belongs to
	 * @param center
	 *            the center Point of this Alien
	 */
	public Alien2(GWindow window, Point center) {
		super(window, center);
		this.lives = 1;

		// Display this Alien
		this.draw();
	}

	/**
	 * The alien is being shot Decrement its number of lives and erase it from
	 * the graphics window if it is dead.
	 */
	public void isShot() {
		lives--;
		if (lives ==0){
			erase();
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
		if(center.y % 3 == 0){
			center.x += rnd.nextInt(20) ;
		}else if (center.y % 3 == 1){
			center.x -= rnd.nextInt(20) ;
		}else if(center.y % 3 == 2){
			center.x -= rnd.nextInt(20) ;
		}
		
		erase();
		draw();
	
		// or move the alien preserving the z order
//		for (Shape sh: shapes){
//			sh.moveBy(0, 10);
//			
//		}
	}

	/**
	 * Display this Alien in the graphics window
	 */
	protected void draw() {
		
		// Graphics elements for the display of this Alien
		// A circle on L
		this.shapes = new Shape[3];
		this.shapes[0] = new Line(this.center.x -  RADIUS, this.center.y - 
				RADIUS, this.center.x -  RADIUS, this.center.y + RADIUS, Color.RED);
		this.shapes[1] = new Line(this.center.x -  RADIUS, this.center.y + RADIUS, 
				this.center.x +  RADIUS, this.center.y +
				RADIUS, Color.RED);
		this.shapes[2] = new Oval(this.center.x- 3*RADIUS+4 , this.center.y- 2*RADIUS
				, 4* RADIUS, 4* RADIUS, Color.RED, false);

		for (int i = 0; i < this.shapes.length; i++)
			this.window.add(this.shapes[i]);

		// Bounding box of this Alien
		this.boundingBox = new Rectangle(this.center.x- 2*RADIUS , this.center.y
				-2*RADIUS, 4* RADIUS, 4* RADIUS);

		this.window.doRepaint();
	}
	
}
