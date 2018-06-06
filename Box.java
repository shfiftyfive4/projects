/**
 * creates a box object representing a briefcase in the game "Deal or No Deal"
 * @author Carl Cutler
 */
public class Box {

  /** the monetary value of the box */
  private double value;
  /** whether the box has been opened to be seen by the player */
  private boolean boxOpen;
  
  /** 
   * box object constructor creates a new box with given value and is closed
   * @param value the given monetary value
   */
  public Box(double value) {
    this.value = value;
    boxOpen = false;
  }
  
  /**
   * getter method for the value of a box
   * @return value 
   */
  public double getValue() {
    return value;
  }
  
  /** 
   * returns whether the box is open
   * @return boxOpen 
   */
  public boolean isOpen() {
    return boxOpen;
  }
  
  /** opens a box */
  public void open() {
    boxOpen = true;
  }
  
  /**
   * a to string method to display the variables for a box
   * @return a sentence with box information
   */
  public String toString() {
    return "Open: " + boxOpen + " Value: " + value;
  }
}
  
    

  