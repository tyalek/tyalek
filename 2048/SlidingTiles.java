import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.util.List; 
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;


/**
 * THEE better version of the 2048 game 
 * @author Tyale Kitambi
 */
public class SlidingTiles extends Application {
  
  /**an array of buttons */
  private Button[][] buttons;
  
  /** default number of rows for the grid */
  private int row = 4; 
  
  /** default number of columns for the grid */
  private int col = 4; 
  
  /**an array of integers */
  private int[][] intArray;
  
  /**an board of integers storing an array of integers */
  private Board intBoard = new Board(row, col);
  
  /** the stage for the game*/
  private Stage stage; 
  
  /**grid that is used to store the buttons*/
  private GridPane gridPane = new GridPane(); 
  
  /**an array of buttons */
  private BorderPane pane = new BorderPane(); 
  private Scene scene; 
  
  private Button[][] arrowButtons = new Button[3][3];
  
  private GridPane arrows = new GridPane();
  
  /**
   * Creates the JavaFX application with The 2048 Game
   * @param primaryStage the main window
   */
  public void start(Stage stage) {
    
    //creates and stores the users inputs
    List<String> args = this.getParameters().getRaw();
    
    //automatically creates a 4 by 4 board if no user input
    if (args.isEmpty()) { 
      intBoard = new Board(4,4); 
    }
    //creates the board with the user input
    else {
      intBoard = new Board(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
      row = Integer.parseInt(args.get(0));
      col = Integer.parseInt(args.get(1));
    }
    
    //stores the integer values of the Board in another board
    intArray = intBoard.getValueBoard(); 
    
    //creates the buttons that have the inputs from the 
    buttons = intToButton(intBoard.getValueBoard());
    
    //for loop that creates a gridpane of buttons
    for (int row = 0; row < buttons.length; row++) {
      for (int col = 0; col < buttons[row].length; col++) {
        gridPane.add(buttons[row][col], row, col); 
        buttons[row][col].setMinSize(100,100);
        buttons[row][col].setFont(Font.font("Avenir", FontWeight.BOLD, 28.0));
      }
    } 
    /**NEW STUFF **/
    //for loop that creates arrow buttons 
    for (int a = 0; a < arrowButtons.length; a++) {
      for (int b = 0; b < arrowButtons[a].length; b++) {
        //nyan cat gif
        ImageView image = new ImageView("https://thumbs.gfycat.com/AccurateAgreeableDairycow-max-1mb.gif");
        image.setFitHeight(50);
        image.setFitWidth(50);
        //right button
        if (a == 2 && b == 1) {  
        arrowButtons[a][b] = new Button("",image);
        image.setRotate(0);
        
        }
        //left button
        else if (a == 0 && b == 1) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 180 degrees
        image.setRotate(180);
        //rotating the image abt the y axis
        image.setRotationAxis(Rotate.Y_AXIS);
        }
        //top button 
        else if (a == 1 && b == 0) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 270 degrees
        image.setRotate(270);
        }
        //bottom button
        else if (a == 1 && b == 2) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 90 degrees
        image.setRotate(90);
        } 
        //up left button
        else if (a == 0 && b == 0) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 225 degrees
        image.setRotate(225);
        }
        //middle button
        else if (a == 1 && b == 1) {  
        ImageView img = new ImageView("https://media.tenor.com/images/353c7c3a34cc6f89517d8d872a9926e9/tenor.gif");
        img.setFitHeight(50);
        img.setFitWidth(50);
        arrowButtons[a][b] = new Button("",img);
        }
        //down left button 
        else if (a == 0 && b == 2) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 135 degrees
        image.setRotate(135);
        }
        //up right button
        else if (a == 2 && b == 0) {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 315 degrees
        image.setRotate(315);
        }
        //down right button 
        else  {  
        arrowButtons[a][b] = new Button("",image);
        //rotating the image 45 degrees
        image.setRotate(45);
        }
        //adding the buttons to the gridpane
        arrows.add(arrowButtons[a][b], a, b);
        //customizing gridpane size
        arrowButtons[a][b].setMinSize(50,50);
      }
    }
    
    //sliding right
    arrowButtons[2][1].setOnAction( e -> {
        //note: mergeDown only works here
        intBoard.mergeDown(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        //for testing purposes
        System.out.println(intBoard);
      }
      );
    
    //sliding left
    arrowButtons[0][1].setOnAction( e -> {
        //note: mergeUp only works here
        intBoard.mergeUp(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    //sliding up
    arrowButtons[1][0].setOnAction( e -> {
        //note: mergeLeft only works here
        intBoard.mergeLeft(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    //sliding down
    arrowButtons[1][2].setOnAction( e -> {
        //note: mergeRight only works here
        intBoard.mergeRight(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    
    //sliding diagonal up lefthand side
    arrowButtons[0][0].setOnAction( e -> {
        //note: mergeRight only works here
        intBoard.mergeDiagonalUpLeft(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    
    //sliding diagonal up righthand side
    arrowButtons[2][0].setOnAction( e -> {
        //note: mergeDiagonalDownRight only works here
        intBoard.mergeDiagonalDownRight(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    //sliding down diagonal lefthand side
    arrowButtons[0][2].setOnAction( e -> {
        //note: mergeDiagonalUpRight only works here
        intBoard.mergeDiagonalUpRight(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
    
    //sliding diagonal down righthand side
    arrowButtons[2][2].setOnAction( e -> {
        //note: mergeDiagonalDownLeft only works here
        intBoard.mergeDiagonalDownLeft(intArray); 
        intBoard.insertRand();
        updateValues(intBoard, gridPane);
        System.out.println(intBoard);
      }
      );
    
   
    //creates the title of the window
    stage.setTitle("TYALE'S 2048"); 
 //   VBox root = new VBox();
  //  ImageView imageView = new ImageView("https://i.pinimg.com/originals/44/d7/a6/44d7a60b7f6b45d2012137bcbe9f5cfa.gif");
    pane.setCenter(gridPane);
  //  root.getChildren().addAll(imageView);
   pane.setRight(arrows);
    // Create a "scene" that contains this border area
    Scene scene = new Scene(pane); 
    
    // Add the "scene" to the main window
    stage.setScene(scene); 
    
    // Display the window
    stage.show();                    
  }
  
  /**
   * Helper method that updates the values on the board
   * @param b: Board input
   * @param g: the gridpane used to store the values
   */
  public void updateValues(Board b, GridPane g){
    //traverses through the 
    for (int i = 0; i < intBoard.getValueBoard().length; i++) {
      for (int j = 0; j < intBoard.getValueBoard()[i].length; j++) {
        if (intBoard.getValueBoard()[i][j] != 0) {
          setNumColors(); 
          buttons[i][j].setText(String.valueOf(intArray[i][j]));
        }
        else {
          buttons[i][j].setText(""); 
        }
      }
    }
  }
  
  /**
   * Helper method that changes int array to buttons
   * @param array: array of integers
   * @return an array of buttons 
   */
  private Button[][] intToButton(int[][] array) {
    buttons = new Button[array.length][array[0].length];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        buttons[i][j] = new Button("" + array[i][j]);
      }
    }
    return buttons;
  }
  
  
  /**!!! EXTRA CREDIT !!!**
    * Method sets the colors for the grid and its values
    */
  public void setNumColors(){ 
    for (int i = 0; i < intBoard.getValueBoard().length; i++) {
      for (int j = 0; j < intBoard.getValueBoard()[i].length; j++) {
        buttons[i][j].setTextFill(Paint.valueOf("white"));
        if (intBoard.getValueBoard()[i][j] == 0) 
          buttons[i][j].setStyle("-fx-base: #fffff;"); 
        else if (intBoard.getValueBoard()[i][j] == 1) 
          buttons[i][j].setStyle("-fx-base: #f9e6ff;");
        else if (intBoard.getValueBoard()[i][j] == 2) 
          buttons[i][j].setStyle("-fx-base: #f1c5ff;");  
        else if (intBoard.getValueBoard()[i][j] == 4) 
          buttons[i][j].setStyle("-fx-base: #ECBFFA;");
        else if (intBoard.getValueBoard()[i][j] == 8) 
          buttons[i][j].setStyle("-fx-base: #E9A4FF;");
        else if (intBoard.getValueBoard()[i][j] == 16) 
          buttons[i][j].setStyle("-fx-base: #E592FF;");
        else if (intBoard.getValueBoard()[i][j] == 32) 
          buttons[i][j].setStyle("-fx-base: #DB69FF;");
        else if (intBoard.getValueBoard()[i][j] == 64) 
          buttons[i][j].setStyle("-fx-base: #D44DFF;");
        else if (intBoard.getValueBoard()[i][j] == 128) 
          buttons[i][j].setStyle("-fx-base: #FFA4EE;");
        else if (intBoard.getValueBoard()[i][j] == 256) 
          buttons[i][j].setStyle("-fx-base: #FF77E6;");
        else if (intBoard.getValueBoard()[i][j] == 512) 
          buttons[i][j].setStyle("-fx-base: #FF5FE2;");
        else if (intBoard.getValueBoard()[i][j] == 1024) 
          buttons[i][j].setStyle("-fx-base: #FF31DA;");
        else if (intBoard.getValueBoard()[i][j] == 2048) 
          buttons[i][j].setStyle("-fx-base: #FF00D1;");
        else 
          buttons[i][j].setStyle("-fx-base: #C200B4;");        
      }     
    }
  } 
  
  /**
   * Launch the JavaFX application
   * @param args the command line arguments are currently ignored
   */
  public static void main(String[] args) {
    Application.launch(args);
  }
  
  
}