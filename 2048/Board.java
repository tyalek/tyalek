import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.layout.GridPane;

/**
 * board for the 2048 game
 * @author Tyale Kitambi
 */
public class Board {
  
  /**board that stores a 2d array of integers*/
  private static int[][] valueBoard; 
  
  /**
   * Constructor that creates a valueBoard
   * @param rows: the number of rows for the board
   * @param cols: the number of columns for the board 
   */
  public Board(int rows, int cols) {
    valueBoard = new int[rows][cols]; 
  }
  
  /**
   * Creates a standard valueBoard with 4 rows and 4 columns
   */
  public Board() {
    valueBoard = new int[4][4];
  }
  
  /**
   * Initializes/Creates the board 
   * @param valueBoard: the 2d array of integers 
   */
  public static void setValueBoard(int[][] valueBoard) {
    valueBoard = valueBoard;
  }
  
  /**
   * Retrieves the board
   * @return valueBoard: the 2d array of integers
   */
  public static int[][] getValueBoard() {
    return valueBoard;
  }
  
  /**
   * Inserts the number 1 at the random position 
   */
  public static void insertRand() {
   // check if a tile is empty
    boolean isAdded = false;
    while(!isAdded){
      // random x coordinate
      int xCoord = (int)(Math.random()* getValueBoard().length);
      // random y caoordinate
      int yCoord = (int)(Math.random()* getValueBoard()[0].length);
      // assignments a random value of 1
      if (getValueBoard()[xCoord][yCoord] == 0 ){
        getValueBoard()[xCoord][yCoord] = 1;
        isAdded = true;
      }
    }
  }
  
  /**
   * Merges the values to the left 
   * @param input: the row and columns of the board that will be updated
   * @return input: the updated board with the merged values
   */
  public static int[][] mergeLeft(int [][] input){
    //loops through the row
    for (int row= 0; row < input.length; row++){
      //loops through the column
      for (int col = input[row].length - 1; col > 0; col--){
        int counter = 1;
        //increments and iterates through the tiles
        while (col-counter > -1 && (input[row][col] == input[row][col-counter] || input[row][col-counter] == 0)){
          if (input[row][col - counter] != 0 && input[row][col] == input[row][col - counter] && col != (col - counter)){
            input[row][col - counter] = input[row][col - counter] + input[row][col];
            input[row][col] = 0;
          }
          // the counter is incremented by 1
          counter++;
        }
      }
      // iterates through the entire column
      for (int k = 0; k < input[row].length; k++){
        int counter = k;
        //the column is flipped
        while (counter > 0 && input[row][counter - 1] == 0){
          input[row][counter - 1] = input[row][counter];
          input[row][counter] = 0;
          counter--;
        }
      }
    }
    //the board is updated to the new input
    setValueBoard(input);
    return input;
  }
  
  /**
   * Merges the values to the right 
   * @param input: the row and columns of the board that will be updated
   * @return input: the updated board with the merged values
   */
  public static int[][] mergeRight(int[][] input){
    
    //traversing the rows
    for (int i = 0; i < input.length; i++) {
      //the beginning and end of the rows
      int st = 0; 
      int end = input.length - 1; 
      
      //flips the elements from the beginning and end 
      while (st < end) {
        int temp = input[i][st]; 
        input[i][st] = input[i][end]; 
        input[i][end] = temp;
        
        st ++; 
        end--;
      }
    }
    //merges the board to the left
    mergeLeft(input);
    
    //repeating the earlier traversals
    for (int i = 0; i < input.length; i++) {
      
      int st = 0; 
      int end = input.length - 1; 
      
      while (st < end) {
        int temp = input[i][st]; 
        input[i][st] = input[i][end]; 
        input[i][end] = temp;
        
        st ++; 
        end--;
      }
    }
    //the board is updated to the new input
    setValueBoard(input); 
    return input;
  }
  
  
  /**
   * Merges the values to the right 
   * @param input: the row and columns of the board that will be updated
   * @return input: the updated board with the merged values
   */
  public int[][] mergeUp(int[][] input){
    for(int col =0; col<input.length; col++){
      for(int row =input[col].length-1;row>0;row--){
        int counter = 1;
        
        while (row-counter >-1 && (input[row][col] == input[row-counter][col] || input[row-counter][col] == 0)){
          if (input[row-counter][col] != 0 && input[row][col] == input[row-counter][col] && row!=(row-counter)){
            //incremente the tiles 
            input[row-counter][col] = input[row-counter][col] + input[row][col];
            input[row][col] = 0;
          }
          //incremented counter
          counter ++;
        }
      }
      //loop through the whole tile
      for (int k = 0; k< input[0].length;k++){
        int counter = k;
        while (counter>0 && input[counter-1][col] ==0){
          input[counter-1][col] = input[counter][col];
          input[counter][col] = 0;
          counter --;
        }
      }
    }
    //the board is updated to the new input
    setValueBoard(input);
    return input;   
  }
   
