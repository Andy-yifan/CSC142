import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class        

/**
 * An OceanScene displays fish, sailboats, stars, and a fourth element of your
 * choice in a graphics window.
 * 
 * Add a line to declare an instance field of type the new type that you defined
 * in hw1, then complete the method addGraphicsElements. Leave all the rest of
 * the code unchanged.
 * 
 * @author Your name here
 */

public class OceanScene extends GWindowEventAdapter {

	/** The graphics window that displays the picture */
	private GWindow window;

	/** The elements in the picture */
	// 2 sailboats that sail back and forth
	private SailBoat sailboat1, sailboat2;
	// 4 stars that twinkle
	private Star star1, star2, star3, star4,star5,star6,star7,star8;
	// 6 fish that swim back and forth
	private Fish fish1, fish2, fish3, fish4, fish5, fish6;
	// 1 moon that change from broken moon to full moon
	private CrescentMoon moon;

	// Add here the declaration of an instance field of the type
	// that you created in hw1
	// YOU MUST NAME THIS VARIABLE: myElement.
	// Thus your statement should be (replacing
	// classname with the name of your class)
	// private classname myElement; (e.g. private Moon myElement if your fourth
	// element is a Moon).

	// To keep track of the duration of the animation
	private int animationCounter;

	/**
	 * Create a picture
	 */
	public OceanScene() {
		// The graphics window
		this.window = new GWindow("Mountain scene");
		this.window.setExitOnClose();

		// The ocean and the sky
		Rectangle ocean = new Rectangle(0, 0, window.getWindowWidth(), window
				.getWindowHeight(), Color.blue, true);
		window.add(ocean);

		// the sky covers the upper quarter of the window
		Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), window
				.getWindowHeight() / 4, Color.black, true);
		window.add(sky);
		


		// Add the graphics elements
		this.addGraphicsElements();

		// Code to do the animation
		this.window.addEventHandler(this);
		this.window.startTimerEvents(150);
	}

	// To do the animation
	public void timerExpired(GWindowEvent we) {
		this.window.suspendRepaints();

		// stars twinkle
		this.star1.twinkle();
		this.star2.twinkle();
		this.star3.twinkle();
		this.star4.twinkle();
		this.star5.twinkle();
		this.star6.twinkle();
		this.star7.twinkle();
		this.star8.twinkle();
		
		// fish swim
		this.fish1.swim();
		this.fish2.swim();
		this.fish3.swim();
		this.fish4.swim();
		this.fish5.swim();
		this.fish6.swim();
		this.sailboat1.changeSize();
		this.sailboat1.moveUpOrDown(2,2);
		//this.moon.twinkle();
		

		// this.myElement.doAction();
		this.window.resumeRepaints();

		// Run the animation 100 times (about 15 s)
		this.animationCounter++;
		if (this.animationCounter >= 100)
			this.window.stopTimerEvents();
		// lets moon back
		if (animationCounter < 20) {
			this.moon.doAction(-1, -1);
		}
		if(animationCounter >= 20 && animationCounter <45){
			this.moon.doAction(1, 1);
		}
		// don't move the moon
		if(animationCounter >= 45){
			this.moon.doAction(0, 0);
		}
		// makes boat disappear
		if(animationCounter < 51){
			Rectangle blue1 = new Rectangle(190,150,65,50,Color.BLUE,true);
			window.add(blue1);
			this.sailboat2.moveUpOrDown(1, 1);
			Rectangle blue = new Rectangle(190,150,65,50,Color.BLUE,true);
			window.add(blue);
		}else{
			Rectangle blue1 = new Rectangle(190,150,65,50,Color.BLUE,true);
			window.add(blue1);
			this.sailboat2.moveUpOrDown(0, 0);
			Rectangle blue = new Rectangle(190,150,65,50,Color.BLUE,true);
			window.add(blue);
			}
		
	}

	/**
	 * Instantiate in this method the elements of the scene. This is the only
	 * method that you need to modify in this class
	 */
	private void addGraphicsElements() {
		// draw stars
		this.star1 = new Star(50, 20, 0.8, this.window);
		this.star2 = new Star(100, 30, 1.2, this.window);
		this.star3 = new Star(150, 10, 0.6, this.window);
		this.star4 = new Star(300, 30, 1.5, this.window);
		this.star5 = new Star(20, 30, 1, this.window);
		this.star6 = new Star(30, 22, 0.5, this.window);
		this.star7 = new Star(230, 43, 1, this.window);
		this.star8 = new Star(430, 14, 0.9, this.window);

		// draw fish
		this.fish1 = new Fish(50, 250, 1, Fish.LEFT_MOVING, this.window);
		this.fish2 = new Fish(200, 370, 1.2, Fish.RIGHT_MOVING, this.window);
		this.fish3 = new Fish(300, 340, 1.1, Fish.LEFT_MOVING, this.window);
		this.fish4 = new Fish(100, 400, 0.8, Fish.RIGHT_MOVING, this.window);
		this.fish5 = new Fish(200, 120, 0.6, Fish.LEFT_MOVING, this.window);
		this.fish6 = new Fish(350, 380, 1.3, Fish.RIGHT_MOVING, this.window);
		
		// draw boats
		this.sailboat1 = new SailBoat(300, 300, 1.5,SailBoat.UP, this.window);
		this.sailboat2 = new SailBoat(200, 100, 0.7, SailBoat.DOWN,this.window);
		
		//draw moon
		this.moon = new CrescentMoon(400,30,window);


	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new OceanScene();
	}
}