public class Queen extends Piece {
    public Queen(int pos_X, int pos_Y, String color, String name) {
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

        for (int x = this.pos_X + 1, y = this.pos_Y + 1; x < 8 && y < 8; x++, y++) { // Checks the SE diagonal
            Square testSquare = ChessBoard.squares[x][y];
            if (testSquare.isOccupied()
                    && !testSquare.pieceOnSquare().getColor().equals(this.color)) {
                moves.add(testSquare); // Occupied by enemy
                break;
            } else if (testSquare.isOccupied()) {
                watching.add(testSquare);
                break; // break on ally occupation
            } else {
                moves.add(testSquare);
            }
        }

        for (int x = this.pos_X + 1, y = this.pos_Y - 1; x < 8 && y >= 0; x++, y--) { // Checks the NE diagonal
            Square testSquare = ChessBoard.squares[x][y];
            if (testSquare.isOccupied()
                    && !testSquare.pieceOnSquare().getColor().equals(this.color)) {
                moves.add(testSquare); // Occupied by enemy
                break;
            } else if (testSquare.isOccupied()) {
                watching.add(testSquare);
                break; // break on ally occupation
            } else {
                moves.add(testSquare);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y-1; x >= 0 && y >= 0; x--, y--) { // Checks the NW diagonal
            Square testSquare = ChessBoard.squares[x][y];
            if (testSquare.isOccupied()
                    && !testSquare.pieceOnSquare().getColor().equals(this.color)) {
                moves.add(testSquare); // Occupied by enemy
                break;
            } else if (testSquare.isOccupied()) {
                watching.add(testSquare);
                break; // break on ally occupation
            } else {
                moves.add(testSquare);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y+1; x >= 0 && y < 8; x--, y++) { // Checks the SW diagonal
            Square testSquare = ChessBoard.squares[x][y];
            if (testSquare.isOccupied()
                    && !testSquare.pieceOnSquare().getColor().equals(this.color)) {
                moves.add(testSquare); // Occupied by enemy
                break;
            } else if (testSquare.isOccupied()) {
                watching.add(testSquare);
                break; // break on ally occupation
            } else {
                moves.add(testSquare);
            }
        }

        checkMoves();
    }
}
