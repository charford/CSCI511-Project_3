import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

class BallPanel extends JPanel {

  public BallPanel() {
    System.out.println("DEBUG: BallPanel class constructor");
  }

  public void paintComponent(Graphics g) {
    g.setColor(Color.black);
    g.drawOval(10,10,10,10);

  }
};
