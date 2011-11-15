import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;

class FallingBall implements Runnable {

  public static final int BALL_SIZE = 30;
  private BallManager manager;
  private int slot, height;
  private Color ballColor;
  private boolean keepRunning; 
  private Thread myThread;
  
  public FallingBall(int aSlot, BallManager theManager) {
    //System.out.println("FallingBall constructor");
 
    /** set slot and height */
    slot = aSlot;
    height = manager.MAX_BALLS*BALL_SIZE;

    myThread = new Thread(this);
    myThread.start();
    keepRunning = true;

    /** set ball color */
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    ballColor = new Color(r,g,b);
    
  }

  public void run() {
    System.out.println("FallingBall run(), Color = " + ballColor + ", slot = " + slot + ", height = " + height);
    while (keepRunning) {
      if(height > 0) {
        height = height-3;
        System.out.println("height now = " + height);
      }
    }
  }

  public void draw(Graphics g) {
    System.out.println("FallingBall draw()");
    g.fillOval(slot,height,BALL_SIZE,BALL_SIZE);
  }

  public void killBall() {
    System.out.println("FallingBall killBall()");
    keepRunning = false;
    myThread = null;
  }  
}
