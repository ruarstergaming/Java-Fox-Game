public class Board {

    // The following five constants were defined in the starter code (kt54)
    private static final int  BOARD_SIZE = 7;
    private static final char FREE    = '.';
    private static final char INVALID = ' ';
    private static final char FOX     = '*';
    private static final char GOOSE   = 'o';

    private int boardsize; 
    private char[][] board;

    // Default constructor was provided by the starter code. Extend as needed (kt54) 
    public Board() {
        this.boardsize = BOARD_SIZE;

        board = new char[boardsize][boardsize];

        // Clear all playable fields
        for(int x=0; x<boardsize; x++){
            for(int y=0; y<boardsize; y++){
                board[x][y] = FREE;
            }
        }

        // Assign geese to correct places 
        for(int x=0; x<(boardsize); x++){
            for(int y=0; y<boardsize; y++){

                if(y < 3 || y == 3 && x < 2 || y == 3 && x > 4){
                    board[x][y] = GOOSE;
                }

            }
        }

        // Empty the corners of the board 
        for(int x=0; x<(boardsize); x++){
            for(int y=0; y<boardsize; y++){

                if(x < 2 && y < 2 || x > 4 && y < 2 || x < 2 && y > 4 || x >4 && y > 4){
                    board[x][y] = INVALID;
                }

            }
        }


        // Put a single fox in the middle
        board[boardsize/2][boardsize*5/7] = FOX;
    }

    // Prints the board. This method was provided with the starter code. Better not modify to ensure
    // output consistent with the autochecker (kt54)
    public void printBoard() {

        // A nested loop fo each square of the board
        for(int y=0; y<boardsize; y++){
            for(int x=0; x<boardsize; x++) {
                
                // Print a space between each piece of the board to imporve player experience
                System.out.print(" ");

                // A switch statement to print out the correct piece at the current part of the board
                switch(board[x][y]) {
                    case FREE: 
                        System.out.print(".");
                        break;
                    case FOX:
                        System.out.print("*");
                        break;
                    case GOOSE:
                        System.out.print("o");
                        break;
                    default:
                        System.out.print(" ");
                }
            }
            //After printing out the first line start a new line
            System.out.println();
        }
    }

    // Public method that returns a boolean to check if a move made by a player is a legal move    
    public boolean isMoveLegal(int currentXcoord, int currentYcoord, int newXcoord, int newYcoord, boolean gooseTurn){
        
        //A boolean value that says if a move is legal or not
        boolean isLegal = false;

        //Check to see if its the geese' turn
        if(gooseTurn){

            //Check if the coordinates the user entered are out of bounds
            if(currentXcoord >= 0 && currentXcoord < BOARD_SIZE && currentYcoord >=0 && currentYcoord < BOARD_SIZE && newXcoord >= 0 && newXcoord < BOARD_SIZE && newYcoord >=0 && newYcoord < BOARD_SIZE){
                //A nested loop to check each square adjacent to the current square 
                for(int i = currentXcoord-1; i <= currentXcoord+1; i++){
                    for(int j = currentYcoord-1; j<= currentYcoord+1; j++){

                        //If statement to check if the current adjacent square is within bounds of the array
                        if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){

                            //Another if statment to check if the current place actaually has a goose and the place they are moving is both free and actually adjacent
                            if(board[currentXcoord][currentYcoord] == GOOSE && board[newXcoord][newYcoord] == FREE && i == newXcoord && j == newYcoord){
                            
                                //If all the checks pass then the move is legal.
                                isLegal = true;
                            
                            }
                        }

                    }
                }
            }

        }

        //Otherwise it will be the foxes turn
        else{

            //Check if the coordinates the user entered are out of bounds
            if(currentXcoord >= 0 && currentXcoord < BOARD_SIZE && currentYcoord >=0 && currentYcoord < BOARD_SIZE && newXcoord >= 0 && newXcoord < BOARD_SIZE && newYcoord >=0 && newYcoord < BOARD_SIZE){

                //A nested loop to check each square adjacent to the current square 
                for(int i = currentXcoord-1; i <= currentXcoord+1; i++){
                    for(int j = currentYcoord-1; j<= currentYcoord+1; j++){
                    
                        //If statement to check if the current adjacent square is within bounds of the array
                        if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){

                            //Another if statment to check if the current place actaually has a fox and the place they are moving is both free and actually adjacent
                            if(board[currentXcoord][currentYcoord] == FOX && board[newXcoord][newYcoord] == FREE && i == newXcoord && j == newYcoord){
                        
                                //If all the checks pass then the move is legal.
                                isLegal = true;
                            
                            }
                        }

                    }
                }
            
                //A nested loop to check each square that is 2 boxes away adjacent to the current square i.e. the square the fox can jump to if its taking a goose 
                for(int i = currentXcoord-2; i <= currentXcoord+2; i++){
                    for(int j = currentYcoord-2; j<= currentYcoord+2; j ++){
                    
                        //If statement to check if the current adjacent square is within bounds of the array
                        if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){

                            //Another if statment to check if the current place actaually has a fox and the place they are moving is both free and actually adjacent
                            if(board[currentXcoord][currentYcoord] == FOX && board[newXcoord][newYcoord] == FREE &&  i == newXcoord && j == newYcoord){
                            
                                //An if statements to see if adjacent square the loop is currently on is bottom left  
                                if(i == currentXcoord-2 && j == currentYcoord-2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord-1][currentYcoord-1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }

                            
                                //An if statements to see if adjacent square the loop is currently on is middle left  
                                else if(i == currentXcoord-2 && j == currentYcoord){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord-1][currentYcoord] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }
                            

                                //An if statements to see if adjacent square the loop is currently on is top left  
                                else if(i == currentXcoord-2 && j == currentYcoord+2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord-1][currentYcoord+1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }


                                //An if statements to see if adjacent square the loop is currently on is bottom middle  
                                else if(i == currentXcoord && j == currentYcoord-2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord][currentYcoord-1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }


                                //An if statements to see if adjacent square the loop is currently on is top middle                              
                                else if(i == currentXcoord && j == currentYcoord+2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord][currentYcoord+1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }


                                //An if statements to see if adjacent square the loop is currently on is bottom right  
                                else if(i == currentXcoord+2 && j == currentYcoord-2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord+1][currentYcoord-1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }


                                //An if statements to see if adjacent square the loop is currently on is middle right  
                                else if(i == currentXcoord+2 && j == currentYcoord){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord+1][currentYcoord] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }
                            

                                //An if statements to see if adjacent square the loop is currently on is top right  
                                else if(i == currentXcoord+2 && j == currentYcoord+2){

                                    //If so then check if the fox is actually jumping over a goose
                                    if(board[currentXcoord+1][currentYcoord+1] == GOOSE){

                                        //Then if all of those if statements pass then the move is legal
                                        isLegal = true; 
                                    }
                                }
                            
                            }
                        }

                        //Increment the loop so it only checks the adjacent squares so that the goose is jumping in a straight line
                        j++;
                    }

                    //Increment the loop so it only checks the adjacent squares so that the goose is jumping in a straight line
                    i++;
                }
            }
        }

        //Then return the boolean value if the move is legal
        return isLegal;
    }

    //A public method that makes the move with the given coordinates the player entered and is only called if the move is legal
    public void makeMove(int currentXcoord, int currentYcoord, int newXcoord, int newYcoord, boolean gooseTurn){

        //Since the move is legal set the square the player is moving a piece from to a free space
        board[currentXcoord][currentYcoord] = FREE;

        //If the move is being made by a goose then set the space the goose is moving to, to a goose
        if(gooseTurn){    
            board[newXcoord][newYcoord] = GOOSE;
        }

        //Otherwise its a fox thats moving
        else{

            //Check if the coordinates the user entered are out of bounds
            if(currentXcoord >= 0 && currentXcoord < BOARD_SIZE && currentYcoord >=0 && currentYcoord < BOARD_SIZE && newXcoord >= 0 && newXcoord < BOARD_SIZE && newYcoord >=0 && newYcoord < BOARD_SIZE){

                //A nested loop to check each square that is 2 boxes away adjacent to the current square i.e. the square the fox can jump to if its taking a goose 
                for(int i = currentXcoord-2; i <= currentXcoord+2; i++){
                    for(int j = currentYcoord-2; j<= currentYcoord+2; j ++){
                    
                        //To prevent errors make sure the current part of the array is in bounds
                        if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){
                       
                            //Another if statment to check if the current place actaually has a fox and the place they are moving is both free and actually adjacent
                            if(i == newXcoord && j == newYcoord){

                                //An if statements to see if adjacent square the loop is currently on is bottom left
                                if(i == currentXcoord-2 && j == currentYcoord-2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord - 1][currentYcoord - 1] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is middle left
                                else if(i == currentXcoord-2 && j == currentYcoord){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord - 1][currentYcoord] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is top left
                                else if(i == currentXcoord-2 && j == currentYcoord+2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord - 1][currentYcoord + 1] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is bottom middle
                                else if(i == currentXcoord && j == currentYcoord-2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord][currentYcoord - 1] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is top middle
                                else if(i == currentXcoord && j == currentYcoord+2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord][currentYcoord + 1] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is bottom right
                                else if(i == currentXcoord+2 && j == currentYcoord-2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord + 1][currentYcoord - 1] = FREE;
                                }


                                //An if statements to see if adjacent square the loop is currently on is middle right
                                else if(i == currentXcoord+2 && j == currentYcoord){
                            
                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord + 1][currentYcoord] = FREE;
                                }

                        
                                //An if statements to see if adjacent square the loop is currently on is top right
                                else if(i == currentXcoord+2 && j == currentYcoord+2){

                                    //If so then set the space of the goose the fox is jumping over to free
                                    board[currentXcoord + 1][currentYcoord + 1] = FREE;
                                }
                            }
                                                    
                        }

                        //Increment the loop so it only checks the adjacent squares so that the goose is jumping in a straight line
                        j++;
                    }

                    //Increment the loop so it only checks the adjacent squares so that the goose is jumping in a straight line
                    i++;
                }
            }
            //Then set the location the piece is moxing to to a fox
            board[newXcoord][newYcoord] = FOX;

        }
    }
    


    //Public method to check if a player has one or not and if so which one and returns the char of the player who won or a a free char if no one has won
    public char checkWin(){

        //Declare a char which will be returned which is initialised as goose being the winner
        char winner = GOOSE;

        //Delcare and initialise int values for the number of geese left, the x and y coordinate of the fox
        int noGeseeLeft = 0;
        int foxLocationX = 0;
        int foxLocationY = 0;

        //A nested loop that checks each square of the board
        for(int y=0; y<boardsize; y++){
            for(int x=0; x<boardsize; x++) {
                
                //If the current square is a goose add 1 to the number of geese left
                if(board[x][y] == GOOSE){
                    noGeseeLeft++;
                }
                
                //If the current square is a fox set the first index to the x coordinate of the fox and the second index to the y coordinate of the fox
                if(board[x][y] == FOX){
                    foxLocationX = x;
                    foxLocationY = y;
                }

            }
        }
        
        //A nested loop that goes through each adjacent square to the fox
        for(int i = foxLocationX-1; i <= foxLocationX+1; i++){
            for(int j = foxLocationY-1; j<= foxLocationY+1; j++){
                
                //Check if the current adjacent square is in bounds
                if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){
                    
                    //Then check if the current adjacent square is free and if so then the fox can move so the geese dont win 
                    if(board[i][j] == FREE){
                        winner = FREE;                         
                    }
                }

            }
        }

        //A nested loop to check each square that is 2 boxes away adjacent to the current square i.e. the square the fox can jump to if its taking a goose 
        for(int i = foxLocationX-2; i <= foxLocationX+2; i+=2){
            for(int j = foxLocationY-2; j<= foxLocationY+2; j +=2){
                
                //Check if the current adjacent square is in bounds
                if(i >= 0 && i < BOARD_SIZE && j >=0 && j < BOARD_SIZE){
                    
                    //Then using if statements see if the fox can make a legal move where they take a goose wuth if statments checking each adjacent square if there is a goose to jump over.
                    if(board[foxLocationX][foxLocationY] == FOX && board[foxLocationX][foxLocationY] == FREE && board[i][j] == board[foxLocationX][foxLocationY]){
                        
                        if(i == foxLocationX-2 && j == foxLocationY-2){
                            if(board[foxLocationX-1][foxLocationY-1] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX-2 && j == foxLocationY){
                            if(board[foxLocationX-1][foxLocationY] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX-2 && j == foxLocationY+2){
                            if(board[foxLocationX-1][foxLocationY+1] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX && j == foxLocationY-2){
                            if(board[foxLocationX][foxLocationY-1] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX && j == foxLocationY+2){
                            if(board[foxLocationX][foxLocationY+1] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX+2 && j == foxLocationY-2){
                            if(board[foxLocationX+1][foxLocationY-1] == GOOSE){
                                winner = FREE;                             
                            }
                        }

                        else if(i == foxLocationX+2 && j == foxLocationY){
                            if(board[foxLocationX+1][foxLocationY] == GOOSE){
                                winner = FREE;  
                            }
                        }
                        

                        else if(i == foxLocationX+2 && j == foxLocationY+2){
                            if(board[foxLocationX+1][foxLocationY+1] == GOOSE){
                                winner = FREE; 
                            }
                        }
                        
                    }
                }

            }

        }

        //If all the geese have been eliminated then the winner is the fox
        if(noGeseeLeft == 0){
            winner = FOX;
        }

        //return the player who won
        return winner;
    }
}
