import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public abstract class Piece extends ImageView {
    int pos_X, pos_Y;
    String color;
    String name;

    /**
     * 
     * @param pos_X the pieces col
     * @param pos_Y the pieces row
     * @param color
     * @param name
     */

    public Piece(int pos_X, int pos_Y, String color, String name) {
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.color = color;
        this.name = name;

        this.setOnDragDetected(event -> {
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(this.getImage());
            db.setContent(clipboardContent);


            ArrayList<Square> moveSquares = this.getValidMoves();
            for (Square square : moveSquares) {
                square.setEffect(new Glow(0.5));
            }
            event.consume();
        });

        this.setOnDragEntered(event -> {
            event.consume();
        });

        this.setOnDragExited(event -> {
            event.consume();
        });

        this.setOnDragDone(event -> {
            event.consume();
        });

        this.setOnDragDropped(event -> {
            Piece piece = (Piece) event.getGestureSource();

            if (piece.color.equals(ChessBoard.currentPlayer)) {
                Piece otherPiece = (Piece) event.getPickResult().getIntersectedNode();
                Square oldPos = ChessBoard.squares[piece.pos_X][piece.pos_Y];
                Square newPos = ChessBoard.squares[otherPiece.pos_X][otherPiece.pos_Y];

                System.out.println(newPos);

                ArrayList<Square> moveSquares = piece.getValidMoves();

                if (moveSquares.contains(newPos)) {
                    newPos.getChildren().clear();
                    newPos.getChildren().add(piece);
                    newPos.setOccupied(true);
                    piece.pos_X = newPos.x;
                    piece.pos_Y = newPos.y;

                    oldPos.getChildren().clear();
                    oldPos.setOccupied(false);

                    // let the source know whether the image was successfully transferred and used
                    event.setDropCompleted(true);
                    ChessBoard.endTurn();

                }

            

            } else {
                System.err.println("It's not your turn.");
                // TODO: Make this a dialog box
            }
            event.consume();
        });

    }

    public String getColor() {
        return color;
    }

    public void setPieceImage() { // Sets the pieces corresponding image
        setImage(new Image("/media/" + this.color + "" + this.name + ".png"));
    }

    // public void showValidMoves() { // the squares that are in validMoves glow

    // for (Square square : getValidMoves()) {
    // square.setEffect(new Glow(0.5));
    // System.out.println(square.toString());
    // }

    // }

    public abstract ArrayList<Square> getValidMoves(); // method will calculate all valid moves at the pieces current
                                                       // position on the board and return a list containing all the
                                                       // squares it can go to

}
