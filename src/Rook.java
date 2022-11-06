import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {

        ArrayList<Square> moves = new ArrayList<>();

        try {
            for (int x = this.pos_X; x < 8; x++) {
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

            for (int x = this.pos_X; x >= 0; x--) {
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

            for (int y = this.pos_Y; y < 8; y++) {
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

            for (int y = this.pos_Y; y >= 0; y--) {
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
        } catch (IndexOutOfBoundsException e) {

        }

        return moves;
    }
}
