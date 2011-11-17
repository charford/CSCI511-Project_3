/**
 *  Class FallingBall
 *  @author Casey Harford
 *  @version 1.0
*/
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

class FallingBall implements Runnable {

  public static final int BALL_SIZE = 30;
  private BallManager manager;
  private int slot; 
  private int height;
  private int oldHeight;
  private Color ballColor;
  private boolean keepRunning; 
  private Thread myThread;
  
  /**
   *  constructor for FallingBall
   *  @param aSlot      the slot to create a falling ball
   *  @param theManager the manager that manages this falling ball
  */
  public FallingBall(int aSlot, BallManager theManager) {

    /** assign the manager */
    manager = theManager;

    /** set slot and height */
    slot = aSlot;
    height = 0;
    oldHeight = height;

    /** set ball color */
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    ballColor = new Color(r,g,b);

    /** start the thread */
    myThread = new Thread(this);
    myThread.start();
    keepRunning = true;
  }

  /**
   *  method run, animates the ball drop
  */
  public void run() {

    /** initialize ballBelow */
    int ballBelow = 0;

    /** run till keepRunning turns false(only happens if killBall is called) */
    while (keepRunning) {
      try {
    
        /** wait 10 milliseconds between each frame animation */
        Thread.sleep(10);

        /** check if ball below */
        if(manager.available(slot,(height/BALL_SIZE)+1)) { 
          ballBelow = height + BALL_SIZE;
        }
        
        /** if space between ball below still, continue falling */
        if( (height+BALL_SIZE) <= ballBelow) {
        
          /** increment the height (moves the ball) */
          height += 3;

          /** check if ball is still moving */
          if( (height/BALL_SIZE) > (oldHeight/BALL_SIZE) ) {
            manager.moveBall(slot, (oldHeight/BALL_SIZE), slot, (height/BALL_SIZE));
            oldHeight = height;
          }
        }
        /** no space left below, what about the sides? */
        else {
          /** check if ball to the left, below */
          if(manager.available(slot-1,(oldHeight/BALL_SIZE))) {
            /** check if ball to the left, same height */
            if(manager.available(slot-1,(oldHeight/BALL_SIZE)+1)) {
              manager.moveBall(slot, (oldHeight/BALL_SIZE), slot-1, (oldHeight/BALL_SIZE)+1);
              slot -= 1;
              height = ((oldHeight/BALL_SIZE)+1)*BALL_SIZE;
              oldHeight = height;
            }
          }
          /** check if ball to the right, below */
          if(manager.available(slot+1,(oldHeight/BALL_SIZE))) {
            /** check if ball to the right, same height */
            if(manager.available(slot+1,(oldHeight/BALL_SIZE)+1)) {
              manager.moveBall(slot, (oldHeight/BALL_SIZE), slot+1, (oldHeight/BALL_SIZE)+1);
              slot += 1;
              height = ((oldHeight/BALL_SIZE)+1)*BALL_SIZE;
              oldHeight = height;
            }
          }
        }
      }
      catch (Exception e) { }
    }
  }
  
  /**
   *  method draw, draws the current position of the ball
   *  @param g  object to draw with(usually passed from BallPanel)
  */
  public void draw(Graphics g) {

    /** draw outline of ball */
    g.setColor(Color.black);
    g.fillOval(slot*BALL_SIZE,height, BALL_SIZE, BALL_SIZE);

    /** draw the ball */
    g.setColor(ballColor);
    g.fillOval( (slot*BALL_SIZE)+2, height+2, BALL_SIZE-4, BALL_SIZE-4);
  }

  /**
   *  method killBall, kills the current ball
  */
  public void killBall() {
    keepRunning = false;
  }  
}
