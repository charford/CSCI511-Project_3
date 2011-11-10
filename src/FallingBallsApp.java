import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class FallingBallsApp extends JFrame {

  FallingBallsApp(final BallManager manager) {
    setLayout(new BorderLayout());
    JPanel ballPanel = new BallPanel();
    ballPanel.setPreferredSize(new Dimension( 
      BallManager.MAX_SLOTS*FallingBall.BALL_SIZE,
      BallManager.MAX_BALLS*FallingBall.BALL_SIZE));
    add(ballPanel);

    pack();
    setVisible(true);
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int slot = x / FallingBall.BALL_SIZE;
        System.out.println("Mouse click in slot " + slot);
        manager.dropBall(slot);
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
};
