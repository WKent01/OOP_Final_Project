import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public void setValidMoves() {
        moves.clear();
        watching.clear();
        Square testSquare;
        // Checks 2 furthest left positions.
        if (pos_X >= 2) {
            if (pos_Y <= 6) {
                if((testSquare=ChessBoard.squares[pos_X - 2][pos_Y + 1]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
                
            }
            if (pos_Y >= 1) {
                if((testSquare=ChessBoard.squares[pos_X - 2][pos_Y - 1]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
        }
        // Checks 2 positions 1 to the left
        if (pos_X >= 1) {
            if (pos_Y <= 5) {
                if((testSquare=ChessBoard.squares[pos_X - 1][pos_Y + 2]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
            if (pos_Y >= 2) {
                if((testSquare=ChessBoard.squares[pos_X - 1][pos_Y - 2]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
        }

        // Checks 2 furthest right positions.
        if (pos_X <= 5) {
            if (pos_Y <= 6) {
                if((testSquare=ChessBoard.squares[pos_X + 2][pos_Y + 1]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
            if (pos_Y >= 1) {
                if((testSquare=ChessBoard.squares[pos_X + 2][pos_Y - 1]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
        }
        // Checks 2 positions 1 to the right
        if (pos_X <= 6) {
            if (pos_Y <= 5) {
                if((testSquare=ChessBoard.squares[pos_X + 1][pos_Y + 2]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
            if (pos_Y >= 2) {
                if((testSquare=ChessBoard.squares[pos_X + 1][pos_Y - 2]).isOccupied()){
                    if(testSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(testSquare);
                    }
                }
                else
                    moves.add(testSquare);
            }
        }

        checkMoves();
    }
}
