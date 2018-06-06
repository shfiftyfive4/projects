import java.util.*;
import java.io.*;
/**
 * creates an array of box objects
 * @author Carl Cutler
 */
public class BoxList {

  /** array of box objects */
  private Box[] boxes;

  /**
   * constructor for the array of briefcases
   * @param monetaryAmounts a constant array of given monetary values
   */
  public BoxList(double[] monetaryAmounts) {
    boxes = new Box[monetaryAmounts.length];
    for (int i = 0; i < boxes.length; ++i) {
      Box box = new Box(monetaryAmounts[i]);
      boxes[i] = box;
    }
  }
  
  /**
   * getter method for the value of a box of a given index
   * @param index the position in the boxes array
   * @return the value
   */
  public double getValue(int index) {
    return boxes[index].getValue();
  }
  
  /**
   * whether a box of a given index is open
   * @param index
   * @return whethee the box is open
   */
  public boolean isOpen(int index) {
    return boxes[index].isOpen();
  }
  
  /**
   * opens a box of given index
   * @param index
   */
  public void open(int index) {
    boxes[index].open();
  }
  
  /**
   * calculates the average value of all currently unopened boxes
   * @return average
   */
  public double averageValueOfUnopenedBoxes() {
    int c = 0;
    double average = 0;
    
    for (int i = 0; i < boxes.length; ++i) {
      if (boxes[i].isOpen() == false) {
        average += boxes[i].getValue();
        ++c;
      }
    }
    
    return average / c;
  }
  
  /**
   * shuffles the boxes array
   * @param numberOfSwaps
   */
  public void shuffle(int numberOfSwaps) {
    Random rand = new Random();
    
    for (int i = 0; i < numberOfSwaps; ++i) {
      int first = rand.nextInt(boxes.length - 1);
      int second = rand.nextInt(boxes.length - 1);      
      
      Box temp      = boxes[first];
      boxes[first]  = boxes[second];
      boxes[second] = temp;
    }
  }
  
  /**
   * writes out every box in the boxes array
   * @return the string of boxes
   */
  public String toString() {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < boxes.length; ++i) 
        sb.append(boxes[i].toString() + "\n");
        
      return sb.toString();
  }
  
  public static void main(String[] args) {
    //System.out.println(boxes.toString());
  }
}
