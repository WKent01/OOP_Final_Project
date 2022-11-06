import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        return null;
    }
}
