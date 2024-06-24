import java.awt.*;

public class Board {

    // Instance variables
    private Piece[][] board;

    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        Piece inPlace = board[row][col];
        return inPlace;
    }

    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        // gets the piece that is going
        Piece pieceMove = getPiece(startRow, startCol);
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol) == true){ // checks if the piece ia able to move
            System.out.println("It works");
            // sets the piece to the new location and sets the old location to null
            setPiece(endRow, endCol, pieceMove);
            pieceMove.setPosition(endRow, endCol);
            board[startRow][startCol] = null;
            return true;
        }
        System.out.println("It does not work");
        return false;
    }

    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingCount = 0; // count of kings on the board
        for (int i = 0; i < board.length; i++){ // loops through the entire board to see if there are kings
            for (int j = 0; j < board[i].length; j++){
                Piece compare = getPiece(i,j); // gets the piece at each cell on the board
                if(compare == null){ // if the space is null continue the loop
                    continue;
                }
                if ((compare.getCharacter() == '\u265a') || (compare.getCharacter() == '\u2654')){ // if the piece is a king add to count
                    kingCount+=1;
                }
            }
        }
        if (kingCount <= 1){ // if the king count is below one, end game
            return true;
        }
        return false;
    }

    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String printBoard = ""; // string that will be printed out containing the board
        System.out.println("   0 1 2 3 4 5 6 7"); // number of columns
        for (int i = 0; i < board.length; i++){ // add the rows to the string
            printBoard += (i + " ");
            for (int j = 0; j < board[i].length; j++){ // add the columns to the string
                if (board[i][j] == null){ // if a spot on the board is null make leave it empty
                    printBoard+=("| ");
                } else {
                    printBoard+=("|" + board[i][j]); // if there is a board piece make it show up on the board
                }
            }
            printBoard += "|\n"; // newline for each row on the board
        }
        return printBoard;
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < board.length; i++){ // nested loop to set every cell in the board to null
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // check if each point is in the board
        if (((0 <= startRow && startRow < board.length) && (0 <= startCol && startCol < board.length)) && ((0 <= endRow && endRow < board.length) && (0 <= endCol && endCol < board.length))){
            // check if start piece is not null and is the same color as the one in the parameter
            Piece startPiece = getPiece(startRow, startCol);
            if ((startPiece != null) && (startPiece.getIsBlack() == isBlack) ){
                // check if end piece is null or is the opposite color as the one in the parameter
                Piece endPiece = getPiece(endRow, endCol);
                // check if the end location is null or if the end location has a piece it is the opposite color to be captured
                if ((endPiece == null) || (endPiece != null && endPiece.getIsBlack() != isBlack)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // tests if piece can move up and down
        if (((endRow == startRow - 1) && (endCol == startCol)) || (endRow == startRow + 1) && (endCol == startCol)){
            return true;
        }
        // tests if piece can move left right
        if (((endCol == startCol - 1) && (endRow == startRow)) || (endCol == startCol + 1  && (endRow == startRow))){
            return true;
        }
        // tests if piece can move top left and right diagonals
        if ((endRow == startRow - 1 && endCol == startCol - 1) || (endRow == startRow - 1 && endCol == startCol + 1)){
            return true;
        }
        // tests if piece can move bottom left and right diagonals
        if ((endRow == startRow + 1 && endCol == startCol - 1) || (endRow == startRow + 1 && endCol == startCol + 1)){
            return true;
        }
        // tests if piece can move to the same location
        if ((endRow == startRow && endCol == startCol)){
            return true;
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // the difference between columns
        int diff = Math.abs(endCol - startCol);
        // check if two points are on the same row
        if (startRow == endRow) {
            int dirHor = endCol > startCol ? 1 : -1; // determines the direction to loop from and to each column
            for (int i = 1; i < diff; i++) {
                if (getPiece(startRow, startCol + (i * dirHor)) != null) { // if there is a piece in the way return false
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // the difference between rows
        int diff = Math.abs(endRow - startRow);
        // check if two points are on the same column
        if (startCol == endCol) {
            int dirVert = endRow > startRow ? 1 : -1; // determines the direction to loop from and to each row
            for (int i = 1; i < diff; i++) {
                if (getPiece(startRow + (i * dirVert), startCol) != null) { // if there is a piece in the way return false
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // the difference between rows
        int diff = Math.abs(endRow - startRow);
        // check if two points are on the same diagonal
        if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
            int dirVert = endRow > startRow ? 1 : -1; // determines the direction to loop from and to each row
            int dirHor = endCol > startCol ? 1 : -1;// determines the direction to loop from and to each column
            for (int i = 1; i < diff; i++) { // if there is a piece in the way return false
                if (getPiece(startRow + (i * dirVert), startCol + (i * dirHor)) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
