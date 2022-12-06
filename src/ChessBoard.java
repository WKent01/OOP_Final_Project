import java.util.Stack;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ChessBoard extends StackPane {

    static GridPane board;
    static Square[][] squares = new Square[8][8]; // Square matrix that will contain all the squares of the board and
                                                  // the pieces that will be on the squares
    static Stack<String> pastMoves = new Stack<>(); // Push each move to this stack after it has been played. Used for
                                                    // replays and handling en passant.
    static ArrayList<ArrayList<Square>> WhiteValidMoves = new ArrayList<>(); // List of all current squares that white pieces can be moved to.
    static ArrayList<ArrayList<Square>> BlackValidMoves = new ArrayList<>(); // Same as above, but for black.
    static ArrayList<Piece> whitePieces = new ArrayList<>();
    static ArrayList<Piece> blackPieces = new ArrayList<>();
    static King blackKing;
    static King whiteKing;
    static Piece whiteCheck; //The piece currently checking the black king.
    static Piece blackCheck;
    static String currentPlayer;

    public ChessBoard(GridPane board) {
        // this.board = board;
        ChessBoard.board = board;
        currentPlayer = "white";

        setUpBoard(board);

        ChessBoard.board.setOnDragEntered(event -> {
            System.out.println(event.getGestureSource());
            if (event.getGestureSource() != ChessBoard.board && event.getDragboard().hasImage()) {
                Piece p = (Piece) event.getGestureSource();
                p.setOpacity(0.7);
            }

            event.consume();
        });

        ChessBoard.board.setOnDragOver(event -> {
            if (event.getGestureSource() != ChessBoard.board && event.getDragboard().hasImage()) {
                // allow for moving
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        ChessBoard.board.setOnDragExited(event -> {
            ChessBoard.resetBoardGlow();

            Piece p = (Piece) event.getGestureSource();
            p.setOpacity(1);

            event.consume();
        });

        ChessBoard.board.setOnDragDropped(event -> {
            Piece piece = (Piece) event.getGestureSource();
                //debug so i can fling pieces around faster
            if (piece.color.equals(currentPlayer)||true) { // checks if the piece is the color of the current player
                Square oldPos = ChessBoard.squares[piece.pos_X][piece.pos_Y];
                Square newPos = (Square) event.getPickResult().getIntersectedNode(); // new square
                System.out.println(newPos);
                ArrayList<Square> moveSquares = piece.getValidMoves();
                if (moveSquares.contains(newPos)) { // checks to see if newPos is in here
                    if(piece.name.equals("Pawn")&&!newPos.isOccupied()&&newPos.x!=oldPos.x){ //En passant handling
                        newPos.setOccupied(true); //for purposes of proper move reporting
                        squares[newPos.x][oldPos.y].getChildren().clear();
                        squares[newPos.x][oldPos.y].setOccupied(false);
                    }
                    String thisMove = ChessStrings.encodeMove(piece, newPos, false);
                    newPos.getChildren().clear();
                    newPos.getChildren().add(piece);
                    newPos.setOccupied(true);
                    piece.pos_X = newPos.x;
                    piece.pos_Y = newPos.y;
                    if(piece.name.equals("Pawn") && Math.abs(oldPos.y-newPos.y)==2){
                        ((Pawn)piece).doubleMoved=true;
                    }
                    oldPos.getChildren().clear();
                    oldPos.setOccupied(false);
                    // let the source know whether the image was successfully transferred and used
                    event.setDropCompleted(true);
                    endTurn(thisMove);
                }

                

            } else {
                System.out.println("It's not your turn.");
                // TODO: Make this a dialog box
            }
            event.consume();
        });
    }

    public static void endTurn(String move) {
        updateMoves(); //This isn't perfectly optimal, as it wastes time re-checking every piece's moves on each move, rather than only the updated ones. 
                       //However, finding which pieces need to be updated is far more difficult, and not needing to recalculate on every piece click makes up for it.
                       //Additionally, the time taken here will likely not be noticed, as it's during the time that the human players swap places.
                       //Ideal implemention would be to have each piece be "watching" a set range of squares, 
                       //and give each one a thread that monitors the move stack for additions, and if a move contains a monitored square, to run the update.
        String nextPlayer = currentPlayer.equals("white") ? "black" : "white";
        if(isChecked(nextPlayer)){
            if(isCheckmate(nextPlayer)){
                move+="#";
                nextPlayer="Game Over";
            }
            else{
            move+="+";
            }
        }
        System.out.println(move);
        pastMoves.push(move);
        currentPlayer=nextPlayer;
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
        updateMoves();
    }

    public static void resetBoardGlow() {
        for (Square[] row : ChessBoard.squares) {
            for (Square s : row) {
                s.setEffect(new Glow(0));
            }
        }
    }

    public static void updateMoves(){
        for(Piece p:whitePieces){
            p.setValidMoves();
        }
        for(Piece p:blackPieces){
            p.setValidMoves();
        }
    }

    /**
     * 
     * @param square
     * @param piece  the piece that is being set on the square
     */

    private void addPiece(Square square, Piece piece) {
        square.getChildren().add(piece);
        square.setOccupied(true);
        if(piece.color.equals("white")){
            whitePieces.add(piece);
            WhiteValidMoves.add(piece.getValidMoves());
        }
        else{
            blackPieces.add(piece);
            BlackValidMoves.add(piece.getValidMoves());
        }
    }

    public static boolean isChecked(String color) { // This method will return true if the specified color is in check.
        if(color.equals("black")){
            for (int i = 0; i < WhiteValidMoves.size(); i++) {
                if(WhiteValidMoves.get(i).contains(squares[blackKing.pos_X][blackKing.pos_Y])){
                    whiteCheck=whitePieces.get(i);
                    return true;
                }
            }
            return false;
        }
        else{
            for (int i = 0; i < BlackValidMoves.size(); i++) {
                if(BlackValidMoves.get(i).contains(squares[whiteKing.pos_X][whiteKing.pos_Y])){
                    blackCheck=whitePieces.get(i);
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isCheckmate(String color) { // This method will return true if the specified color is checkmated.
        return isChecked(color) && (color.equals("White") ? whiteKing : blackKing).getValidMoves().size() > 0;
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