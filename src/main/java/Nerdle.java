import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Nerdle {
    public static final int NORMAL_LENGTH = 8;
    public static final int MINI_LENGTH = 6;
    public enum SymbolHint {
        USELESS,
        MISPLACED,
        CORRECT
    }
    public boolean validateExpression(String expression) {
        // Given a string, it returns whether or not it follows the syntax
        // ArithmeticExpression "=" Result
        // with ints, "+", "-", "*" and "/" as operators and
        // without parentheses or spaces AND
        // the value of ArithmeticExpression equals Result
        // Not yet implemented...
        return true;
    }
    public SymbolHint[] getHints(String guess, String solution, boolean isMini) {
        // Not yet implemented...
        if (guess == null || solution == null) return null;
        if (isMini){
            if (guess.length()!=6 || solution.length()!=6) return null;
        }
        else {
            if (guess.length()!=8 || solution.length()!=8) return null;
        }
        if (validateExpression(guess) && validateExpression(solution)){
             SymbolHint[] res;
             if (isMini) res = new SymbolHint[6];
             else res = new SymbolHint[8];
             String solutionAux = new String(solution);
             for(int i = 0; i < res.length; i++){
                 if (guess.charAt(i) == solution.charAt(i)) {
                     res[i] = SymbolHint.CORRECT;
                    solutionAux.replaceFirst(String.valueOf(guess.charAt(i)),"");
                 }
             }
             return res;
        }
        return null;
    }
}