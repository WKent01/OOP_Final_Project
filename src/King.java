import java.text.BreakIterator;
import java.util.ArrayList;

public class King extends Piece {

    public King(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        return new ArrayList<Square>();
    }

    @Override
    public void setValidMoves() {
        ArrayList<ArrayList<Square>>blocked = (this.color.equals("white")?ChessBoard.BlackValidMoves:ChessBoard.WhiteValidMoves);
        for (int i = -1; i < 2; i++) {
            for(int j = -1; j<2; j++){
                if(pos_X+i>0&&pos_X+i<7&&pos_Y+j>0&&pos_Y+j<7){
                    Square testSquare=ChessBoard.squares[pos_X+i][pos_Y+j];
                    if(!(testSquare.isOccupied()&&testSquare.pieceOnSquare().color.equals(color))){
                        boolean canMove=true;
                        for (ArrayList<Square> checklist : blocked) {
                            if(checklist.contains(testSquare)){
                                canMove=false;
                                break;
                            }
                        }
                        if(canMove){
                            moves.add(testSquare);
                        }
                    }
                }
            }
        }
        
    }

    
}
