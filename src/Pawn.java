import java.util.ArrayList;

public class Pawn extends Piece {

    public boolean doubleMoved;

    public Pawn(int pos_X, int pos_Y, String color, String name){
        super(pos_X, pos_Y, color, name);
        setPieceImage();
        doubleMoved=false;
    }

    @Override
    public void setValidMoves() {

        moves.clear();
        watching.clear();
            if (this.color.equals("white")) {
                if (this.pos_Y == 6) { // checks to see if the pawn is on its' inital row which then allows the pawn to
                                       // move forward two spaces
                    if (!ChessBoard.squares[this.pos_X][this.pos_Y - 1].isOccupied()&&!ChessBoard.squares[this.pos_X][this.pos_Y - 2].isOccupied()) {
                        moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 2]);
                        // when moved, set doubleMoved to true.
                    }
                }
                if (!ChessBoard.squares[this.pos_X][this.pos_Y - 1].isOccupied()) { // checks to see if there is any
                    // piece infront of it
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 1]);
                }
                // no null conditional?
                if (this.pos_X > 0) { // checks the left diagonal for a black piece
                    Square check = ChessBoard.squares[this.pos_X - 1][this.pos_Y - 1];
                    if (check.isOccupied()) {
                        if(check.pieceOnSquare().getColor().equals("black"))
                            moves.add(check);
                        else
                            watching.add(check);
                    }
                }

                if (this.pos_X < 7) { // checks the right diagonal for a black piece
                    Square check = ChessBoard.squares[this.pos_X + 1][this.pos_Y - 1];
                    if (check.isOccupied()) {
                        if(check.pieceOnSquare().getColor().equals("black"))
                            moves.add(check);
                        else
                            watching.add(check);
                    }
                } 
            }
            else { // the piece is a black pawn
                if (this.pos_Y == 1) {
                    if (!ChessBoard.squares[this.pos_X][this.pos_Y + 1].isOccupied()&&!ChessBoard.squares[this.pos_X][this.pos_Y + 2].isOccupied()) {
                        moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 2]);
                        // if moved, set doubleMoved to true.
                    }
                }
                if (!ChessBoard.squares[this.pos_X][this.pos_Y + 1].isOccupied()) {
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 1]);
                }

                if (this.pos_X > 0) { // checks the left diagonal for a white piece
                    Square check = ChessBoard.squares[this.pos_X - 1][this.pos_Y + 1];
                    if (check.isOccupied()) {
                        if(check.pieceOnSquare().getColor().equals("white"))
                            moves.add(check);
                        else
                            watching.add(check);
                    }
                }

                if (this.pos_X < 7) { // checks the right diagonal for a white piece
                    Square check = ChessBoard.squares[this.pos_X + 1][this.pos_Y + 1];
                    if (check.isOccupied()) {
                        if(check.pieceOnSquare().getColor().equals("black"))
                            moves.add(check);
                        else
                            watching.add(check);
                    }
                }
            }
            //Leftward En Passant
            if (this.pos_X>0 &&
                ChessBoard.squares[this.pos_X - 1][this.pos_Y].isOccupied() &&
                ChessBoard.squares[this.pos_X - 1][this.pos_Y].pieceOnSquare().name.equals("Pawn")) {
                    Pawn otherPawn = (Pawn) ChessBoard.squares[this.pos_X - 1][this.pos_Y].pieceOnSquare();
                    if(!otherPawn.color.equals(this.color)){
                        String lastMove = (ChessBoard.pastMoves.isEmpty()?"":ChessBoard.pastMoves.peek()); 
                
                        if (otherPawn.doubleMoved &&
                        lastMove.charAt(0)>96 && //Originally this compared the length to 2, but that would break in the offchance that a pawn double-moved to put the king in check.
                        ChessStrings.xLookup.indexOf(lastMove.substring(0,1)) == this.pos_X - 1){
                            moves.add(ChessBoard.squares[this.pos_X - 1][this.pos_Y+(this.color.equals("white")?-1:1)]); 
                        }
                    }
            }

            //Rightward En Passant
            if (this.pos_X<7 &&
                ChessBoard.squares[this.pos_X+1][this.pos_Y].isOccupied() &&
                ChessBoard.squares[this.pos_X+1][this.pos_Y].pieceOnSquare().name.equals("Pawn")) {
                    Pawn otherPawn = (Pawn) ChessBoard.squares[this.pos_X+1][this.pos_Y].pieceOnSquare();
                    if(!otherPawn.color.equals(this.color)){
                        String lastMove = (ChessBoard.pastMoves.isEmpty()?"":ChessBoard.pastMoves.peek()); 
            
                        if (otherPawn.doubleMoved &&
                        lastMove.charAt(0)>96 && //Originally this compared the length to 2, but that would break in the offchance that a pawn double-moved to put the king in check.
                        ChessStrings.xLookup.indexOf(lastMove.substring(0,1)) == this.pos_X+1){
                            moves.add(ChessBoard.squares[this.pos_X+1][this.pos_Y+(this.color.equals("white")?-1:1)]); 
                        }
                    }
            }
            
        checkMoves();
    }

}