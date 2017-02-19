import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Triangle;

/**
 * A smiling face in a graphics window This is the solution of last week's lab.
 * Adapt it to add two methods: public void moveTo(int x, int y) to move the
 * face to (x,y) public void moveBy(int dx, int dy) to move the face by dx and
 * dy
 */

public class SmilingFace {

	// The graphics window
	private GWindow window;

	// Location of the face
	private int x, y;

	// Scale used to draw the face
	private double scale;

	// shapes making up the drawing of the face
	private Oval head, yellowCircle, blackCircle;
	private Triangle nose;
	private Oval leftEye, leftPupil, rightEye, rightPupil;
	
	// is the face increasing in size?
	private boolean isIncreasing; // false by default
	
	// growth or shrink factor
	// final means constant
	// static means one copy (not repeated in every instance
	// of the class)
	// to access a constant outside of the class (if public)
	// use ClassName.CONSTANT
	// e.g. SmilingFace.FACTOR
	public static final double FACTOR = 1.1;
	public static final double MAX_SCALE = 2;
	public static final double MIN_SCALE = 0.5;

	/**
	 * Draws a smiling face in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the center of the face
	 * @param y
	 *            the y coordinate of the center of the face
	 * @param scale
	 *            the multiplication factor for all default dimensions
	 * @param window
	 *            the graphics window where to draw
	 */
	public SmilingFace(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;

		// Draw the face in the graphics window
		this.drawFace();
	}

	/**
	 * Increases/Decreases the size of the face
	 */
	
	public void increaseDecrease() {
		// update the scale
		if (isIncreasing) {
			scale *= FACTOR; // same as scale = scale * factor;
		} else {
			scale /= FACTOR; // same as scale = scale / factor;
		}
		if (scale > MAX_SCALE) {
			isIncreasing = false;
		} 
		if (scale < MIN_SCALE) {
			isIncreasing = true;
		}
		// erase the face: remove the shapes making up the face
		remove();
		// redraw at the new location
		drawFace();
	}

	/**
	 * Moves the face by a given displacement
	 * 
	 * @param dx
	 *            the displacement along x
	 * @param dy
	 *            the displacement along y
	 */
	public void moveBy(int dx, int dy) {
		// update the location of the face
		x += dx; // same as x = x + dx;
		y += dy;
		// erase the face: remove the shapes making up the face
		remove();
		// redraw at the new location
		drawFace();
	}
	
	/**
	 * Erases the face from the window
	 */
	private void remove() {
		window.remove(head);
		window.remove(yellowCircle);
		window.remove(blackCircle);
		window.remove(nose);
		window.remove(leftEye);
		window.remove(leftPupil);
		window.remove(rightEye);
		window.remove(rightPupil);		
	}

	/**
	 * Draws a face in the graphics window
	 */
	private void drawFace() {
		// The face (a circle)
		int r = (int) (50 * this.scale);
		head = new Oval(this.x - r, this.y - r, 2 * r, 2 * r, Color.yellow,
				true);
		this.window.add(head);

		// The mouth
		this.drawMouth(this.x, this.y + 9 * r / 10);

		// The eyes
		// left
		this.drawEye(this.x - r / 2, this.y);
		// right
		this.drawEye(this.x + r / 2, this.y);

		// The nose
		this.drawNose(this.x, this.y);

		// Show the face
		this.window.doRepaint();
	}

	/**
	 * Draws an eye
	 * 
	 * @param eyex
	 *            the x coordinate of the center of the eye
	 * @param eyey
	 *            the y coordinate of the center of the eye
	 */
	private void drawEye(int eyex, int eyey) {
		// A black circle in a white oval
		int eyeHalfWidth = (int) (15 * this.scale);
		int eyeHalfHeight = (int) (8 * this.scale);
		Oval eye = new Oval(eyex - eyeHalfWidth, eyey - eyeHalfHeight,
				2 * eyeHalfWidth, 2 * eyeHalfHeight, Color.white, true);
		this.window.add(eye);
		int pupilRadius = eyeHalfHeight;
		Oval pupil = new Oval(eyex - pupilRadius, eyey - pupilRadius,
				2 * pupilRadius, 2 * pupilRadius, Color.black, true);
		this.window.add(pupil);

		if (eyex < x) { // left eye
			leftEye = eye;
			leftPupil = pupil;
		} else { // right eye
			rightEye = eye;
			rightPupil = pupil;
		}
	}

	/**
	 * Draws a nose
	 * 
	 * @param nx
	 *            the x coordinate of the top point of the nose
	 * @param ny
	 *            the y coordinate of the top point of the nose
	 */
	private void drawNose(int nx, int ny) {
		// A nose is a triangle
		int noseHeight = (int) (20 * this.scale);
		int noseWidth = (int) (20 * this.scale);
		nose = new Triangle(nx, ny, nx + noseWidth / 2, ny + noseHeight, nx
				- noseWidth / 2, ny + noseHeight, Color.black, true);
		this.window.add(nose);
	}

	/**
	 * Draws a mouth
	 * 
	 * @param mouthx
	 *            the x coordinate of the middle bottom point of the mouth
	 * @param mouthy
	 *            the y coordinate of the middle bottom point of the mouth
	 */
	private void drawMouth(int mouthx, int mouthy) {
		// Draw two circles (one black and one yellow)
		// The yellow circle is on top of the black circle and slightly shifted
		// up
		int mouthRadius = (int) (30 * this.scale);
		int mouthThickness = (int) (6 * this.scale);
		blackCircle = new Oval(mouthx - mouthRadius, mouthy - 2 * mouthRadius,
				2 * mouthRadius, 2 * mouthRadius, Color.black, true);
		this.window.add(blackCircle);
		yellowCircle = new Oval(mouthx - mouthRadius, mouthy - 2 * mouthRadius
				- mouthThickness, 2 * mouthRadius, 2 * mouthRadius,
				Color.yellow, true);
		this.window.add(yellowCircle);
	}
}
