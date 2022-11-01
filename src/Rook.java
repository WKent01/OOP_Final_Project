public class Rook extends Piece {
    final MovesList rookMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public Rook(char col, int row, char color){
        super(col, row, 'R', rookMoves, color);
    }
}
