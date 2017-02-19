// Write your compliance statement here:
// What are your 4 extra features?
// How is your new alien different from the one described by the Alien class?
//
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import uwcse.graphics.GWindow;
import uwcse.graphics.GWindowEvent;
import uwcse.graphics.GWindowEventAdapter;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;

/**
 * A SpaceInvader displays a fleet of alien ships and a space ship. The player
 * directs the moves of the spaceship and can shoot at the aliens.
 */

public class SpaceInvader extends GWindowEventAdapter {
	// Possible actions from the keyboard
	/** No action */
	public static final int DO_NOTHING = 0;
    // add new alien
	private int al= 0;
	
	/** Steer the space ship */
	public static final int SET_SPACESHIP_DIRECTION = 1;

	/** To shoot at the aliens */
	public static final int SHOOT = 2;

	// Period of the animation (in ms)
	// (the smaller the value, the faster the animation)
	private int animationPeriod = 100;

	// Current action from the keyboard
	private int action;

	// Game window
	private GWindow window;

	// The space ship
	private SpaceShip spaceShip;

	// Direction of motion given by the player
	private int dirFromKeyboard = MovingObject.LEFT;

	// The aliens
	private ArrayList<Alien> aliens;
	private ArrayList<Alien2> alien;
	private ArrayList<Alien3> alien3;
	// Is the current game over?
	private String messageGameOver = " ";

	/**
	 * Construct a space invader game
	 */
	public SpaceInvader() {
		this.window = new GWindow("Space invaders", 500, 500);
		this.window.setExitOnClose();
		this.window.addEventHandler(this); // this SpaceInvader handles all of
		// the events fired by the graphics
		// window

		// Display the game rules
		String rulesOfTheGame = "Save the Earth! Destroy all of the aliens ships.\n"
				+ "To move left, press 'a'.\n"
				+ "To move right, press 'd'.\n"
				+ "To move up, press 'w'.\n"
				+ "To move down, press 's'.\n"
				+ "To shoot, press the ' '.\n" 
				+ "To quit, press 'q'."
				+ "To restart press 'e'.";
		JOptionPane.showMessageDialog(null, rulesOfTheGame, "Space invaders",
				JOptionPane.INFORMATION_MESSAGE);
		this.initializeGame();
	}

	/**
	 * Initialize the game No1 (draw the background, aliens, and space ship)
	 */
	private void initializeGame() {
		// Clear the window
		this.window.erase();

		// Background (starry universe)
		Rectangle background = new Rectangle(0, 0,
				this.window.getWindowWidth(), this.window.getWindowHeight(),
				Color.black, true);
		this.window.add(background);
		// Add 50 stars here and there (as small circles)
		Random rnd = new Random();
		for (int i = 0; i < 50; i++) {
			// Random radius between 1 and 3
			int radius = rnd.nextInt(3) + 1;
			// Random location (within the window)
			// Make sure that the full circle is visible in the window
			int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
			this.window.add(new Oval(x, y, 2 * radius, 2 * radius, Color.white,
					true));
		}

		// ArrayList of aliens
		this.aliens = new ArrayList<Alien>();
		this.alien = new ArrayList<Alien2>();


		// Create 12 aliens
		// Initial location of the aliens
		// (Make sure that the space ship can fire at them)
		int x = 5 * SpaceShip.WIDTH;
		int y = 10 * Alien.RADIUS;
		for (int i = 0; i < 10; i++) {
			this.aliens.add(new Alien(this.window, new Point(x, y)));
			x += 5 * Alien.RADIUS;
			y = rnd.nextInt(100);
		}
		for (int i = 0; i < 1; i++) {
			this.alien.add(new Alien2(this.window, new Point(x, y)));
			x += 5 * Alien2.RADIUS;
			y = rnd.nextInt(100);
		}

		// Create the space ship at the bottom of the window
		x = this.window.getWindowWidth() / 2;
		y = this.window.getWindowHeight() - SpaceShip.HEIGHT / 2;
		this.spaceShip = new SpaceShip(this.window, new Point(x, y));

		// start timer events
		this.window.startTimerEvents(this.animationPeriod);
	}

