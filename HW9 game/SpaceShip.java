import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import uwcse.graphics.GWindow;
import uwcse.graphics.Line;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Shape;
import uwcse.graphics.TextShape;
import uwcse.graphics.Triangle;
import uwcse.io.Sound;

/**
 * The space ship
 */
public class SpaceShip extends MovingObject {
	/** Height of a space ship */
	public static final int HEIGHT = 40;

	/** Width of a space ship */
	public static final int WIDTH = 20;

	/** Is the space ship shooting with its laser? */
	private boolean isShooting;
	//set the score
	public static int score;
	
	private static Sound explosion = new Sound("12.wav");

	/**
	 * Construct this SpaceShip
	 */
	public SpaceShip(GWindow window, Point center) {
		super(window, center);
		this.direction = MovingObject.LEFT;

		// Draw this SpaceShip
		this.draw();
	}

	/**
	 * Move this SpaceShip. The space ship should be constantly moving. Select a
	 * new direction if the space ship can't move in the current direction of
	 * motion.
	 */
	public void move() {
		// A space ship moves left or right
		if (this.direction != MovingObject.LEFT
				&& this.direction != MovingObject.RIGHT
				&& this.direction != MovingObject.UP
				&& this.direction != MovingObject.DOWN)
			throw new IllegalArgumentException("Invalid space ship direction");

		// move this SpaceShip
		boolean isMoveOK;
		// Distance covered by the space ship in one step
		int step = this.boundingBox.getWidth() / 2;

		do {
			int x = this.center.x;
			int y = this.center.y;
			switch (this.direction) {
			case LEFT:
				x -= step;
				break;
			case RIGHT:
				x += step;
				break;
			case UP:
				y -= step;
				break;
			case DOWN:
				y += step;
				break;
			}

			// Is the new position in the window?
			if (x >= this.window.getWindowWidth()){
					isMoveOK = false;
					this.center.x= 0;
				} else if (x <= 0) {
					isMoveOK = false;
					this.center.x= window.getWindowWidth();
				} else if (y >= this.window.getWindowHeight()){
					isMoveOK = false;
					this.center.y= 1;
			    } else if (y <= 0) {
					isMoveOK = false;
					this.center.y= window.getWindowHeight();
				
				
				} else {
					isMoveOK = false;
					this.center.x = x;
					this.center.y = y;
				}
			} while (isMoveOK);

		// Show the new location of this SpaceShip
		this.erase();
		this.draw();
	}

	/**
	 * Shoot at the aliens If an alien is hit, decrease its number of lives or
	 * remove it from the array list if it is dead.
	 * 
	 * @param aliens
	 *            the ArrayList of aliens
	 */
	public void shoot(ArrayList<Alien> aliens, ArrayList<Alien2> alien, ArrayList<Alien3> alien3) {
		explosion.play();
		this.isShooting = true;
		// is alien shot?
		for(int i =0; i< aliens.size(); i ++){
			Alien a = aliens.get(i);
			Rectangle box = a.getBoundingBox();
			if(center.x >= box.getX()&& center.x <=box.getX()+box.getWidth()){
				a.isShot();
				score +=10;
			}
		}
		for(int n =0; n< alien.size(); n ++){
			Alien2 b = alien.get(n);
			Rectangle box2 = b.getBoundingBox();
			if(center.x >= box2.getX()&& center.x <=box2.getX()+box2.getWidth()){
				b.isShot();
				score += 100;
			}
		}
		if(alien3 != null && alien3.size() != 0){
			Alien3 a = alien3.get(0);
			Rectangle box = a.getBoundingBox();
			if (center.x >= box.getX()
					&& center.x <= box.getX() + box.getWidth()) {
				a.isShot();
				score += 300;
			}
		}
	}

	/**
	 * Draw this SpaceShip in the graphics window
	 */
	protected void draw() {
		this.shapes = new Shape[6];

		// Body of the space ship
		Rectangle body = new Rectangle(this.center.x - SpaceShip.WIDTH / 2,
				this.center.y - SpaceShip.HEIGHT / 2, SpaceShip.WIDTH,
				SpaceShip.HEIGHT, Color.cyan, true);

		this.shapes[0] = body;

		// Cone on top
		int x1 = body.getX();
		int y1 = body.getY();
		int x2 = x1 + body.getWidth();
		int y2 = y1;
		int x3 = body.getCenterX();
		int y3 = y1 - body.getWidth();
		this.shapes[1] = new Triangle(x1, y1, x2, y2, x3, y3, Color.pink, true);
		// Show the laser beam if the rocket is shooting
		if (this.isShooting) {
			this.shapes[4] = new Line(x3, y3, x3, 0, Color.yellow);
			this.isShooting = false;
		}

		// Wings on the sides
		x1 = body.getX();
		y1 = body.getY() + body.getHeight();
		x2 = body.getX() - body.getWidth() / 2;
		y2 = y1;
		x3 = x1;
		y3 = y1 - body.getWidth() / 2;
		this.shapes[2] = new Triangle(x1, y1, x2, y2, x3, y3, Color.red, true);
		x1 = body.getX() + body.getWidth();
		x2 = x1 + body.getWidth() / 2;
		x3 = x1;
		this.shapes[3] = new Triangle(x1, y1, x2, y2, x3, y3, Color.red, true);

		// The bounding box of this SpaceShip
		this.boundingBox = this.shapes[0].getBoundingBox();
		
		this.shapes[5] = new TextShape("Socre:" + String.valueOf(score),10,10,Color.WHITE);

		// Put everything in the window
		for (int i = 0; i < this.shapes.length; i++)
			if (this.shapes[i] != null)
				this.window.add(this.shapes[i]);

		this.window.doRepaint();
	}
}
