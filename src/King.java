public class King extends Piece {
    final MovesList kingMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public King(char col, int row, char color){
        super(col, row, 'K', kingMoves, color);
    }
}
