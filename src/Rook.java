import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {

        ArrayList<Square> moves = new ArrayList<>();

        int checkX = pos_X - 1;
        // Check squares to left
        while (checkX >= 0) {
            Piece posPiece = ChessBoard.squares[checkX][pos_Y].pieceOnSquare();
            if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                moves.add(ChessBoard.squares[checkX][pos_Y]);
            checkX--;
            if (!(posPiece == null))
                break;
        }
        checkX = pos_X + 1;
        // Check squares to right
        while (checkX <= 7) {
            Piece posPiece = ChessBoard.squares[checkX][pos_Y].pieceOnSquare();
            if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                moves.add(ChessBoard.squares[checkX][pos_Y]);
            checkX++;
            if (!(posPiece == null))
                break;
        }

        int checkY = pos_Y - 1;
        // Check squares below
        while (checkY >= 0) {
            Piece posPiece = ChessBoard.squares[pos_X][checkY].pieceOnSquare();
            if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                moves.add(ChessBoard.squares[pos_X][checkY]);
            checkY--;
            if (!(posPiece == null))
                break;
        }
        checkY = pos_Y + 1;
        // Check squares above
        while (checkY <= 7) {
            Piece posPiece = ChessBoard.squares[pos_X][checkY].pieceOnSquare();
            if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                moves.add(ChessBoard.squares[pos_X][checkY]);
            checkY++;
            if (!(posPiece == null))
                break;
        }

        return moves;
    }
}
