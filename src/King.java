import java.util.ArrayList;

public class King extends Piece {
    
    public King(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        return moves;
    }

    @Override
    public void setValidMoves() {
        moves.clear();
        watching.clear();
        ArrayList<ArrayList<Square>>blocked = (this.color.equals("white")?ChessBoard.blackValidMoves:ChessBoard.whiteValidMoves);
        ArrayList<ArrayList<Square>>watched = (this.color.equals("white")?ChessBoard.blackWatchedSquares:ChessBoard.whiteWatchedSquares);

        //Check main 8 squares
        for (int i = -1; i < 2; i++) {
            for(int j = -1; j<2; j++){
                if(pos_X+i>=0&&pos_X+i<=7&&pos_Y+j>=0&&pos_Y+j<=7){
                    Square testSquare=ChessBoard.squares[pos_X+i][pos_Y+j];
                    boolean squareChecked = false; //Test if any enemy piece is checking the square.

                    if(!(testSquare.isOccupied()&&testSquare.pieceOnSquare().color.equals(this.color))){
                        //Test if any enemy pieces can move to an empty square.
                        for (ArrayList<Square> testList : blocked) {
                            if(testList.contains(testSquare)){
                                //Since pawns can't capture empty squares, this makes sure they don't block squares they can move to.
                                Piece checker = (this.color.equals("white")?ChessBoard.blackPieces:ChessBoard.whitePieces).get(blocked.indexOf(testList));
                                if(!checker.name.equals("Pawn")){
                                    squareChecked=true;
                                    break;
                                }
                            }
                        }
                        //Test if any enemy pieces can move to an occupied square.
                        for (ArrayList<Square> testList : watched) {
                            if(testList.contains(testSquare)){
                                squareChecked=true;
                                break;
                            }
                        }
                        //Test if square would be checked if the King were there. e.g. A king moves to a square that a checking Rook can't move to, but only because it's behind the king.
                        if(ChessBoard.isChecked(this.color)){ 
                            Piece checkPiece = (this.color.equals("white")?ChessBoard.blackCheck:ChessBoard.whiteCheck);
                            if(checkPiece.name.equals("Rook")||checkPiece.name.equals("Bishop")||checkPiece.name.equals("Queen")){
                            int dxc = (pos_X+i)-checkPiece.pos_X; //Same line detection as in Piece.between(), but does not check if between.
                            int dyc = (pos_Y+j)-checkPiece.pos_Y;
                            int dxl = this.pos_X-checkPiece.pos_X;
                            int dyl = this.pos_Y-checkPiece.pos_Y;
                            int cross = dxc * dyl - dyc * dxl;
                            if(cross==0)
                                squareChecked=true;
                            }
                        }
                    }
                    else{ //Don't allow moves to squares occupied by allies. Without this, a king can even capture itself.
                        squareChecked=true;
                    }

                    if(!squareChecked){
                        moves.add(testSquare);
                    }
                    else{
                        watching.add(testSquare); //Prevents a King from moving next to another King.
                    }
                }
            }
        }

        if(!this.hasMoved){
            //Kingside Castling
            if(!ChessBoard.squares[this.pos_X+1][this.pos_Y].isOccupied()){
                if(!ChessBoard.squares[this.pos_X+2][this.pos_Y].isOccupied()){
                    Piece cornerPiece;
                    if(ChessBoard.squares[this.pos_X+3][this.pos_Y].isOccupied()&&
                       (cornerPiece=ChessBoard.squares[this.pos_X+3][this.pos_Y].pieceOnSquare()).name.equals("Rook")&&
                       !cornerPiece.hasMoved){
                        moves.add(ChessBoard.squares[this.pos_X+2][this.pos_Y]);
                       }
                }
            }
            //Queenside Castling
            if(!ChessBoard.squares[this.pos_X-1][this.pos_Y].isOccupied()){
                if(!ChessBoard.squares[this.pos_X-2][this.pos_Y].isOccupied()){
                    if(!ChessBoard.squares[this.pos_X-3][this.pos_Y].isOccupied()){
                        Piece cornerPiece;
                        if(ChessBoard.squares[this.pos_X-4][this.pos_Y].isOccupied()&&
                           (cornerPiece=ChessBoard.squares[this.pos_X-4][this.pos_Y].pieceOnSquare()).name.equals("Rook")&&
                           !cornerPiece.hasMoved){
                            moves.add(ChessBoard.squares[this.pos_X-2][this.pos_Y]);
                           }
                    }
                }
            }
        }
        
    }

    
}
