import java.util.ArrayList;
import java.util.Arrays;

public class ChessStrings {
    public static final ArrayList<Integer> yLookup = new ArrayList<>(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1));
    public static final ArrayList<String> xLookup = new ArrayList<>(
            Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

    public static String encodeMove(Piece p, Square s, boolean promoted) { // note: no support for ambiguous moves,
                                                                           // castling
                                                                           
        String move = "";
        if(p.name.equals("Knight"))
            move+="N";
        else if (!p.name.equals("Pawn"))
            move += p.name.substring(0, 1);
        
        if (s.isOccupied()){
            if(p.name.equals("Pawn")){
                move+=xLookup.get(p.pos_X);
            }
            move += "x";
        }
        move += xLookup.get(s.x);
        move += yLookup.get(s.y);
        if (promoted) {
            move = move + "=Q";
        }
        return move;
    }
    // May be useful to add in a parser to get a Piece and Square from a String.
}