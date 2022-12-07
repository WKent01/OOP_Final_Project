import java.util.ArrayList;
import java.util.Iterator;

public class Queen extends Piece {
    public Queen(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public void setValidMoves() {

        moves.clear();

        int checkX = pos_X - 1;
        // Check squares to left
        while (checkX >= 0) {
            Piece posPiece = ChessBoard.squares[checkX][pos_Y].pieceOnSquare();
            if (posPiece == null || !(posPiece.color.equals(this.color)))
                moves.add(ChessBoard.squares[checkX][pos_Y]);
            checkX--;
            if (!(posPiece == null))
                break;
        }
        checkX = pos_X + 1;
        // Check squares to right
        while (checkX <= 7) {
            Piece posPiece = ChessBoard.squares[checkX][pos_Y].pieceOnSquare();
            if (posPiece == null || !(posPiece.color.equals(this.color)))
                moves.add(ChessBoard.squares[checkX][pos_Y]);
            checkX++;
            if (!(posPiece == null))
                break;
        }

        int checkY = pos_Y - 1;
        // Check squares below
        while (checkY >= 0) {
            Piece posPiece = ChessBoard.squares[pos_X][checkY].pieceOnSquare();
            if (posPiece == null || !(posPiece.color.equals(this.color)))
                moves.add(ChessBoard.squares[pos_X][checkY]);
            checkY--;
            if (!(posPiece == null))
                break;
        }
        checkY = pos_Y + 1;
        // Check squares above
        while (checkY <= 7) {
            Piece posPiece = ChessBoard.squares[pos_X][checkY].pieceOnSquare();
            if (posPiece == null || !(posPiece.color.equals(this.color)))
                moves.add(ChessBoard.squares[pos_X][checkY]);
            checkY++;
            if (!(posPiece == null))
                break;
        }

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

        checkMoves();
    }
}
