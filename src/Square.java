import javafx.geometry.Insets;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Square extends StackPane {

    final int x, y;
    private boolean occupied;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = false;
        this.setPrefHeight(100);
        this.setPrefWidth(100);
        this.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        if ((x + y) % 2 == 0) {
            this.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            this.setBackground(
                    new Background(new BackgroundFill(Color.DARKSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        this.setOnMouseClicked(e -> { // clears all glowing squares when the piece is deselected
            System.out.println("square clicked at " + this.toString());
            if (e.getTarget() instanceof Square) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        ChessBoard.squares[i][j].setEffect(new Glow(0));
                    }
                }
            }
        });
    }

    public Piece pieceOnSquare() { // returns the piece reference that's on the square
        return ((Piece) ChessBoard.squares[x][y].getChildren().get(0));
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}