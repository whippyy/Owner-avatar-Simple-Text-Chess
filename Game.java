import java.util.Scanner;
public class Game {
    public static void main(String[] args){
        Board newGame = new Board(); // creates a new game and board
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", newGame); // sets the pieces on the board to is correct unicode characters
        Scanner scanner = new Scanner(System.in); // creates input for the user
        while (!newGame.isGameOver()){ // make sure there are two kings on the board and one is not captured
            System.out.println(newGame); // print out the board and gives instruction for the player
            System.out.println("What is your move?");
            System.out.println("[Start row]");
            int startRow = scanner.nextInt(); // take in an int for the start row from the player
            System.out.println("[Start col]");
            int startCol = scanner.nextInt(); // take in an int for the start column from the player
            System.out.println("[End row]");
            int endRow = scanner.nextInt(); // take in an int for the end row from the player
            System.out.println("[End col]");
            int endCol = scanner.nextInt(); // take in an int for the end column from the player
            newGame.movePiece(startRow, startCol, endRow, endCol); // moves the player piece to the end location
            // checks if the moved pawn is at row 0 or 7 then ask for input from the player for pawn promotion
            Piece pawnChecker = newGame.getPiece(endRow, endCol);
            if ((pawnChecker.getCharacter() =='\u265f') || (pawnChecker.getCharacter() =='\u2659')){
                if(endRow == 0 || endRow == 7){
                    System.out.println(newGame);
                    boolean pieceColor = pawnChecker.getIsBlack();
                    pawnChecker.pawnPromo(newGame, pieceColor, endRow, endCol);
                }
            }
        }
        System.out.println(newGame);
        System.out.println("King has been captured"); // the game has ended
    }
}