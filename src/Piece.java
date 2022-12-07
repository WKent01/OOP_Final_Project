import java.util.ArrayList;
import java.util.Iterator;

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
    ArrayList<Square> moves;
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
        moves = new ArrayList<>();
        this.setOnDragDetected(event -> {
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(this.getImage());
            db.setContent(clipboardContent);


            ArrayList<Square> moveSquares = moves;
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

                ArrayList<Square> moveSquares = piece.getValidMoves();

                if (moveSquares.contains(newPos)) {
                    String thisMove;
                    if(piece.name.equals("Pawn")&&(newPos.y==0||newPos.y==7)){ //Promotion logic
                        thisMove = ChessStrings.encodeMove(piece, newPos, true);
                        ArrayList<Piece> updateList = (piece.color.equals("white")?ChessBoard.whitePieces:ChessBoard.blackPieces);
                        updateList.set(updateList.indexOf(piece),(piece=new Queen(piece.pos_X,piece.pos_Y,piece.color,"Queen")));
                    }
                    else{
                        thisMove = ChessStrings.encodeMove(piece, newPos, false);
                    }
                    if(newPos.isOccupied()){
                        Piece captured = newPos.pieceOnSquare();
                        ArrayList<Piece> updateList = (captured.color.equals("white")?ChessBoard.whitePieces:ChessBoard.blackPieces);
                        ArrayList<ArrayList<Square>> movesUpdateList =(captured.color.equals("white")?ChessBoard.whiteValidMoves:ChessBoard.blackValidMoves);
                        updateList.remove(captured);
                        movesUpdateList.remove(captured.getValidMoves());
                    }
                    newPos.getChildren().clear();
                    newPos.getChildren().add(piece);
                    newPos.setOccupied(true);
                    piece.pos_X = newPos.x;
                    piece.pos_Y = newPos.y;

                    oldPos.getChildren().clear();
                    oldPos.setOccupied(false);

                    // let the source know whether the image was successfully transferred and used
                    event.setDropCompleted(true);
                    ChessBoard.endTurn(thisMove);

                }

            

            } else {
                System.out.println("It's not your turn.");
            }
            event.consume();
        });

    }

    public String getColor() {
        return color;
    }

    public void captured(){
        this.moves=new ArrayList<Square>();
        this.pos_X=-1;
        this.pos_Y=-1;
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

    public abstract void setValidMoves(); // method will calculate all valid moves at the pieces current position on the board 
    
    public void checkMoves(){
        if(ChessBoard.isChecked(this.color)){
            Iterator<Square> tester = moves.iterator();
            while(tester.hasNext()){

                Square test = tester.next();
                Piece enemyCheck = (this.color.equals("white")?ChessBoard.blackCheck:ChessBoard.whiteCheck);
                King thisKing = (this.color.equals("white")?ChessBoard.whiteKing:ChessBoard.blackKing);
                ArrayList<Piece> enemyPieces = (this.color.equals("white")?ChessBoard.blackPieces:ChessBoard.whitePieces);
                ArrayList<ArrayList<Square>> enemyMoves = (this.color.equals("white")?ChessBoard.blackValidMoves:ChessBoard.whiteValidMoves);

                if(test.isOccupied()&&!test.pieceOnSquare().equals(enemyCheck)){
                    tester.remove();
                }
                else if(enemyMoves.get(enemyPieces.indexOf(enemyCheck)).contains(test)){
                    int kingX = thisKing.pos_X;
                    int kingY = thisKing.pos_Y;
                    int oppX = enemyCheck.pos_X;
                    int oppY = enemyCheck.pos_Y;
                    int thisX = test.x;
                    int thisY = test.y;
                    if(!between(kingX,kingY,thisX,thisY,oppX,oppY)){
                        tester.remove();
                    }
                }
                else if(!test.isOccupied()){
                    tester.remove();
                }
            }
        }
    }

    public boolean between(int ax, int ay, int cx, int cy, int bx, int by){
        int dxc = cx-ax;
        int dyc = cy-ay;
        int dxl = bx-ax;
        int dyl = by-ay;
        int cross = dxc * dyl - dyc * dxl;
        if(cross!=0){
            return false;
        }

        if(Math.abs(dxl)>=Math.abs(dyl)){
            return(dxl>0?(ax<=cx&&cx<=bx):(bx<=cx&&cx<=ax));
        }
        else{
            return(dyl>0?(ay<=cy&&cy<=by):(by<=cy&&cy<=ay));
        }
    }
    public ArrayList<Square> getValidMoves(){return moves;}
}
