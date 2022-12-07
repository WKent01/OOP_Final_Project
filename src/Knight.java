public class Knight extends Piece {
    public Knight(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public void setValidMoves() {
        moves.clear();
        watching.clear();
        Piece BlockPiece = this.blocksPiece();
        boolean blocking = !(BlockPiece==null);
        Square checkSquare;
        // Checks 2 furthest left positions.
        if (pos_X >= 2) {
            if (pos_Y <= 6) {
                if((checkSquare=ChessBoard.squares[pos_X - 2][pos_Y + 1]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
                
            }
            if (pos_Y >= 1) {
                if((checkSquare=ChessBoard.squares[pos_X - 2][pos_Y - 1]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
        }
        // Checks 2 positions 1 to the left
        if (pos_X >= 1) {
            if (pos_Y <= 5) {
                if((checkSquare=ChessBoard.squares[pos_X - 1][pos_Y + 2]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
            if (pos_Y >= 2) {
                if((checkSquare=ChessBoard.squares[pos_X - 1][pos_Y - 2]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
        }

        // Checks 2 furthest right positions.
        if (pos_X <= 5) {
            if (pos_Y <= 6) {
                if((checkSquare=ChessBoard.squares[pos_X + 2][pos_Y + 1]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
            if (pos_Y >= 1) {
                if((checkSquare=ChessBoard.squares[pos_X + 2][pos_Y - 1]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
        }
        // Checks 2 positions 1 to the right
        if (pos_X <= 6) {
            if (pos_Y <= 5) {
                if((checkSquare=ChessBoard.squares[pos_X + 1][pos_Y + 2]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
    
            }
            if (pos_Y >= 2) {
                if((checkSquare=ChessBoard.squares[pos_X + 1][pos_Y - 2]).isOccupied()){
                    if(checkSquare.pieceOnSquare().color.equals(this.color)){
                        watching.add(checkSquare);
                    }
                    else{
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                            moves.add(checkSquare);
                        else
                            watching.add(checkSquare);
                    }
                }
                else{
                    if(!blocking)
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare);
                }
            }
        }
        }
        checkMoves();
    }
}
