import java.awt.Color; // access the Color class

import uwcse.graphics.GWindow; // access the graphics utilities in the uw library
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Triangle;

/**
 * <p>Create a tree with ornaments in a graphics window</p>
 * @author Yifan Yu
 */   

public class Tree {

  // Instance fields
  // The graphics window this tree belongs to
  private GWindow window;
  // The location of this tree
  // (precisely (as done in the draw method), (x,y) is
  // the upper left corner of the tree trunk)
  private int x;
  private int y;
  // The scale used to draw this tree
  private double scale;
  
  private Oval ball1,ball2,ball3;
  private Triangle foliage1, foliage2,foliage3;
  private Rectangle trunk;
  //color the ball
  private Color color;
  
 

  /**
   * Create a tree
   * @param x the x coordinate of the tree location (upper left corner of the tree trunk)
   * @param y the y coordinate of the tree location
   * @param window the graphics window this Tree belongs to
   * @param scale the scale of the drawing (all default dimensions are multiplied
   * by scale)
   */
  public Tree(int x, int y, double scale, GWindow window)
  {
    // Initialize the instance fields (the use of this is required
    // since the instance fields have the same name as the
    // parameters of the constructor)
    this.window = window;
    this.scale = scale;
    this.x = x;
    this.y = y;

    // the details of the drawing are in written in the private method draw()
    this.draw();
    
  }
  	//make the ornaments flash
	public void flashOrnaments(){
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		color = new Color(r, g, b);
		// erase the ornaments
			eraseBall();
				
		// redraw the ornaments
		draw();
		
	}
	//erase ball
    private void eraseBall() {
		window.remove(ball1);
		window.remove(ball2);
		window.remove(ball3);
				
	}
  
  /**
   * draw a pine tree
   */
  private void draw(){
    // trunk of the tree: a brown rectangle
    // (int) converts to an int 20*this scale (etc...), which is a double
    // For instance, (int)23.8 is 23
    // This is necessary since the Rectangle constructor takes integers
    trunk = new Rectangle(this.x+(int)(5*this.scale),this.y+(int)(15*this.scale),(int)(15*this.scale),(int)(60*this.scale),
			            Color.black,true);
    // Foliage (improve the drawing!)
    // a green triangle
    foliage1 = new Triangle(this.x-(int)(15*this.scale),this.y+(int)(15*this.scale),
			 this.x+(int)(13*this.scale),this.y-(int)(35*this.scale),
			 this.x+(int)(40*this.scale),this.y+(int)(15*this.scale),
				 Color.green,true);
    foliage2 = new Triangle(this.x-(int)(15*this.scale),this.y+(int)(15*this.scale)+15*(int)(this.scale),
			 this.x+(int)(13*this.scale),this.y-(int)(35*this.scale)+15*(int)(this.scale),
			 this.x+(int)(40*this.scale),this.y+(int)(15*this.scale)+15*(int)(this.scale),
			 Color.green,true);
    foliage3 = new Triangle(this.x-(int)(15*this.scale),this.y+(int)(15*this.scale)+(int)(this.scale)*30,
			 this.x+(int)(13*this.scale),this.y-(int)(35*this.scale)+(int)(this.scale)*30,
			 this.x+(int)(40*this.scale),this.y+(int)(15*this.scale)+(int)(this.scale)*30,
			 Color.green,true);
    
    this.window.add(trunk);
    this.window.add(foliage1);
	this.window.add(foliage2);
	this.window.add(foliage3);
 
	
	//define height and width of the ball
	int height = (int)(30 * scale);
	int width = (int)(30 * scale);
	ball1 = new Oval(this.x-(int)(12*this.scale),this.y+(int)(15*this.scale),height/3,width/3, color, true);
    ball2 = new Oval(this.x+(int)(12*this.scale),this.y-(int)(16*this.scale),height/3,width/3, color, true);
    ball3 = new Oval(this.x+(int)(15*this.scale),this.y-(int)(6*this.scale),height/3,width/3, color, true);
    this.window.add(ball1);
	this.window.add(ball2);
	this.window.add(ball3);
	
  }
    
    
}