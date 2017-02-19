import java.awt.Color;
import uwcse.graphics.*;

/**
 * A sailboat in a graphics window
 */

public class SailBoat {

	// The graphics window the boat belongs to
	private GWindow window;

	// The location of the boat
	private int x;

	private int y;

	// Scale of the drawing of the boat
	private double scale;
	
	// The part of Boat
	private Triangle leftFlag;
	private Triangle rightFlag;
	private Rectangle flagPole;
	private Polygon boat;
	
	// the direction of boat up or down
	private int direction;
	
	// possible directions 
	public static final int UP = 1; // the values are irrelevant
	public static final int DOWN = 2;
	
	// to change the scale
	private double factor = 1.05;
	
	
	
	

	/**
	 * Draws a sailboat in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the location of the boat
	 * @param y
	 *            the y coordinate of the location of the boat
	 * @param window
	 *            the graphics window where the boat is displayed
	 * @param window
	 *            the graphics window the boat belongs to
	 */
	public SailBoat(int x, int y, double scale,int direction, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.window = window;
		this.scale = scale;
		this.direction = direction;
		// Draw the boat
		this.draw();
	}
	// boat moves
	public void moveUpOrDown(int dx,int dy){
		// update the location
		if(direction==UP){
			y-=dy; // y=y-dy
		}else{
			y+=dy; // y=y+dy
		}
		// erase the current boat
		eraseBoat();
		// redraw the boat
		draw();
	}
	// changes the size of boat
	public void changeSize() {
		
		if (scale > 1) {
			factor = 1/1.005;
		}
		// change the scale
		scale *= factor;
		// erase boat
		eraseBoat();
		// redraw
		draw();
	}
	/**
	 * Erases the boat from the window
	 */
	private void eraseBoat(){
		window.remove(leftFlag);
		window.remove(rightFlag);
		window.remove(flagPole);
		window.remove(boat);
	}
	/**
	 * Draws this sailboat
	 */
	private void draw() {
		int height = (int)(50 * scale);
		int width = (int)(30 * scale);
		// the left flag
		leftFlag = new Triangle(x, y+height, x+width, y,x + width, y + height,
				Color.WHITE, true);
		//the right flag
		rightFlag = new Triangle(x+width+width/8, y, x+width+width/8, y+height,x+width*2+width/8, y+height,
				Color.RED, true);
		// the pole
		flagPole = new Rectangle(x+width,y,width/8,height+height/15,Color.BLACK,true);
		// boat of bottom
		boat = new Polygon(Color.YELLOW, true);
		boat.addPoint(x-width/2+width+2*width+width/8,y+height+height/15);
		boat.addPoint(x-width/2+width+2*width+width/8-width/3,y+height+height/15+height/3);
		boat.addPoint(x-width/2+width/3,y+height+height/15+height/3);
		boat.addPoint(x-width/2,y+height+height/15);
		
		// Other way to draw a boat
		
		//Rectangle boat = new Rectangle(x-width/2,y+height+height/15,width+2*width+width/8,height/3,Color.YELLOW,true);
		//Triangle leftTriangleBoat = new Triangle(x-width/2,y+height+height/15,
		//		x-width/2,y+height+height/15+height/3,
		//		x-width/2+width/3,y+height+height/15+height/3,Color.blue,true);
		//Triangle rightTruangleBoat = new Triangle(x-width/2+width+2*width+width/8,y+height+height/15,
		//		x-width/2+width+2*width+width/8-width/3,y+height+height/15+height/3,
		//		x-width/2+width+2*width+width/8,y+height+height/15+height/3,Color.blue,true);
		
		// draw the boat
		window.add(leftFlag);
		window.add(rightFlag);
		window.add(flagPole);
		window.add(boat);
		
		//window.add(boat);
		//window.add(leftTriangleBoat);
		//window.add(rightTruangleBoat);
	}
}
