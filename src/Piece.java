import java.util.ArrayList;

import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView {
    int pos_X, pos_Y;
    String color;
    String name;

    /**
     * 
     * @param pos_X the pieces col
     * @param pos_Y the pieces row
     * @param color
     * @param name
     */

    public Piece(int pos_X, int pos_Y, String color, String name) {
        this.pos_X = pos_X;
        this.pos_Y = pos_Y;
        this.color = color;
        this.name = name;

    }

    public String getColor() {
        return color;
    }

    public void setPieceImage() { // Sets the pieces corresponding image
        setImage(new Image("/media/" + this.color + "" + this.name + ".png"));
    }

    public void showValidMoves() { // the squares that are in validMoves glow

        for (Square square : getValidMoves()) {
            square.setEffect(new Glow(0.5));
            System.out.println(square.toString());
        }

    }

    public abstract ArrayList<Square> getValidMoves(); // method will calculate all valid moves at the pieces current
                                                       // position on the board and return a list containing all the
                                                       // squares it can go to

}
