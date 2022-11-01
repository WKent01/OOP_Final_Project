public class Queen extends Piece {
    final MovesList queenMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public Queen(char col, int row, char color){
        super(col, row, 'Q', queenMoves, color);
    }
}
