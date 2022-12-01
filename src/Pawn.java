import java.util.ArrayList;

public class Pawn extends Piece {

    public boolean doubleMoved;

    public Pawn(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {

        ArrayList<Square> moves = new ArrayList<>();
        try {
            if (this.color.equals("white")) {
                if (this.pos_Y == 6) { // checks to see if the pawn is on its' inital row which then allows the pawn to
                                       // move forward two spaces
                    if (!(ChessBoard.squares[this.pos_X][this.pos_Y - 2].isOccupied())) {
                        moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 2]);
                        // when moved, set doubleMoved to true.
                    }
                }
                if (!ChessBoard.squares[this.pos_X][this.pos_Y - 1].isOccupied()) { // checks to see if there is any
                    // piece infront of it
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y - 1]);
                }
                // no null conditional?
                if (this.pos_X > 0) { // checks the left diagonal for a black piece
                    Square check = ChessBoard.squares[this.pos_X - 1][this.pos_Y - 1];
                    if (check.isOccupied() && check.pieceOnSquare().getColor().equals("black")) {
                        moves.add(check);
                    }
                }

                if (this.pos_X < 7) { // checks the right diagonal for a black piece
                    Square check = ChessBoard.squares[this.pos_X + 1][this.pos_Y - 1];
                    if (check.isOccupied() && check.pieceOnSquare().getColor().equals("black")) {
                        moves.add(check);
                    }
                }

            } else { // the piece is a black pawn
                if (this.pos_Y == 1) {
                    if (!ChessBoard.squares[this.pos_X][this.pos_Y + 2].isOccupied()) {
                        moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 2]);
                        // if moved, set doubleMoved to true.
                    }
                }
                if (!ChessBoard.squares[this.pos_X][this.pos_Y + 1].isOccupied()) {
                    moves.add(ChessBoard.squares[this.pos_X][this.pos_Y + 1]);
                }

                if (this.pos_Y > 0) { // checks the left diagonal for a white piece
                    Square check = ChessBoard.squares[this.pos_X - 1][this.pos_Y + 1]; // Don't need to worry about out
                                                                                       // of bounds on x because it will
                                                                                       // promote.
                    if (check.isOccupied() && check.pieceOnSquare().getColor().equals("white")) {
                        moves.add(check);
                    }
                }

                if (this.pos_Y < 7) { // checks the right diagonal for a white piece
                    Square check = ChessBoard.squares[this.pos_X + 1][this.pos_Y + 1];
                    if (check.isOccupied() && (check.pieceOnSquare().getColor().equals("white"))) {
                        moves.add(check);
                    }
                }
            }
            // EN CROISSANT || The compound chained if statments are a bit hard to
            // understand, but I still think they look a bit better than a stack of nested
            // statements.
            // Out of bounds| Prevent accessing null piece | Is it a pawn?
            if (this.pos_X > 0 && ChessBoard.squares[this.pos_X - 1][this.pos_Y].isOccupied()
                    && ChessBoard.squares[this.pos_X - 1][this.pos_Y].pieceOnSquare().name.equals("Pawn")) {
                String lastMove = ChessBoard.pastMoves.peek(); // note: not currently sure how to make sure it was a
                                                               // double move and not just a second single move. will
                                                               // fix later.
                Pawn otherPawn = (Pawn) ChessBoard.squares[this.pos_X - 1][this.pos_Y].pieceOnSquare(); // We already
                                                                                                        // checked that
                                                                                                        // it was a pawn
                                                                                                        // above.
                // Is it a pawn move | Was it the neighboring pawn | Did it double move (vs
                // moving 1 square twice)
                if (lastMove.length() == 2
                        && ChessStrings.xLookup.indexOf(Integer.parseInt(lastMove.substring(1))) == this.pos_X - 1
                        && otherPawn.doubleMoved)
                    moves.add(ChessBoard.squares[this.pos_X - 1][this.pos_Y]); // Fun fact if you're looking at this in
                                                                               // VS Code: That 'beginIndex:' isn't
                                                                               // properly monospaced at the end, which
                                                                               // is why the comment doesn't line up.
            }
            if (this.pos_X < 7 && ChessBoard.squares[this.pos_X + 1][this.pos_Y].isOccupied()
                    && ChessBoard.squares[this.pos_X + 1][this.pos_Y].pieceOnSquare().name.equals("Pawn")) {
                String lastMove = ChessBoard.pastMoves.peek(); // note: not currently sure how to make sure it was a
                                                               // double move and not just a second single move. will
                                                               // fix later.
                if (lastMove.length() == 2
                        && ChessStrings.xLookup.indexOf(Integer.parseInt(lastMove.substring(1))) == this.pos_X + 1
                        && ChessStrings.yLookup.indexOf(lastMove.substring(0, 1)) == this.pos_Y)
                    moves.add(ChessBoard.squares[this.pos_X + 1][this.pos_Y]);
            }

        } catch (IndexOutOfBoundsException e) {
            // this error is only thrown if a square was accessed and no piece was on that
            // square
        }

        return moves;
    }

}