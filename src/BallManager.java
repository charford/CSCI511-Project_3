/**
 *  Class BallManager
 *  @author Casey Harford
 *  @version 1.0
*/
class BallManager {
  
  public static final int MAX_SLOTS = 10;
  public static final int MAX_BALLS = 15;
  private FallingBall[][] slots;

  /**
   *  constructor for BallManager 
  */
  public BallManager() {

    /** create a new array to manage the FallingBalls */
    slots = new FallingBall[MAX_BALLS][MAX_SLOTS];

    /** set initial values to null, there are no balls yet */
    for(int i=0; i<MAX_BALLS; i++) {
      for(int j=0; j<MAX_SLOTS; j++) {
        slots[i][j] = null;
      }
    }
  }

  /**
   *  method for dropBall
   *  @param slot the slot to drop the ball in
  */
  public synchronized void dropBall(int slot) {

    /** drop the ball */
    slots[0][slot] = new FallingBall(slot,this);
  }

  /**
   *  method for killBall
   *  @param x  the x coordinate in the array to kill ball
   *  @param y  the y coordinate in the array to kill ball
   *  @return true if ball exists and was killed, or false if ball does not exist in that space
  */
  public synchronized boolean killBall(int x, int y) {
    
    /** if the space is currently occupied, then set to null, and return false */
    if(slots[y][x] != null) {
      slots[y][x] = null;
      return true;
    }
    /** if no ball exists, then return false, failed to kill a ball */
    else return false;
  }

  /**
   *  method for available
   *  @param slot the slot to check if ball exists
   *  @param row the row to check if ball exists
   *  @return true if available, or false if occupied
  */
  public synchronized boolean available(int slot, int row) {
    
    /** check if the space is available, if it is return true */
    if(slots[row][slot] != null) return false;
    
    /** not available, then return false */
    else return true;
  }

  /**
   *  method moveBall, moves the ball to new slot
   *  @param oldSlot  the old slot that ball was placed in
   *  @param oldPos  the old position(row) that ball was placed in
   *  @param newSlot  the new slot to place ball
   *  @param newPos  the new position(row) to place ball
  */
  public synchronized void moveBall(int oldSlot, int oldPos, int newSlot, int newPos) {

    /** first assign ball to new position  */
    slots[newPos][newSlot] = slots[oldPos][oldSlot];

    /** set old position to null(available) */
    slots[oldPos][oldSlot] = null;
  }

  /**
   *  method getSlots
   *  @return a clone of FallingBall[][] slots
  */
  public synchronized FallingBall[][] getSlots() {
    
    /** return a clone of the slots[]][] array */
    return slots.clone();
  }
}
