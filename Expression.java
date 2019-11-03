/**
 * Expression.java
 *
 */

import java.util.Vector;

/**
 * Explanation:
 * This class has the capability to perform the evaluation of mathematical expressions which have arithmetic operators
 * such as +,-,%,/,*. These are evaluated based on the operator precedence and the output is printed.
 *
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 * @author Santosh Kumar Nunna (sk7916@rit.edu)
 * */
public class Expression {

    public static void main (String args []) {
        Vector<String> aLine = new Vector<String>();

/*
	  for ( String arg: args ) {
	  }
		aLine.add(arg);
*/
        aLine.add("2");
        aLine.add("+");
        aLine.add("2");
        aLine.add("*");
        aLine.add("3");

        if ( aLine.size() > 0 )
            System.out.println(copyExpression(aLine) + " == " + sum(aLine) );
    }

    /**
     * This function copies the vector passed in as input as a string to a string variable
     * @param aLine Vector Input vector passed
     * @return string Converted string
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

    public static double term (Vector s) {
        double result;
        char op = ((String)s.elementAt(0)).charAt(0);

        switch (op) {
            default:
                result = Double.parseDouble((String)s.elementAt(0));
                s.remove(0);
        }
        return result;
    }

}