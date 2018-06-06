import java.util.*;
import java.io.*;
/**
 * provides methods for the functioning of the game
 * @author Carl Cutler
 */
public class DealGame {

  /** This is the number of boxes in the game */
  public static final int NUM_BOXES = 26;
  
  /** These are the values that will be used in the game */
  public static final double[] BOX_VALUES = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000,
                                             25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000}; 
  /** These are the number of boxes to be opened in each round */
  public static final int[] BOXES_IN_ROUND = {0, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1}; 
  
  /** This is the number of rounds in the game */
  public static final int NUM_ROUNDS = 10;
  
  /** This is the number of times boxes are swapped during the set up */
  private static final int BOX_SWAPS = 500;
  
  /** This is the name of the file that contains the high score */
  public static final String HIGH_SCORE_FILE = "highscore.txt";
  
  /** whether or not the player has chosen a box */
  private boolean chosenBox;
  
  /** the box index the player owns */
  private int playerIndex;
  
  /** the current round number */
  private int roundNumber;
  
  /** the number of boxes open in the current round, the number of boxes opened in all rounds */
  private int openInRound, totalOpen;
  
  /** the highscore of all games played */
  private double highScore;
  
  /** instance of the BoxList class, passed BOX_VALUES */
  private static BoxList list;
  
  public static void main(String[] args) {
    // tests
    list = new BoxList(BOX_VALUES);
    System.out.println(list);
  }
  
  /**
   * constructs the beginning of the game
   * @param testing whether the current game is a test
   */
  public DealGame(boolean testing) {
    Scanner input;
    list = new BoxList(BOX_VALUES);
    roundNumber = 1;
    openInRound = 0;
    totalOpen   = 0;
    
    if (!testing)
      list.shuffle(BOX_SWAPS);
    
    input = getInputScanner();
    if (input == null)
      highScore = 0;
    else
      highScore = input.nextDouble();
  }
  
  /**
   * getter method for whether the user has chosen a box
   * @return chosenBox
   */
  public boolean hasPlayerChosenBox() {
    return chosenBox;
  }
  
  /**
   * selects a box
   * @param index
   */
  public void selectBox(int index) {
    if (!chosenBox) {
      playerIndex = index;
      chosenBox = true;
    }
    else {
      list.open(index);
      ++openInRound;
      ++totalOpen;
    }
  }
  
  /**
   * calculates the number of boxes remaining to be opened this round
   * @return closed boxes in round
   */
  public int getBoxesRemainingToOpenThisRound() {
    return BOXES_IN_ROUND[roundNumber] - openInRound;
  }
  
  /**
   * gets the boxes currently open in the round
   * @return openInRound
   */
  public int getBoxesOpenedThisRound() {
    return openInRound;
  }
  
  /**
   * gets the round number
   * @return roundNumber
   */
  public int getRound() {
    return roundNumber;
  }
  
  /** increments the round number and begins new round */
  public void startNextRound() {
    ++roundNumber;
    openInRound = 0;
  }
  
  /**
   * checks if the round has ended
   * @return the difference between the total to be opened and the number opened
   */
  public boolean isEndOfRound() {
    return BOXES_IN_ROUND[roundNumber] == openInRound;
  }
  
  /**
   * gets the value of the player's box
   * @return player box value
   */
  public double getPlayerBoxValue() {
    return list.getValue(playerIndex);
  }
  
  /**
   * checks to see if a box is open
   * @param index
   * @return whether box is open
   */
  public boolean isBoxOpen(int index) {
    return list.isOpen(index);
  }
  
  /**
   * gets value of a given box
   * @param index
   * @return the value
   */
  public double getValueInBox(int index) {
    return list.getValue(index);
  }
  
  /** @return current bank offer */
  public double getCurrentOffer() {
    return list.averageValueOfUnopenedBoxes() * roundNumber / 10;
  }
  /** @return highScore the high score of the game */
  public double getHighScore() {
    return highScore;
  }
  
  /**
   * writes to high score file if score is a high score
   * @param value the score of the game
   * @return whether score was a high score
   */
  public boolean isNewHighScore(double value) {
    PrintStream out;
    
    if (value > highScore) {
      out = getOutputPrintStream();
      out.println(value);
      highScore = value;
      return true;
    }
    else
      return false;
  }
  
  /**
   * gets a printstream to write to the high score file
   * @return out
   */
  public PrintStream getOutputPrintStream() {
    PrintStream out = null;
    
    try {
      out = new PrintStream(new File(HIGH_SCORE_FILE));
    }
    catch (FileNotFoundException e) {
    }
    
    return out;  	
  }
  
  /**
   * gets an input scanner for the high score file
   * @return in
   */
  public Scanner getInputScanner() {
    Scanner in = null;
    
    try {
      in = new Scanner(new File(HIGH_SCORE_FILE));
    }
    catch (FileNotFoundException e) {
    }
    
    return in;	
  }
}
