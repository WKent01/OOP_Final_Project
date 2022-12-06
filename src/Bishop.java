import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public void setValidMoves() {

        moves.clear();

        for (int x = this.pos_X + 1, y = this.pos_Y + 1; x < 8 && y < 8; x++, y++) { // Checks the SE diagonal
            if (ChessBoard.squares[x][y].isOccupied()
                    && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                moves.add(ChessBoard.squares[x][y]); // Occupied by enemy
                break;
            } else if (ChessBoard.squares[x][y].isOccupied()) {
                break; // break on ally occupation
            } else {
                moves.add(ChessBoard.squares[x][y]);
            }
        }

        for (int x = this.pos_X + 1, y = this.pos_Y - 1; x < 8 && y >= 0; x++, y--) { // Checks the NE diagonal
            if (ChessBoard.squares[x][y].isOccupied()
                    && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                moves.add(ChessBoard.squares[x][y]); // Occupied by enemy
                break;
            } else if (ChessBoard.squares[x][y].isOccupied()) {
                break; // break on ally occupation
            } else {
                moves.add(ChessBoard.squares[x][y]);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y-1; x >= 0 && y >= 0; x--, y--) { // Checks the NW diagonal
            if (ChessBoard.squares[x][y].isOccupied()
                    && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                moves.add(ChessBoard.squares[x][y]); // Occupied by enemy
                break;
            } else if (ChessBoard.squares[x][y].isOccupied()) {
                break; // break on ally occupation
            } else {
                moves.add(ChessBoard.squares[x][y]);
            }
        }

        for (int x = this.pos_X-1, y = this.pos_Y+1; x >= 0 && y < 8; x--, y++) { // Checks the SW diagonal
            if (ChessBoard.squares[x][y].isOccupied()
                    && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                moves.add(ChessBoard.squares[x][y]); // Occupied by enemy
                break;
            } else if (ChessBoard.squares[x][y].isOccupied()) {
                break; // break on ally occupation
            } else {
                moves.add(ChessBoard.squares[x][y]);
            }
        }

    }
}
