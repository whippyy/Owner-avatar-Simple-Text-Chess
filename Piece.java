import java.util.Scanner;
public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    // returns true if you can promote pawn
    public boolean pawnPromo(Board board, Boolean isBlack, int endRow, int endCol){
        // take in input for the user to make
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to promote your pawn to?");
        System.out.println("Choices are Rook, Queen, Knight, and Bishop");
        String promo = scanner.nextLine();
        // turns the user input to lower case and check if it matches any
        promo = promo.toLowerCase();
        // promotes the pawn to rook
        if (promo.equals("rook")){
            // sets the space of the pawn to black rook
            if (isBlack == true){
                System.out.println("It works");
                Piece rook = new Piece('\u265c', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, rook);
                return true;
            }
            // sets the space of the pawn to white rook
            if (isBlack == false){
                System.out.println("It works");
                Piece rook = new Piece('\u2656', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, rook);
                return true;
            }
        }
        // promotes the pawn to queen
        if (promo.equals("queen")){
            // sets the space of the pawn to black queen
            if (isBlack == true){
                System.out.println("It works");
                Piece queen = new Piece('\u265b', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, queen);
                return true;
            }
            // sets the space of the pawn to white queen
            if (isBlack == false){
                System.out.println("It works");
                Piece queen = new Piece('\u2655', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, queen);
                return true;
            }
        }
        // promotes the pawn to knight
        if (promo.equals("knight")){
            // sets the space of the pawn to black knight
            if (isBlack == true){
                System.out.println("It works");
                Piece knight = new Piece('\u265e', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, knight);
                return true;
            }
            // sets the space of the pawn to white knight
            if (isBlack == false){
                System.out.println("It works");
                Piece knight = new Piece('\u2658', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, knight);
                return true;
            }
        }
        // promotes the pawn to bishop
        if (promo.equals("bishop")){
            // sets the space of the pawn to black bishop
            if (isBlack == true){
                System.out.println("It works");
                Piece bishop = new Piece('\u265d', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, bishop);
                return true;
            }
            // sets the space of the pawn to white bishop
            if (isBlack == false){
                System.out.println("It works");
                Piece bishop = new Piece('\u2654', endRow, endCol, isBlack);
                board.setPiece(endRow, endCol, bishop);
                return true;
            }
        }
        System.out.println("It does not work");
        return false;
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current chess unicode character.
     * @return Unicode character.
     */
    public char getCharacter(){
        return this.character;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other){
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }


}
