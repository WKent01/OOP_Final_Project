import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public void setValidMoves() {

        moves.clear();
        watching.clear();
        int checkX = pos_X - 1;
        Square checkSquare;
        // Check squares to left
        while (checkX >= 0) {
            checkSquare = ChessBoard.squares[checkX][pos_Y];
            if(checkSquare.isOccupied()){
                if(checkSquare.pieceOnSquare().color.equals(this.color)){
                    watching.add(checkSquare);
                }
                else{
                    moves.add(checkSquare);
                }
                break;
            }
            else{
                moves.add(checkSquare);
            }
            checkX--;
        }
        checkX = pos_X + 1;
        // Check squares to right
        while (checkX <= 7) {
            checkSquare = ChessBoard.squares[checkX][pos_Y];
            if(checkSquare.isOccupied()){
                if(checkSquare.pieceOnSquare().color.equals(this.color)){
                    watching.add(checkSquare);
                }
                else{
                    moves.add(checkSquare);
                }
                break;
            }
            else{
                moves.add(checkSquare);
            }
            checkX++;
        }

        int checkY = pos_Y - 1;
        // Check squares below
        while (checkY >= 0) {
            checkSquare = ChessBoard.squares[pos_X][checkY];
            if(checkSquare.isOccupied()){
                if(checkSquare.pieceOnSquare().color.equals(this.color)){
                    watching.add(checkSquare);
                }
                else{
                    moves.add(checkSquare);
                }
                break;
            }
            else{
                moves.add(checkSquare);
            }
            checkY--;
        }
        checkY = pos_Y + 1;
        // Check squares above
        while (checkY <= 7) {
            checkSquare = ChessBoard.squares[pos_X][checkY];
            if(checkSquare.isOccupied()){
                if(checkSquare.pieceOnSquare().color.equals(this.color)){
                    watching.add(checkSquare);
                }
                else{
                    moves.add(checkSquare);
                }
                break;
            }
            else{
                moves.add(checkSquare);
            }
            checkY++;
        }

        checkMoves();
    }
}
