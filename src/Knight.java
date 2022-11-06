import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        return null;
    }
}
