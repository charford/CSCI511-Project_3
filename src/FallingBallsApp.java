import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;

class FallingBallsApp extends JFrame {
  BallManager thisManager;
  FallingBallsApp(final BallManager manager) {
    thisManager = manager;
    setLayout(new BorderLayout());
    JPanel ballPanel = new BallPanel();
    ballPanel.setPreferredSize(new Dimension( 
      manager.MAX_SLOTS*FallingBall.BALL_SIZE,
      manager.MAX_BALLS*FallingBall.BALL_SIZE));
    add(ballPanel);

    pack();
    setVisible(true);
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int slot = x / FallingBall.BALL_SIZE;
        int row = y / FallingBall.BALL_SIZE;
        if(slot < manager.MAX_SLOTS) {
          System.out.println("Mouse click in slot " + slot);
          if(manager.available( slot,row-1 ) ) manager.dropBall(slot);
        }
      }
    });  
  }

  public static void main(String[] args) {
    FallingBallsApp application = new FallingBallsApp(new BallManager());
    application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    while (true) {
      application.repaint();
      try {
        Thread.sleep(30);
      }
      catch (InterruptedException exception) {

      }
    }
  }
  class BallPanel extends JPanel {

    public BallPanel() {
      System.out.println("DEBUG: BallPanel class constructor");
    }

    public void paintComponent(Graphics g) {
      FallingBall[][] slots = FallingBallsApp.this.thisManager.getSlots();
      for(int i=0; i<BallManager.MAX_BALLS-1; i++) {
        for(int j=0; j<BallManager.MAX_SLOTS-1; j++) {
          if(slots[i][j] != null) slots[i][j].draw(g);
        }
      }
    }
  };
};
