import java.awt.Color;

import uwcse.graphics.GWindow; // access the graphics utilities in the uw library
import uwcse.graphics.Line;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Triangle;
// access the Color class


/**
 * <p>Create a cable car in a graphics window</p>  
 * @author Yifan Yu
 */

public class CableCar {

  // Your instance fields go here
	private GWindow window;
	private int x;
	private int y;
	private double scale;
	// shapes making up the drawing of the cableCar
	
	private Triangle tether;
	private Rectangle car,win1,win2,win3;
  /**
   * Create a cable car at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the cable car
   * @param y the y coordinate of the center of the cable car
   * @scale the factor that multiplies all default dimensions for this cable car
   * (e.g. if the default size is 80, the size of this cable car is
   * scale * 80)
   * @window the graphics window this cable car belongs to
   */
  public CableCar(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	    this.window = window;
	    this.scale = scale;
	    this.x = x;
	    this.y = y; 
    // The details of the drawing are in a private method
    this.draw();
    
  }
  /**
   * make movement
   */
  public void move(int dx, int dy) {
		// update the location of the cable car
		x += dx; 
		
		// erase the cable car: remove the shapes making up the cable car
		removeCab();
		// redraw at the new location
		draw();
	}
  /**
	 * Erases the cablecar from the window
	 */
	private void removeCab() {
		
		window.remove(tether);
		window.remove(car);
		window.remove(win1);
		window.remove(win2);
		window.remove(win3);
				
	}
  /** Draw a cable car at location (x,y) */
  private void draw()
  {
	  //draw a line
	 Line line1 = new Line(0,100,500,100); 
	 this.window.add(line1);
	 // draw the tether
	 tether = new Triangle(this.x+(int)scale*60,this.y-(int)scale*35,this.x+(int)scale*25,this.y-(int)scale,this.x+(int)scale*110,this.y-(int)scale,Color.BLACK,false);
	 this.window.add(tether);
	 //draw the car
	 car = new Rectangle(this.x,this.y,(int)(150*this.scale),(int)(75*this.scale),
	            Color.blue,true);
	 this.window.add(car);
	 // draw the windows
	 win1 = new Rectangle(this.x+(int)this.scale*90,this.y+(int)this.scale*25,(int)(25*this.scale),(int)(25*this.scale),Color.white,true);
	 this.window.add(win1);
	 win2 = new Rectangle(this.x+(int)this.scale*60,this.y+(int)this.scale*25,(int)(25*this.scale),(int)(25*this.scale),Color.white,true);
	 this.window.add(win2);
	 win3 = new Rectangle(this.x+(int)this.scale*30,this.y+(int)this.scale*25,(int)(25*this.scale),(int)(25*this.scale),Color.white,true);
	 this.window.add(win3);
	 
  }

	
 
}
