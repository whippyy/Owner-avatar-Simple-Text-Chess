public class King {
    /**
     * Constructor.
     * @param row   The current row of the king
     * @param col   The current column of the king
     * @param isBlack   The color of the king
     */
    public King(int row, int col, boolean isBlack) {
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
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            // checks the starting location and end location for adjacent movement
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
                return true;
            }
            // capturing the piece
            else if(board.verifyAdjacent(this.row, this.col, endRow, endCol)){
                return board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack;
            }
        }
        return false;

    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
