public class Board {
    Piece[][] board;
    public Board(){
        board = new Piece[8][8];
        //Initialize all pieces
    }
    public boolean move(String notation){
        //Parse notation
        Piece p = new Piece();
        char newCol = 'c';
        int newRow = '2'; //All extracted from notation
        if(p.move(newCol,newRow)){
            board[p.row][p.column-'a'] = null;
            board[newRow][newCol-'a'] = p;
        }
        return p.move(newCol,newRow);
    }
}
