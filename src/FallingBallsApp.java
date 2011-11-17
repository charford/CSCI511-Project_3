/**
 *  Class FallingBallsApp
 *  @author Casey Harford
 *  @version 1.0
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;

class FallingBallsApp extends JFrame {
  
  /** create a ballManager object, used when BallPanel need to paint */
  private BallManager thisManager;

  /**
   *  constructor for FallingBallsApp
   *  @param manager  BallManager to use for this session
  */
  FallingBallsApp(final BallManager manager) {

    /** assign the manager */
    thisManager = manager;

    /** configure and display app space */
    setLayout(new BorderLayout());
    setTitle("FallingBallsApp");
    JPanel ballPanel = new BallPanel();
    ballPanel.setPreferredSize(new Dimension( 
      manager.MAX_SLOTS*FallingBall.BALL_SIZE,
      manager.MAX_BALLS*FallingBall.BALL_SIZE));
    add(ballPanel);
    pack();
    setResizable(false);
    setVisible(true);

    /** using the MouseListener to drop and kill balls when user clicks */
    addMouseListener(new MouseAdapter() {

      /**
       *  method mouseClicked, gets the x and y coordinates and either kills a ball, or drops a ball
       *  @param e  mouseClicked event
      */
      public void mouseClicked(MouseEvent e) {
    
        /** get the x and y coordinates */
        int x = e.getX();
        int y = e.getY();

        /** convert the x and y coordinates to array coordinates */
        int slot = x / FallingBall.BALL_SIZE;
        int row = y / FallingBall.BALL_SIZE;
        
        /** check to see if spot that was clicked is currently occupied, if so, kill it */
        if(!manager.available(slot,row-1)) manager.killBall(slot,row-1);
        
        /** not occupied, so drop ball in slot */
        else if(slot < manager.MAX_SLOTS) manager.dropBall(slot);
      } //end method mouseClicked
    }); //end MouseListener
  } //end constructor for FallingBallsApp

  /**
   *  main method, creates a new application, and repaints the space every 30 milliseconds
   *  @param args not used, but would get command line arguments
  */
  public static void main(String[] args) {

    /** creating the new application */
    FallingBallsApp application = new FallingBallsApp(new BallManager());

    /** configure close */
    application.setDefaultCloseOperation(EXIT_ON_CLOSE);

    /** repaint the drawing space every 30 milliseconds */
    while (true) {
      application.repaint();
      try {
        Thread.sleep(30);
      }
      catch (InterruptedException exception) { }
    }
  } //end main method

  /**
   *  inner class BallPanel, makes calls to each FallingBall and draws it
  */
  private class BallPanel extends JPanel {

    public void paintComponent(Graphics g) {
      FallingBall[][] slots = thisManager.getSlots();
      for(int i=0; i<BallManager.MAX_BALLS; i++) {
        for(int j=0; j<BallManager.MAX_SLOTS; j++) {
          if( slots[i][j] != null ) {
            slots[i][j].draw(g);
          } //end if slot is null statement
        } //end for loop(slots
      } //end for loop(balls)
    } //end paintComponent 
  } //end BallPanel class
} //end FallingBallsApp class
