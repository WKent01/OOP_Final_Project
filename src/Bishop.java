public class Bishop extends Piece {

    public Bishop(int pos_X, int pos_Y, String color, String name) {
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
        for (int x = this.pos_X + 1, y = this.pos_Y + 1; x < 8 && y < 8; x++, y++) { // Checks the SE diagonal
            checkSquare = ChessBoard.squares[x][y];
            if (checkSquare.isOccupied()
                    && !checkSquare.pieceOnSquare().getColor().equals(this.color)) {
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare); // Occupied by enemy
                break;
            } else if (checkSquare.isOccupied()) {
                watching.add(checkSquare);
                break; // break on ally occupation
            } else {
                if(!blocking)
                    moves.add(checkSquare);
                else
                    watching.add(checkSquare);
            }
        }

        for (int x = this.pos_X + 1, y = this.pos_Y - 1; x < 8 && y >= 0; x++, y--) { // Checks the NE diagonal
            checkSquare = ChessBoard.squares[x][y];
            if (checkSquare.isOccupied()
                    && !checkSquare.pieceOnSquare().getColor().equals(this.color)) {
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare); // Occupied by enemy
                break;
            } else if (checkSquare.isOccupied()) {
                watching.add(checkSquare);
                break; // break on ally occupation
            } else {
                if(!blocking)
                    moves.add(checkSquare);
                else
                    watching.add(checkSquare);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y-1; x >= 0 && y >= 0; x--, y--) { // Checks the NW diagonal
            checkSquare = ChessBoard.squares[x][y];
            if (checkSquare.isOccupied()
                    && !checkSquare.pieceOnSquare().getColor().equals(this.color)) {
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare); // Occupied by enemy
                break;
            } else if (checkSquare.isOccupied()) {
                watching.add(checkSquare);
                break; // break on ally occupation
            } else {
                if(!blocking)
                    moves.add(checkSquare);
                else
                    watching.add(checkSquare);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y+1; x >= 0 && y < 8; x--, y++) { // Checks the SW diagonal
            checkSquare = ChessBoard.squares[x][y];
            if (checkSquare.isOccupied()
                    && !checkSquare.pieceOnSquare().getColor().equals(this.color)) {
                        if(!blocking||checkSquare.pieceOnSquare().equals(BlockPiece))
                        moves.add(checkSquare);
                    else
                        watching.add(checkSquare); // Occupied by enemy
                break;
            } else if (checkSquare.isOccupied()) {
                watching.add(checkSquare);
                break; // break on ally occupation
            } else {
                if(!blocking)
                    moves.add(checkSquare);
                else
                    watching.add(checkSquare);
            }
        }

        checkMoves();
    }
}
