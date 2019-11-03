/**
 * ExpressionLR.java
 *
 * Version: 1.1
 * Revision: 2
 */
import java.util.Vector;
/**
 * This program has the capability to execute the mathematical expressions based on the priority rule;
 * which is (), *%/, +-
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 */

public class ExpressionLR {

    public static void main (String args []) {
        Vector<String> aLine = new Vector<String>();

/*
	  for ( String arg: args ) {
	  }
		aLine.add(arg);
*/
// Modified input with '(', ')'
        aLine.add("(");
        aLine.add("(");
        aLine.add("2");
        aLine.add("+");
        aLine.add("2");
        aLine.add("/");
        aLine.add("4");
        aLine.add(")");
        aLine.add("+");
        aLine.add("5");
        aLine.add(")");
        aLine.add("*");
        aLine.add("3");

        if ( aLine.size() > 0 )
            System.out.println(copyExpression(aLine) + " == " + sum(aLine) );
    }

    /**
     * This function copies the vector value to a string
     * @param aLine Vector input mathematical expression to be evaluated
     * @return String The string formed by joining up the elements of the vector
     */
    public static String copyExpression (Vector aLine) {
        String rValue = "";
        for ( int index = 0; index < aLine.size(); index ++ )   {
            rValue += aLine.elementAt(index) + " ";
        }
        return rValue;
    }
    /** recognizes sum: sum [{ ('+'|'-') product }];
     */
    public static double sum (Vector s) {
        double result = product(s);

        while (s.size() > 0 ) {
            char op = ((String)s.elementAt(0)).charAt(0);
            switch (op) {
                case '+':
                    s.remove(0);
                    result = result +  product(s);
                    continue;
                case '-':
                    s.remove(0);
                    result = result - product(s);
                    continue;
                default:
                    return result;
            }
        }
        return result;
    }

    /** recognizes product: term [{ ('*'|'%'|'/') term }];
     */
    public static double product (Vector s) {
        double result = term(s);
        if (s.size() == 0 )
            return result;
        while (s.size() > 0 ) {
            char op = ((String)s.elementAt(0)).charAt(0);
            switch (op) {
                case '*':
                    s.remove(0);
                    result = result * term(s);
                    continue;
                case '/':
                    s.remove(0);
                    result = result / term(s);
                    continue;
                case '%':
                    s.remove(0);
                    result = result % term(s);
                    continue;
                default:
                    return result;
            }
        }
        return result;
    }

    /**
     * This function picks up each start of the passed vector and checks for the '(', ')' case
     * @param s Vector Input vector for processing
     * @return sum of the processed vector
     */
    public static double term (Vector s) {
        double result = 0.0;
        char op = ((String)s.elementAt(0)).charAt(0);

        switch (op) {
            case '(':
                s.remove(0);
                result = sum(s);
            case')':
                s.remove(0);
                return result;
            default:
                result = Double.parseDouble((String)s.elementAt(0));
                s.remove(0);
        }
        return result;
    }

}