	/**
	 * Initialize the game No.2 (draw the background, alien boss)
	 */
	private void initializeGame2() {
		// Clear the window
		this.window.erase();

		// Background (starry universe)
		Rectangle background = new Rectangle(0, 0,
				this.window.getWindowWidth(), this.window.getWindowHeight(),
				Color.black, true);
		this.window.add(background);
		// Add 50 stars here and there (as small circles)
		Random rnd = new Random();
		for (int i = 0; i < 60; i++) {
			
			int radius = rnd.nextInt(6) + 1;
			// Random location (within the window)
			// Make sure that the full circle is visible in the window
			int x = rnd.nextInt(this.window.getWindowWidth() - 3* radius);
			int y = rnd.nextInt(this.window.getWindowHeight() - 3* radius);
			this.window.add(new Oval(x, y, 2 * radius, 2 * radius, Color.yellow,
					true));
		}

		// ArrayList of aliens
		
		this.alien3 = new ArrayList<Alien3>();
		this.alien3.add(new Alien3(this.window, new Point(200, 50)));

		

		// Create the space ship at the bottom of the window
		int x = this.window.getWindowWidth() / 2;
		int y = this.window.getWindowHeight() - SpaceShip.HEIGHT / 2;
		this.spaceShip = new SpaceShip(this.window, new Point(x, y));

		// start timer events
		this.window.startTimerEvents(this.animationPeriod);
	}
	
	
	/**
	 * Move the objects within the graphics window every time the timer fires an
	 * event
	 */
	public void timerExpired(GWindowEvent we) {
		// Perform the action requested by the user?
		switch (this.action) {
		case SpaceInvader.SET_SPACESHIP_DIRECTION:
			this.spaceShip.setDirection(this.dirFromKeyboard);
			break;
		case SpaceInvader.SHOOT:
			this.spaceShip.shoot(this.aliens,this.alien, this.alien3);
			
			break;
		}

		this.action = SpaceInvader.DO_NOTHING; // Don't do the same action
		// twice

		//when end the first game, start second one
		this.updateGame();
		
		if(aliens.size() == 0){
			if(al == 0){
				initializeGame2();
				al++;
			}
			if(alien3.size() == 0 && al ==1){
				anotherGame(messageGameOver);
				SpaceShip.score = 0;
				al = 0;
			}
		}
	}

	

	/**
	 * Select the action requested by the pressed key
	 */
	public void keyPressed(GWindowEvent e) {
		// Don't perform the actions (such as shoot) directly in this method.
		// Do the actions in timerExpired, so that the alien ArrayList can't be
		// modified at the same time by two methods (keyPressed and timerExpired
		// run in different threads).

		switch (Character.toLowerCase(e.getKey())) // not case sensitive
		{
		// Put here the code to move the space ship with the < and > keys

		case ' ': // shoot at the aliens
			this.action = SpaceInvader.SHOOT;	
			break;

		case 'a': // < if uppercase -> left
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = MovingObject.LEFT;
			break;

		case 'd': // > if uppercase -> right
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = MovingObject.RIGHT;
			break;
		
		case 'w': // < if uppercase -> left
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = MovingObject.UP;
			break;

		case 's': // > if uppercase -> right
			this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
			this.dirFromKeyboard = MovingObject.DOWN;
			break;
		
		case 'q': // quit the game (BlueJ might not like that one)
			System.exit(0);

		case 'e': // restart the game
			initializeGame();
			SpaceShip.score = 0;
			al = 0;
			alien3.remove(0);
			
		default: // no new action
			this.action = SpaceInvader.DO_NOTHING;
			break;
		}
	}

	/**
	 * Update the game (Move aliens + space ship)
	 */
	private void updateGame() {
		// Is the game won (or lost)?
		
		// Put here code to end the game (= no more aliens)

		this.window.suspendRepaints(); // to speed up the drawing

		// Update the ArrayList(remove the dead aliens)
		Iterator <Alien> it = aliens.iterator();
		
		while(it.hasNext()){
			Alien a = it.next();
			if(a.isDead()){
				it.remove();
			}	
			
		}
		Iterator<Alien2> its = alien.iterator();
		while(its.hasNext()){
			Alien2 b = its.next();
			if(b.isDead()){
				its.remove();
			}
		}	
		// Move the aliens
		for (Alien a : aliens) {
			a.move();
			
		}
		for (Alien2 b : alien) {
			b.move();
		}
		if(alien3 != null && alien3.size() != 0){
			Alien3 a = alien3.get(0);
			if (a.isDead()) {
				alien3.remove(0);
			}
			
			// Move the aliens
			
			a.move();
			
		}
		// Move the space ship
		this.spaceShip.move();

		// Display it all
		this.window.resumeRepaints();
	}

	/**
	 * Does the player want to play again?
	 */
	public void anotherGame(String s) {
		// this method is useful at the end of a game if you want to prompt the
		// user
		// for another game (s would be a String describing the outcome of the
		// game
		// that just ended, e.g. "Congratulations, you saved the Earth!")
		int choice = JOptionPane.showConfirmDialog(null, s
				+ "\nScore"+SpaceShip.score +"\nDo you want to play again?", "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		
		if(choice == JOptionPane.NO_OPTION){
			System.exit(0);
		}else if(choice == JOptionPane.YES_OPTION){
			initializeGame();
		}
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new SpaceInvader();
	}
}
