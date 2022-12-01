import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int pos_X, int pos_Y, String color, String name) {
        super(pos_X, pos_Y, color, name);
        setPieceImage();
    }

    @Override
    public ArrayList<Square> getValidMoves() {
        ArrayList<Square> temp = new ArrayList<>();
        // Checks 2 furthest left positions.
        if (pos_X >= 2) {
            if (pos_Y <= 6) {
                Piece posPiece = ChessBoard.squares[pos_X - 2][pos_Y + 1].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X - 2][pos_Y + 1]);
            }
            if (pos_Y >= 1) {
                Piece posPiece = ChessBoard.squares[pos_X - 2][pos_Y - 1].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X - 2][pos_Y - 1]);
            }
        }
        // Checks 2 positions 1 to the left
        if (pos_X >= 1) {
            if (pos_Y <= 5) {
                Piece posPiece = ChessBoard.squares[pos_X - 1][pos_Y + 2].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X - 1][pos_Y + 2]);
            }
            if (pos_Y >= 2) {
                Piece posPiece = ChessBoard.squares[pos_X - 1][pos_Y - 2].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X - 1][pos_Y - 2]);
            }
        }

        // Checks 2 furthest right positions.
        if (pos_X <= 5) {
            if (pos_Y <= 6) {
                Piece posPiece = ChessBoard.squares[pos_X + 2][pos_Y + 1].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X + 2][pos_Y + 1]);
            }
            if (pos_Y >= 1) {
                Piece posPiece = ChessBoard.squares[pos_X + 2][pos_Y - 1].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X + 2][pos_Y - 1]);
            }
        }
        // Checks 2 positions 1 to the right
        if (pos_X <= 6) {
            if (pos_Y <= 5) {
                Piece posPiece = ChessBoard.squares[pos_X + 1][pos_Y + 2].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X + 1][pos_Y + 2]);
            }
            if (pos_Y >= 2) {
                Piece posPiece = ChessBoard.squares[pos_X + 1][pos_Y - 2].pieceOnSquare();
                if (posPiece == null || (!(posPiece.name.equals("King")) && !(posPiece.color.equals(this.color))))
                    temp.add(ChessBoard.squares[pos_X + 1][pos_Y - 2]);
            }
        }
        return temp;
    }
}
