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
    myThread = new Thread(this);
    myThread.start();

    /** set ball color */
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    ballColor = new Color(r,g,b);
    
    /** set slot and height */
    slot = aSlot;
    height = manager.MAX_BALLS*BALL_SIZE;
  }

  public void run() {
    //System.out.println("FallingBall run()");
    while (true) {
      if(height>0) {
        height = height-3;
        //System.out.println("height now = " + height);
      }
    }
  }

  public void draw(Graphics g) {
    System.out.println("FallingBall draw()");
    g.drawOval(40,40,40,40);
  }

  public void killBall() {
    System.out.println("FallingBall killBall()");
    
  }  
};
