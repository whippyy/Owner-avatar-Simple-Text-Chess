public class Knight {
    /**
     * Constructor.
     * @param row   The current row of the knight
     * @param col   The current column of the knight
     * @param isBlack   The color of the knight
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // checks if the space available to move
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){
            // see if knight can move to top L's
            if ((endRow == this.row - 2 && (endCol == this.col - 1 || endCol == this.col + 1))  && board.getPiece(endRow, endCol) == null){
                return true;
            } else if ((endRow == this.row - 2 && (endCol == this.col - 1 || endCol == this.col + 1))){ // capturing
                return board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
            // see if knight can move to bottom L's
            if ((endRow == this.row + 2 && (endCol == this.col - 1 || endCol == this.col + 1))  && board.getPiece(endRow, endCol) == null){
                return true;
            } else if ((endRow == this.row + 2 && (endCol == this.col - 1 || endCol == this.col + 1))){ // capturing
                return board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
            // see if knight can move to left L's
            if ((endCol == this.col - 2 && (endRow == this.row - 1 || endRow == this.row + 1))  && board.getPiece(endRow, endCol) == null){
                return true;
            } else if ((endCol == this.col - 2 && (endRow == this.row - 1 || endRow == this.row + 1))){ // capturing
                return board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
            // see if knight can move to right L's
            if ((endCol == this.col + 2 && (endRow == this.row - 1 || endRow == this.row + 1))  && board.getPiece(endRow, endCol) == null){
                return true;
            } else if ((endCol == this.col + 2 && (endRow == this.row - 1 || endRow == this.row + 1))){ // capturing
                return board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
            return false;
        }
        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