  /**
   * Merges the values to the right 
   * @param input: the row and columns of the board that will be updated
   * @return input: the updated board with the merged values
   */
  public  int[][] mergeDown(int[][] input){
    //flips the board 
    for (int i = 0; i < (input.length/2); i++){
      int[]flipped = input[i];
      input[i] = input[input.length - i- 1];
      input[input.length - i - 1]= flipped;
    }
    mergeUp(input);
    
    //flip back to correct rotation
    for (int i = 0; i < (input.length/2); i++){
      int[]flipped = input[i];
      input[i] = input[input.length - i - 1];
      input[input.length - i - 1]= flipped;
    }
    //the board is updated to the new input
    setValueBoard(input);
    return input;
  }
    
  /**
   * Merges tiles value together diagonally up to the left
   * @param input:  the row and columns of the board that will be updated
   * @return input:  the updated board with the merged values
   */
  public int[][] mergeDiagonalUpLeft(int[][] input){
    
    //traverses through the input to flip the values
    for(int i = 0; i < input.length; i++){
      for(int j = 0; j < input[i].length; j++){
        if(input[i][j] != 0){
          //stores position of the value
          int temp = input[i][j];
          //stores position of the leftmost side of the board
          int onLeft = j-1;
          //stores position of the upmost side of the board
          int atUp = i-1;
          
          //traverses through the positions of the array 
          while (atUp >= 0 && onLeft >= 0 && input[atUp][onLeft] == 0){
            input[atUp][onLeft] = temp;
            input[atUp + 1][onLeft + 1] = 0;
            onLeft--;
            atUp--;
          }
        }
      }
    }
    
    for (int h = 0; h < input.length; h++){
      for (int k = 0; k < input[h].length;k++){
        //adds the values together if they are the same
        if (input[h][k] != 0){
          int temp = input[h][k];
          int onLeft = k-1;
          int atUp = h-1;
          
          //adding the values
          while( atUp >= 0 && onLeft >= 0 && input[atUp][onLeft] != 0 && input[atUp + 1][onLeft + 1] !=0 && input[atUp][onLeft] == input[atUp+1][onLeft+1]){
            input[atUp][onLeft] = input[atUp][onLeft] + input[atUp + 1][onLeft + 1];
            input[atUp + 1][onLeft + 1] = 0; 
            onLeft --;
            atUp --;
          }
          
          //merges all the numbers to the topleft side
          while (atUp >= 0 && onLeft >= 0 && input[atUp][onLeft] == 0){
            input[atUp][onLeft] = temp;
            input[atUp + 1][onLeft + 1] = 0;
            onLeft--;
            atUp--;
          }
        }
      }
    }  
    //the board is updated to the new input
    setValueBoard(input);
    return input;
  }
  
  /**
   * Merges tiles value together diagonally down to the left
   * @param input:  the row and columns of the board that will be updated
   * @return input:  the updated board with the merged values
   */
  public int[][] mergeDiagonalDownLeft(int[][] input){
    //flips the integers up
    flipsUp(input);
    //merges the integers up right
    mergeDiagonalUpRight(input);
    //flips the input back to its orginal postion
    flipsUp(input);
    //the board is updated to the new input
    setValueBoard(input);
    
    return input;
    
  }
  
  /**
   * Merges tiles value together diagonally down to the left
   * @param input the rows and columns of the titles
   * @return merges values returned diagonally down to the left corner
   */
  public int[][] mergeDiagonalUpRight(int[][] input){
    
    //flips input to the right
    flipsRight(input);
    //merges the values diagonally towards the upper left side
    mergeDiagonalUpLeft(input);
    //flips input to the orignal postion
    flipsRight(input);
    //the board is updated to the new input
    setValueBoard(input);
    
    return input;
  }

  /**
   * Merges tiles value together diagonally dwon to the right
   * @param input the rows and columns of the titles
   * @return merges values returned diagonally down to the right corner
   */
  public int[][] mergeDiagonalDownRight(int[][] input){
    //flips the board up
    flipsUp(input);
    //merges the values diagonally towards the upper left side
    mergeDiagonalUpLeft(input);
    // flip back to original postion
    flipsUp(input);
    //the board is updated to the new input
    setValueBoard(input);
    return input;
  }
  
  /**
   * flip inputn to the right 
   * @param input value to be flipped
   */
  public void flipsRight(int[][] input){
    //traversing through each row 
    for (int i = 0; i< input.length; i++){
      //stores the start and end of the rows
      int start = 0;
      int end = input.length -1;
      
      //Until start equals end, the elements are swapped
      while (start < end){
        
        //swapping the elements
        int temp = input[i][start];
        input[i][start] = input[i][end];
        input[i][end] = temp;
        
        //increments 
        start++;
        //decrements
        end--;
      }
    }
  }
  
  /** 
   * Flips the input up
   * @param input value to be flipped
   */
  public void flipsUp(int[][]input){
    for (int i = 0 ;i< (input.length/2) ;i++){
      int[]flipped = input[i];
      input[i] = input[input.length-i-1];
      input[input.length-i-1] = flipped;
    }
  }
  
  @Override
  /** 
   * overridden toString method 
   */
    public String toString(){
    String s = ""; 
    for (int i = 0; i < valueBoard.length; i++) {
      for (int j = 0; j < valueBoard[i].length; j++) {
        s += valueBoard[i][j] + " ";
      }
      s += "\n";
    }
    return s;
  }
}



