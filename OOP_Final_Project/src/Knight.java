public class Knight extends Piece {
    final MovesList knightMoves = new MovesList(new Object()); //Data structure for valid Pawn moves.
    public Knight(char col, int row, char color){
        super(col, row, 'N', knightMoves, color);
    }
}
