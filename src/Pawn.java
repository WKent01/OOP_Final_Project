import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();

        this.setOnMouseClicked(e -> {

            /*
             * get the valid moves then display them
             */

            super.showValidMoves();

        });
    }

    @Override
    public ArrayList<Square> getValidMoves() {

        ArrayList<Square> moves = new ArrayList<>();

        if (this.color.equals("white")) {
            if (this.pos_Y == 6) { // checks to see if the pawn is on its' inital row which then allows the pawn to
                                   // move forward two spaces
                if (!ChessBoard.squares[this.pos_X][this.pos_Y - 2].isOccupied()
                        && !ChessBoard.squares[this.pos_X][this.pos_Y - 1].isOccupied()) {
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 1]);
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 2]);
                }
            } else {
                if (!ChessBoard.squares[this.pos_X][this.pos_Y - 1].isOccupied()) { // checks to see if there is any
                                                                                    // piece infront of it
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 1]);
                }
            }

            try {
                if (this.pos_X - 1 >= 0) { // checks the left diagonal for a black piece
                    if (ChessBoard.squares[this.pos_X - 1][this.pos_Y - 1].pieceOnSquare().getColor().equals("black")) {
                        moves.add(ChessBoard.squares[this.pos_X - 1][this.pos_Y - 1]);
                    }
                }

                if (this.pos_X + 1 < 8) { // checks the right diagonal for a black piece
                    if (ChessBoard.squares[this.pos_X + 1][this.pos_Y - 1].pieceOnSquare().getColor().equals("black")) {
                        moves.add(ChessBoard.squares[this.pos_X + 1][this.pos_Y - 1]);
                    }
                }
            } catch (IndexOutOfBoundsException e) {

            }

        } else {
            if (this.pos_Y == 1) {
                if (!ChessBoard.squares[this.pos_X][this.pos_Y + 2].isOccupied()
                        && !ChessBoard.squares[this.pos_X][this.pos_Y + 1].isOccupied()) {
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 1]);
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 2]);
                }
            } else {
                if (!ChessBoard.squares[this.pos_X][this.pos_Y + 1].isOccupied()) {
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 1]);
                }
            }

            try {
                if (this.pos_X + 1 < 8) { // checks the left diagonal for a white piece
                    if (ChessBoard.squares[this.pos_X + 1][this.pos_Y + 1].pieceOnSquare().getColor().equals("white")) {
                        moves.add(ChessBoard.squares[this.pos_X + 1][this.pos_Y + 1]);
                    }
                }

                if (this.pos_X - 1 >= 8) { // checks the right diagonal for a white piece
                    if (ChessBoard.squares[this.pos_X - 1][this.pos_Y + 1].pieceOnSquare().getColor().equals("white")) {
                        moves.add(ChessBoard.squares[this.pos_X - 1][this.pos_Y + 1]);
                    }
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }

        return moves;
    }

}