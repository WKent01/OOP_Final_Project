public class Piece {
    char column;
    int row;
    char symbol;
    MovesList validMoves;
    char color;

    public Piece(char col, int row, char symbol, MovesList moves, char side){
        column = col;
        this.row = row;
        this.symbol = symbol;
        validMoves=moves;
        this.color = side;
    }

    public Piece(){ //Dummy piece for testing.
        column = 1;
        row = 'a';
        symbol = '?';
        validMoves = new MovesList(new Object());
        color = 'G';
    }

    /**
     * 
     * @param newCol The letter of the column the piece is moving to.
     * @param newRow The number of the row the piece is moving to.
     * @return  true if piece was moved, false if move was invalid.
     */
    public boolean move(char newCol, int newRow){
        boolean valid = true;
        //Check if move is valid.
        if(valid){
            column = newCol;
            row = newRow;
            return true;
        }
        else return false;
    }
}
