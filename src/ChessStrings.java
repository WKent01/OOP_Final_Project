import java.util.ArrayList;
import java.util.Arrays;
public class ChessStrings{
    public static final ArrayList<Integer> xLookup = new ArrayList<>(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1));
    public static final ArrayList<String>  yLookup = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
    public static String encodeMove(Piece p, Square s, boolean promoted){ //note: no support for ambiguous moves, castling
        String move = "";                        
        if(!p.name.equals("Pawn"))
            move+=p.name.substring(0,1);
        if(s.isOccupied())
            move+="x";
        move+=yLookup.get(s.y);
        move+=xLookup.get(s.x);
        if(promoted){
            move=move.substring(1)+"="+p.name.substring(0,1);
        }
        if(ChessBoard.isCheckmate(p.color.equals("White")?"Black":"White"))
            move+="#";
        else if(ChessBoard.isCheck(p.color.equals("White")?"Black":"White"));
            move+="+";
        return move;
    }
    //May be useful to add in a parser to get a Piece and Square from a String.
}