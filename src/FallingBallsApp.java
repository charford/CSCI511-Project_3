import javax.swing.JFrame;

class FallingBallsApp extends JFrame {

  FallingBallsApp(BallManager manager) {

  }

  public static void main(String[] args) {
    FallingBallsApp application = new FallingBallsApp(new BallManager());
        
    System.out.println("This is a test");
  }
};
