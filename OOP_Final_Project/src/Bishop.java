public class Bishop extends Piece {
    final MovesList bishopMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public Bishop(char col, int row, char color){
        super(col, row, 'B', bishopMoves, color);
    }
}
