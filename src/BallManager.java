class BallManager {
  
  public static final int MAX_SLOTS = 10;
  public static final int MAX_BALLS = 15;
  private FallingBall[][] slots;

  public BallManager() {
    System.out.println("BallManager constructor");
    slots = new FallingBall[MAX_BALLS][MAX_SLOTS];
  }

  public void dropBall(int slot) {
    System.out.println("BallManager dropBall(" + slot + ")");
    slots[MAX_BALLS-1][slot] = new FallingBall(slot,this);
    slots[MAX_BALLS-1][slot].run();
  }

  public boolean killBall(int x, int y) {
    System.out.println("BallManager killBall()");
    return true;
  }

  public boolean available(int slot, int position) {
    System.out.println("BallManager available()");
    return true;
  }

  public void moveBall(int oldSlot, int oldPos, int newSlot, int newPos) {
    System.out.println("BallManager moveBall");
    
  }

};
