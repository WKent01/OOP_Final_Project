public class Pawn extends Piece {
    final MovesList pawnMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public Pawn(char col, int row, char color){
        super(col, row, 'p', pawnMoves, color);
    }
}
