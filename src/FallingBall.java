import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

class FallingBall implements Runnable {

  static final int BALL_SIZE = 30;
  private BallManager manager;
  private int slot, height;
  private Color ballColor;
  private boolean keepRunning; 
  Thread runner;
  
  public FallingBall(int aSlot, BallManager theManager) {
    System.out.println("FallingBall constructor");
  }

  public void run() {
    System.out.println("FallingBall run()");
    
    
  }

  public void draw(Graphics g) {
    System.out.println("FallingBall paint()");
    Random rand = new Random();
    float R = rand.nextFloat();
    float G = rand.nextFloat();
    float B = rand.nextFloat();
    g.setColor(new Color(R,G,B));
    g.fillOval(slot,height,BALL_SIZE,BALL_SIZE);
  }

  public void killBall() {
    System.out.println("FallingBall killBall()");
    if(runner != null) runner = null;
  }  
};
