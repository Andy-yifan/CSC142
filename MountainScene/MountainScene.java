import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>
 * A MountainScene displays snow men, trees (with ornaments), a cable car and several stars
 * fourth element of your choice in a graphics window
 * </p>
 * 
 * @author Yifan Yu
 */

public class MountainScene {

	/** The graphics window that displays the picture */
	private GWindow window;

	/**
	 * Create an image of a mountain scene
	 */
	public MountainScene() {

		// The graphics window
		// The window is by default 500 wide and 400 high
		this.window = new GWindow("Mountain scene");
		this.window.setExitOnClose(); // so that a click on the close box of the
		// window terminates the application

		// Background (cyan here)
		Rectangle bgnd = new Rectangle(0, 0, window.getWindowWidth(), window
				.getWindowHeight(), Color.cyan, true);
		this.window.add(bgnd);

		// Create the scene elements
		// e.g. a tree in the lower left area 1.5 times the normal size
		new Tree(100, 300, 1.5, this.window);
		new Tree(250, 200, 1.3, this.window);
		new Tree(45, 250, 1, this.window);
		new Tree(300, 250, 1, this.window);
		new SnowMan(185,300,1,this.window);
		new SnowMan(400,230,2,this.window);
		new SnowMan(200,50,1.2,this.window);
		new CableCar(30,135,1,this.window);
		new Star(300,20,3,this.window);
		new Star(400,20,5,this.window);
		new Star(250,50,3,this.window);
		new Star(100,30,3,this.window);
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new MountainScene();
	}

}
