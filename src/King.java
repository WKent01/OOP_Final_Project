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
        ArrayList<ArrayList<Square>>blocked = (this.color.equals("white")?ChessBoard.blackValidMoves:ChessBoard.whiteValidMoves);
        for (int i = -1; i < 2; i++) {
            for(int j = -1; j<2; j++){
                if(pos_X+i>=0&&pos_X+i<=7&&pos_Y+j>=0&&pos_Y+j<=7){
                    Square testSquare=ChessBoard.squares[pos_X+i][pos_Y+j];
                    boolean squareChecked = false;
                    if(!(testSquare.isOccupied()&&testSquare.pieceOnSquare().color.equals(this.color))){
                        for (ArrayList<Square> testList : blocked) {
                            if(testList.contains(testSquare)){
                                squareChecked=true;
                                break;
                            }
                        }
                        if(!squareChecked){
                            moves.add(testSquare);
                        }
                    }
                }
            }
        }

        
    }

    
}
