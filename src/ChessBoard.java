import java.util.Stack;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ChessBoard extends StackPane {

    GridPane board;
    static Square[][] squares = new Square[8][8]; // Square matrix that will contain all the squares of the board and
                                                  // the pieces that will be on the squares
    static Stack<String> pastMoves = new Stack<>(); //Push each move to this stack after it has been played. Used for replays and handling en passant.
    static ArrayList<Square> WhiteValidMoves = new ArrayList<>(); //List of all current squares that white pieces can be moved to. Will probably be refactored to an ArrayList of ArrayLists, where each one is for an individual piece. Used for king movement check prevention.
    static ArrayList<Square> BlackValidMoves = new ArrayList<>(); //Same as above, but for black.
    static King blackKing;
    static King whiteKing;
    public ChessBoard(GridPane board) {
        this.board = board;

        setUpBoard(board);
    }

    /**
     * 
     * @param board inits all squares of the chessboard and attaches it to the board
     *              and the matrix
     *              then it attaches all the pieces to the squares
     */

    public void setUpBoard(GridPane board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j);
                board.add(square, i, j, 1, 1);
                squares[i][j] = square;
            }
        }
        addPieces();
        pastMoves.push("Kxc2#"); //DEBUG: here to prevent EmptyStackException.
    }

    /**
     * 
     * @param square
     * @param piece  the piece that is being set on the square
     */

    private void addPiece(Square square, Piece piece) {
        square.getChildren().add(piece);
        square.setOccupied(true);
    }
    public static boolean isCheck(String color){ //This method will return true if the specified color is in check.
        return false;
    }
    public static boolean isCheckmate(String color){ //This method will return true if the specified color is checkmated.
        return isCheck(color)&&(color.equals("White")?whiteKing:blackKing).getValidMoves().size()>0; 
    }

    public void addPieces() { // creates all the pieces for the game
        for (int i = 0; i < 8; i++) { // Sets the black pawns to the board
            addPiece(squares[i][1], new Pawn(i, 1, "black", "Pawn"));
        }

        addPiece(squares[0][0], new Rook(0, 0, "black", "Rook")); // Sets the black rooks to the board
        addPiece(squares[7][0], new Rook(7, 0, "black", "Rook"));

        addPiece(squares[1][0], new Knight(1, 0, "black", "Knight")); // Sets the black knights to the board
        addPiece(squares[6][0], new Knight(6, 0, "black", "Knight"));

        addPiece(squares[2][0], new Bishop(2, 0, "black", "Bishop")); // Sets the black bishops to the board
        addPiece(squares[5][0], new Bishop(5, 0, "black", "Bishop"));

        addPiece(squares[3][0], new Queen(3, 0, "black", "Queen")); // Sets the black queen and king respectivly
        addPiece(squares[4][0], (blackKing = new King(4, 0, "black", "King")));

        for (int i = 0; i < 8; i++) { // Sets the white pawns to the board
            addPiece(squares[i][6], new Pawn(i, 6, "white", "Pawn"));
        }

        addPiece(squares[0][7], new Rook(0, 7, "white", "Rook")); // Sets the white rooks to the board
        addPiece(squares[7][7], new Rook(7, 7, "white", "Rook"));

        addPiece(squares[1][7], new Knight(1, 7, "white", "Knight")); // Sets the white knight (lol) to the board
        addPiece(squares[6][7], new Knight(6, 7, "white", "Knight"));

        addPiece(squares[2][7], new Bishop(2, 7, "white", "Bishop")); // Sets the white bishops to the board
        addPiece(squares[5][7], new Bishop(5, 7, "white", "Bishop"));

        addPiece(squares[3][7], new Queen(3, 7, "white", "Queen")); // Sets the white queen and king respectivly
        addPiece(squares[4][7], (whiteKing = new King(4, 7, "white", "King")));

    }

}