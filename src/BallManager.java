class BallManager {
  
  public static final int MAX_SLOTS = 10;
  public static final int MAX_BALLS = 15;
  private FallingBall[][] slots;

  public BallManager() {
    System.out.println("BallManager constructor");
    slots = new FallingBall[MAX_BALLS][MAX_SLOTS];
    for(int i=0; i<MAX_BALLS; i++) {
      for(int j=0; j<MAX_SLOTS; j++) {
        slots[i][j] = null;
      }
    }
  }

  public synchronized void dropBall(int slot) {
    System.out.println("BallManager dropBall(" + slot + ")");
    slots[MAX_BALLS-1][slot] = new FallingBall(slot,this);
  }

  public synchronized boolean killBall(int x, int y) {
    System.out.println("BallManager killBall()");
    return true;
  }

  public synchronized boolean available(int slot, int row) {
    System.out.println("BallManager available() slot = " + slot + ", row = " + row);
    if(slots[row][slot] != null) { System.out.println("returned true"); return true; }
    else { System.out.println("returned false"); return false; } 
    
  }

  public synchronized void moveBall(int oldSlot, int oldPos, int newSlot, int newPos) {
    System.out.println("BallManager moveBall");
    
  }

  public synchronized FallingBall[][] getSlots() {
    return slots;
  }

};
