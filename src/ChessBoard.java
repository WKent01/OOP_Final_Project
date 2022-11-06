import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ChessBoard extends StackPane {

    GridPane board;
    static Square[][] squares = new Square[8][8]; // Square matrix that will contain all the squares of the board and
                                                  // the pieces that will be on the squares

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

    public void addPieces() { // creates all the pieces for the game
        for (int black_col = 0; black_col < 8; black_col++) { // Sets Black Pawns to the board
            addPiece(squares[black_col][1], new Pawn(black_col, 1, "black", "Pawn"));
        }

        addPiece(squares[0][0], new Rook(0, 0, "black", "Rook"));
        addPiece(squares[7][0], new Rook(7, 0, "black", "Rook")); // Sets Black Rooks

        addPiece(squares[1][0], new Knight(0, 0, "black", "Knight"));
        addPiece(squares[6][0], new Knight(7, 0, "black", "Knight")); // Sets Black Knights

        addPiece(squares[2][0], new Bishop(0, 0, "black", "Bishop"));
        addPiece(squares[5][0], new Bishop(7, 0, "black", "Bishop")); // Sets Black Bishops

        addPiece(squares[3][0], new Queen(0, 0, "black", "Queen")); // Sets Black Queen
        addPiece(squares[4][0], new King(7, 0, "black", "King")); // Sets Black King

        for (int white_col = 0; white_col < 8; white_col++) { // Sets all White Pawns
            addPiece(squares[white_col][6], new Pawn(white_col, 6, "white", "Pawn"));
        }

        addPiece(squares[0][7], new Rook(0, 0, "white", "Rook"));
        addPiece(squares[7][7], new Rook(7, 0, "white", "Rook")); // Sets White Rooks

        addPiece(squares[1][7], new Knight(0, 0, "white", "Knight"));
        addPiece(squares[6][7], new Knight(7, 0, "white", "Knight")); // Sets White Knights

        addPiece(squares[2][7], new Bishop(0, 0, "white", "Bishop"));
        addPiece(squares[5][7], new Bishop(7, 0, "white", "Bishop")); // Sets White Bishops

        addPiece(squares[3][7], new Queen(0, 0, "white", "Queen")); // Sets White Queen
        addPiece(squares[4][7], new King(7, 0, "white", "King")); // Sets White King

    }

}
