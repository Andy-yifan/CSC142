import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>Create a snow man in a graphics window</p>
 * @author Yifan Yu
 */  

public class SnowMan {

  // Your instance fields go here
	private GWindow window;
	private int x;
	private int y;
	private double scale;
	
	// the direction of arm and hat up or down
	
		

	// use Increase to control the up and down
	private int Increase;
	
	
	//public static final double MAX_Y = 0.5;
	//public static final double MIN_Y = 0.5;
	
	// The part of snowMan
	private Oval head,body,eye1,eye2,mouth;
	
	private Rectangle hatbot,hattop;
	
	private Line arm1,arm11,arm2,arm22;
	
	
	
		
		
		
  /**
   * Create a snow man in at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the head of the snow man
   * @param y the y coordinate of the center of the head of the snow man
   * @scale the factor that multiplies all default dimensions for this snow man
   * (e.g. if the default head radius is 20, the head radius of this snow man is
   * scale * 20)
   * @window the graphics window this snow man belongs to
   */
  public SnowMan(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	  this.window = window;
	  this.scale = scale;
	  this.x = x;
	  this.y = y;
	  
    // Put the details of the drawing in a private method
    this.drawbody();
    this.drawHat();
    this.drawFace();
    this.drawArm();
  }

  /** Draw in the graphics window a snow man at location (x,y) */
  private void drawbody()
  {
	  //define radius of head
	  
		int height = (int)(30 * scale);
		int width = (int)(30 * scale);
	  //head
	 
		head = new Oval(x - width/2, y - height/2, 
				width, height, Color.white, true);
		 this.window.add(head);
	  //body
	   
		body = new Oval(x - width, y + height/3, 
				width*2, height*2, Color.white, true);
		 this.window.add(body);
		 
  }
  private void drawHat(){
	  // define radius of hat
	  int height = (int)(30 * scale);
	  int width = (int)(30 * scale);
	  //red hat
	  hatbot= new Rectangle(x - width/2, y - height/2, 
				width, height/10,Color.orange,true);
	  hattop= new Rectangle(x - width/4, y - height/2-height/2, 
				width/2, height/2,Color.red,true);
	  this.window.add(hattop);
	  this.window.add(hatbot);
  }	  
  private void drawFace(){
	  // define radius of hat
	  int height = (int)(30 * scale);
	  int width = (int)(30 * scale);
	  //eyes
	  eye1 = new Oval(x - width/4, y - height/5, 
				width/9, height/4, Color.black, true);
	  eye2 = new Oval(x - width/10, y - height/5, 
				width/9, height/4, Color.black, true);
	  // mouth
	  mouth = new Oval(x - width/5, y +height/6, 
				width/5, height/7, Color.red, true);
	  this.window.add(eye1);
	  this.window.add(eye2);
	  this.window.add(mouth);
  }
  private void drawArm(){
	  // define radius of arm
	  int height = (int)(30 * scale);
	  int width = (int)(30 * scale);

	  // arms
	  arm1 = new Line(x-width, y + height/4, 
				x-width/5, y+height+height/4);
	  arm2 = new Line(x+width, y + height/10, 
				x+width/5, y+height+height/10);
	  arm11 = new Line(x-width+width/5, y + height/10, 
			  x-width+width/5, y + height/2);
	  arm22 = new Line(x+width-height/5, y + height/30, 
			  x+width-height/3, y + height/2);
   
	  //add to the window
	  
	  this.window.add(arm1);
	  this.window.add(arm2);
	  this.window.add(arm11);
	  this.window.add(arm22);
  }
  // make to move arms and hat
  public void moveArmsAndHat(int dx,int dy){
		// update the location
	  if (Increase<=5) {
			y -=dy;
			
			//use Increase to control the dy
			Increase +=5;
		} else {
			y+=dy; 
			Increase-=5;
		}
		
		// erase the current image
		eraseHatAndArms();
		// redraw the hat
		drawHat();
		drawArm();
		
	}
  private void eraseHatAndArms(){
		window.remove(hattop);
		window.remove(hatbot);
		window.remove(arm1);
		window.remove(arm11);
		window.remove(arm2);
		window.remove(arm22);
	}  
  }