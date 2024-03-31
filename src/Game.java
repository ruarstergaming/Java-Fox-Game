import java.util.Scanner;

public class Game {
    // The following five constants were defined in the starter code (kt54)
    private static String FOXPLAYS_MSG      = "Fox plays. Enter move:";
    private static String GEESEPLAY_MSG     = "Geese play. Enter move:";
    private static String ILLEGALMOVE_MSG   = "Illegal move!";
    private static String FOXWINS_MSG       = "Fox wins!";
    private static String GEESEWIN_MSG      = "Geese win!";
    
    //These variables are static variables to see if its the geese' turn and the char used to display fox and geese
    private static boolean gooseTurn;
    private static final char FOX     = '*';
    private static final char GOOSE   = 'o';

    private Board gameBoard;

    // Minimal constructor. Expand as needed (kt54)
    public Game() {
        gameBoard = new Board();
    }

    //Public method that manipulates the board and calls other method to play the game
    public void play() {

        //Initialise a scanner to use for user input
        Scanner reader = new Scanner(System.in);

        //Initialise a new gameboard
        gameBoard = new Board();

        //Declare and initialise a boolean value that says if the game is finished and stops the while loop
        boolean done = false;
        gooseTurn = true;
        
        //A conditional loop that as long as done is false repaet the code
        while(!done) {

            //Print the board out into the terminal
            gameBoard.printBoard();

            //If statement to see if its the geese' turn
            if(gooseTurn){

                //Print out a message asking the geese to enter a move
                System.out.println(GEESEPLAY_MSG);

                // Read the line as a string
                String command = reader.nextLine().trim();

                //If the user enters quit as the first coordinate then end the loop and quit the program
                if(command.equals("quit")){
                    done = true;
                }
                
                else{

                    //Otherwise assign the values the user entered as coordinates that move a goose to a new square
                    int currentXcoord = Integer.parseInt(command);
                    int currentYcoord = Integer.parseInt(reader.nextLine());
                    int newXcoord = Integer.parseInt(reader.nextLine());
                    int newYcoord = Integer.parseInt(reader.nextLine());

                    //Call the board method that returna a boolean to see if the move is legal
                    boolean isMoveLegal = gameBoard.isMoveLegal(currentXcoord, currentYcoord, newXcoord, newYcoord, gooseTurn);

                    //If it returns true alternate the turn so it will be the fox's turn next and actually make the move with the board method
                    if(isMoveLegal){
                        gameBoard.makeMove(currentXcoord, currentYcoord, newXcoord, newYcoord, gooseTurn);
                        gooseTurn = false;
                    }

                    //Otherwise print out a message saying the move is illegal
                    else{
                        System.out.println(ILLEGALMOVE_MSG);
                    }

                }
            }

            else{
                //Otherwise its the fox's turn so print out a message prompting the fox user to play 
                System.out.println(FOXPLAYS_MSG);

                // Read the line as a string
                String command = reader.nextLine().trim();

                //If the user enters quit as the first coordinate then end the loop and quit the program
                if(command.equals("quit")){
                    done = true;
                }

                else{

                    //Otherwise assign the values the user entered as coordinates that move the fox to a new square
                    int currentXcoord = Integer.parseInt(command);
                    int currentYcoord = Integer.parseInt(reader.nextLine());
                    int newXcoord = Integer.parseInt(reader.nextLine());
                    int newYcoord = Integer.parseInt(reader.nextLine());

                    //Call the board method that returna a boolean to see if the move is legal
                    boolean isMoveLegal = gameBoard.isMoveLegal(currentXcoord, currentYcoord, newXcoord, newYcoord, gooseTurn);

                    //If it returns true alternate the turn so it will be the geese' turn next and actually make the move with the board method
                    if(isMoveLegal){
                        gameBoard.makeMove(currentXcoord, currentYcoord, newXcoord, newYcoord, gooseTurn);
                        gooseTurn = true;
                    }

                    //Otherwise print out a message saying the move is illegal
                    else{
                        System.out.println(ILLEGALMOVE_MSG);
                    }

                }
                
               
            }

            //Once the move for the current turn has been made use the method from the board class to declare and assign a value to who the winner is
            char winner = gameBoard.checkWin();

            //If the method returns the char as the fox then end the game, print the board and display a fox wins message
            if(winner == FOX){
                gameBoard.printBoard();
                System.out.println(FOXWINS_MSG);
                done = true;
            }

            //Otherwise if the method returns the char as the goose then end the game, print the board and display a geese wins message
            else if(winner == GOOSE){
                gameBoard.printBoard();
                System.out.println(GEESEWIN_MSG);
                done = true;
            }

            //Otherwise no one will win so another if statement isnt needed
        }
    }

}
