import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {

        ArrayList<Square> moves = new ArrayList<>();

        try {
            for (int x = this.pos_X; x < 8; x++) { // checks the right side of the queen
                if (ChessBoard.squares[x][this.pos_Y].isOccupied()
                        && !ChessBoard.squares[x][this.pos_Y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][this.pos_Y]);
                    break;
                } else if (ChessBoard.squares[x][this.pos_Y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][this.pos_Y]);
                }
            }

            for (int x = this.pos_X; x >= 0; x--) { // checks the left side of the queen
                if (ChessBoard.squares[x][this.pos_Y].isOccupied()
                        && !ChessBoard.squares[x][this.pos_Y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][this.pos_Y]);
                    break;
                } else if (ChessBoard.squares[x][this.pos_Y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][this.pos_Y]);
                }
            }

            for (int y = this.pos_Y; y < 8; y++) { // checks in front of the queen
                if (ChessBoard.squares[this.pos_X][y].isOccupied()
                        && !ChessBoard.squares[this.pos_X][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[this.pos_X][y]);
                    break;
                } else if (ChessBoard.squares[this.pos_X][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[this.pos_X][y]);
                }
            }

            for (int y = this.pos_Y; y >= 0; y--) { // checks behind the queen
                if (ChessBoard.squares[this.pos_X][y].isOccupied()
                        && !ChessBoard.squares[this.pos_X][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[this.pos_X][y]);
                    break;
                } else if (ChessBoard.squares[this.pos_X][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[this.pos_X][y]);
                }
            }

            for (int x = this.pos_X, y = this.pos_Y; x < 8 && y < 8; x++, y++) { // Checks the SE diagonal
                if (ChessBoard.squares[x][y].isOccupied()
                        && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][y]);
                    break;
                } else if (ChessBoard.squares[x][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][y]);
                }
            }

            for (int x = this.pos_X, y = this.pos_Y; x < 8 && y >= 0; x++, y--) { // Checks the NE diagonal
                if (ChessBoard.squares[x][y].isOccupied()
                        && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][y]);
                    break;
                } else if (ChessBoard.squares[x][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][y]);
                }
            }

            for (int x = this.pos_X, y = this.pos_Y; x >= 0 && y >= 0; x--, y--) { // Checks the NW diagonal
                if (ChessBoard.squares[x][y].isOccupied()
                        && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][y]);
                    break;
                } else if (ChessBoard.squares[x][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][y]);
                }
            }

            for (int x = this.pos_X, y = this.pos_Y; x >= 0 && y < 8; x--, y++) { // Checks the SW diagonal
                if (ChessBoard.squares[x][y].isOccupied()
                        && !ChessBoard.squares[x][y].pieceOnSquare().getColor().equals(this.color)) {
                    moves.add(ChessBoard.squares[x][y]);
                    break;
                } else if (ChessBoard.squares[x][y].isOccupied()) {
                    continue;
                } else {
                    moves.add(ChessBoard.squares[x][y]);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // throws this error only if the square is empty
        }

        return moves;
    }
}
