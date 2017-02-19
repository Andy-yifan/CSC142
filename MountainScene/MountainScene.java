import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>
 * A MountainScene displays snow men, trees (with ornaments), a cable car and a
 * fourth element of your choice in a graphics window
 * </p>
 * Add a line to declare an instance field of type the new type that you defined
 * in hw1, then complete the method addGraphicsElements. Leave all the rest of
 * the code unchanged.
 * 
 * @author Your name here
 */

public class MountainScene extends GWindowEventAdapter {
	/** The graphics window that displays the picture */
	private GWindow window;

	/** The elements in the picture */
	// 2 snowmen that move their arms and hats
	private SnowMan snowMan1, snowMan2;
	// 3 trees that flash their ornaments
	private Tree tree1, tree2, tree3;
	
	// A cable car that moves back and forth across the window
	private CableCar cableCar;
	// YOU MUST NAME THIS VARIABLE: myElement. Thus your statement should be
	// (replacing
	// Classname with the name of your class)
	 private MyElement myElement1,myElement2,myElement3,myElement4;
	

	// To keep track of the duration of the animation
	private int animationCounter;

	/**
	 * Create a picture
	 */
	public MountainScene() {
		// The graphics window
		this.window = new GWindow("Mountain scene");
		this.window.setExitOnClose();

		// paint the background
		Rectangle bgnd = new Rectangle(0, 0, this.window.getWindowWidth(),
				this.window.getWindowHeight(), Color.cyan, true);
		this.window.add(bgnd);
		this.window.doRepaint();

		// Add the graphics elements
		this.addGraphicsElements();

		// Code to do the animation
		this.window.addEventHandler(this);
		this.window.startTimerEvents(150);
	}

	// To do the animation 
	public void timerExpired(GWindowEvent we) {
		this.window.suspendRepaints();
		if (animationCounter <= 50) {
			// make the snow man move arms and hat
			this.snowMan1.moveArmsAndHat(0,-1);
			this.snowMan2.moveArmsAndHat(0,-1);
			//move the cable car right
			this.cableCar.move(7, 0);
			// increase the star
			this.myElement1.increaseDecrease();
			this.myElement2.increaseDecrease();
			this.myElement3.increaseDecrease();
			this.myElement4.increaseDecrease();
			
		} else {
			// make the snow man move arms and hat
			this.snowMan1.moveArmsAndHat(0,1);
			this.snowMan2.moveArmsAndHat(0,1);
			// move the car left
			this.cableCar.move(-7, 0);
			// decrease the star
			this.myElement1.increaseDecrease();
			this.myElement2.increaseDecrease();
			this.myElement3.increaseDecrease();
			this.myElement4.increaseDecrease();
			
		}
		
		
		// make the ornaments flash
		this.tree1.flashOrnaments();
		this.tree2.flashOrnaments();
		this.tree3.flashOrnaments();
		
		//make my element twinkle
		this.myElement1.twinkle();
		this.myElement2.twinkle();
		this.myElement3.twinkle();
		this.myElement4.twinkle();
		
		this.window.resumeRepaints();

		// Run the animation 100 times (about 15 s)
		this.animationCounter++;
		if (this.animationCounter >= 100)
			this.window.stopTimerEvents();
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new MountainScene();
	}

	/**
	 * Instantiate in this method the elements of the scene. This is the only
	 * method that you need to modify in this class
	 */
	private void addGraphicsElements() {
		// You can change the coordinates and scales that appear
		// in the constructors (but don't change the names of the variables)
		this.cableCar = new CableCar(0, 135, 1, this.window);
		this.tree1 = new Tree(100, 300, 1.5, this.window);
		this.tree2 = new Tree(250, 200, 1.3, this.window);
		this.tree3 = new Tree(45, 250, 1, this.window);
		this.snowMan1 = new SnowMan(185,300,1,this.window);
		this.snowMan2 = new SnowMan(400,250,1.5,this.window);
		
		// Complete the line of code below to instantiate an object of the type
		// that you created in homework #1
		this.myElement1 = new MyElement(300,20,3,this.window);
		this.myElement2 = new MyElement(400,20,5,this.window);
		this.myElement3 = new MyElement(250,50,3,this.window);
		this.myElement4 = new MyElement(100,30,3,this.window);
	}
}
