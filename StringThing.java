/**
 * StringThing.java
 *
 * @version 1.1
 * Revision 2
 */

/**
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
class StringThing {
    public static void method(String id, String literal, String aNewString)	{
        System.out.println(id + ".	" + (literal == aNewString ));
    }
    public static void main( String args[] ) {
        String aString = "654";
        String bString = "654";
        String cString = "6" + "54";

        // The literals are ready for garbage collection once the print statement is executed.

        // False - Because, the "I" is concatenated with bString and is being compared with aString, as Java operates
        // from left to right
        // 2 Strings - "I " is one string being formed and the concatenated output with bString is the second one.
        // "I", final output are ready to be collected, and this is the earliest moment.
        System.out.println("I.	" +     bString == aString   );
        // True - A reference check is being done separately(both the variables share the same reference addresses) and
        // the output is concatenated with the string "II"
        // 2 Strings - "II " is one string being formed and the concatenated output of the brackets is the second one.
        // "II", final output are ready to be collected, and this is the earliest moment.
        System.out.println("II.	" +     ( bString == aString )   );
        // False - The bString concatenated with "" forms a new string which has a new reference which is different from
        // the reference address of aString.
        // 4 Strings - "III " is one string being formed, the second one is "", third is bString with the "" and the
        // concatenated output with the brackets output is the fourth one.
        // "III", bString + "", final output are ready to be collected, and this is the earliest moment.
        System.out.println("III	" +     ( bString  + "" == aString )   );
        // False - The bString concatenated with "" forms a new string which has a new reference which is different from
        // the reference address of aString.
        // 2 Strings - "IV " is one string being formed and the concatenated output with the outer brackets output is
        // the second one.
        // "IV", bString + "", final output are ready to be collected, and this is the earliest moment.
        System.out.println("IV.	" +     ( ( bString  + "" ) == aString )   );
        // False - The bString concatenated with "" forms a new string which has a new reference which is different from
        // the reference address of aString.
        // 3 Strings - "V " is one string being formed, the second one is bString with the "" in the inner brackets and
        // the concatenated output with the total brackets output is the third one.
        // "V", "" + bString, final output are ready to be collected, and this is the earliest moment.
        System.out.println("V.	" +     ( ( "" + bString  ) == aString )   );

        // False - The string "a.   " is being concatenated with "654" which forms a new string and has a new reference
        // which is different from aString reference.
        // 2 Strings - One is the "a " and the second is the combined output to print.
        // "a.", final output are ready to be collected, and is the earliest moment
        System.out.println("a.	" +     "654" == aString   );
        // True - "65" is concatenated with integer 4 to form a string "654" which shares the common reference
        // 3 Strings - One is the "b ", second is the "65" in the inner brackets and third is output concatenation with
        // the result of the brackets
        // "b", "65", and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("b.	" +   ( "65" + 4 == aString ) );
        // c.   654324 - When the following print is evaluated, due to precedence the 6 and 54 are multiplied and
        // concatenated with the before strings in the print statement.
        //  3 Strings - The "c. " is the first string, the concatenation of "c. " and aString is the second one and
        // adding the product to the before string to print is the third string.
        // "c", "c" + aString and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("c.	" +   aString  + 6 * 54 );
        // d.   6544654 - While evaluating from left to right, the string is added to an integer(will be converted to a
        // string) and concatenated to the end.
        // 4 Strings - The "d. " is the first string to be formed, the second and third are formed as they get
        // concatenated with 654 and 4. The fourth string is formed by the combination of aString and previous output.
        // "d", "d654", "6544", and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("d.	" +   654 + 4 + aString  );
        // e.   658654 - As precedence order comes into picture first the sum is performed in the brackets and string
        // is formed with the rest of the elements.
        // 3 String - The "e. " is the first string to be formed, the combination of the brackets addition with "e " is
        // the second string and the concatenation with aString is the final string to be formed.
        // "e", "e658", and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("e.	" +   ( 654 + 4 ) + aString   );
        // f.   6494654 - In precedence, in the brackets, first the arithmetic operation is performed. Now the string is
        // in place so integer is also converted to string and the final output is as written at the start.
        // 5 Strings - The "f. " is the first one, the 654-5 output with "" is the second, +4 is the third, + aString is
        // the fourth and the output concatenation in print is the last one.
        // "f", "", 649 + "" , 649 + "" + 4,and the final output are ready for garbage collection, and is the earliest
        // moment.
        System.out.println("f.	" +   ( 654 - 5 + "" +  4 + aString )  );
        // g.   2616654 - As per processing from left to right, the arithmetic precedence is performed and the
        // multiplication result is converted to a string and concatenated to the rest.
        // 3 Strings - The first one is the "g. " string, the second is the combination of "g" and arithmetic operation
        // and the last is the final concatenated output.
        // "g", "g" + "654*4" , and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("g.	" +   654 * 4 + aString  );
        // h.   654654 - As per processing from left to right, the arithmetic precedence is performed and the
        // division result is converted to a string and concatenated to the rest.
        // 3 Strings - The first one is the "h. " string, the second is the combination of "h" and arithmetic operation
        // and the last is the final concatenated output.
        // "h", "h" + "654/1" , and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("h.	" +   654 / 1 + aString  );
        // i.   654654 - In precedence, in the brackets, first the arithmetic operation is performed. Now the string is
        // in place so integer is also converted to string and the final output is as written at the start.
        // 3 Strings - The first one is the "i. " string, the second is the combination of "i" and arithmetic operation
        // and the last is the final concatenated output.
        // "i", "i" + "654-0" , and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("i.	" +   ( 654 - 0 )  + aString  );
        // True - The string "j.   " is being concatenated with processed output from the brackets. The brackets equate
        // two same references so the attained output is a true
        // 2 Strings - The first is "j " and the final concatenation output to print is the second string.
        // "j", and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("j.	" +     ( "654" == aString )   );
        // True - The brackets is processed first, the two string 65 and 4 form a new string which share the same
        // reference as string 654, so a string with True is formed.
        // 2 Strings - The "4" and the final output concatenation of brackets and "g" is the second string formed
        // "4", and the final output are ready for garbage collection, and is the earliest moment.
        System.out.println("g.	" +     ( "65" + "4" == "654"  )   );
        // True - The brackets is processed first, the cString is a combined string of 65 and 4 which forms a new string
        // which share the same reference as string 654, so a string with True is formed.
        // 1 Strings - The final concatenated output with the brackets output is the only string created.
        // The final output are ready for garbage collection, and is the earliest moment.
        System.out.println("h.	" +     ( "654" == cString )   );

        // True - Since string creation is not happened via new keyword the x + yz is also a literal which combined
        // shares the same reference as literal xyz
        // 7 Strings - The "1", "xyz", "x", "yz" are four new, and " ." in the method call is the fifth one. The id
        // concatenated with "." is the sixth and the final output is the seventh string formed.
        // "1", "xyz", "x", "yz", ".", id + "." and the final output are ready for garbage collection, and is the
        // earliest moment.
        method("1", "xyz", "x" + "yz");
        // True - Since string creation is not happened via new keyword the x + y + z is also a literal which combined
        // shares the same reference as literal xyz
        // 7 Strings - The "2", "y", "z" "xy" and id + "." and the final concatenated output are the strings formed.
        // "2", "y", "z" "xy" and id + "." and the final output are ready for garbage collection, and is the earliest
        // moment.
        method("2", "xyz", "x" + "y" +"z");
        // False - As processing happens the multiplication occurs first and is concatenated with the rest of the string
        // The formed output has a different address with the literal being compared
        // 6 Strings - The "3", "5", "6" + 30, "630" + 4, id + "." and the total concatenation output are the strings
        // formed in this call.
        // "3", "5", "6" + 30, "630" + 4, id + "." and the final output are ready for garbage collection, and is the earliest moment.
        method("3", "6" + "5" + "4", "6" + 5 * 6 + 4);
        // False - A new string is being created which has a different address or reference away from the string
        // constant pool. So the comparision of these references results in a false.
        // 4 Strings - new string "x", "x" + "yz", id + "." and the final concatenation output strings are formed here.
        // "x", "x" + "yz", id + "." and the final output are ready for garbage collection, and is the earliest moment.
        method("4", "xyz", new String("x") + "yz" );
        // False - The literal formed in the newString variable(6-24 - string formed) shares a different reference as
        // compared with the literal on the literal variable. So the comparision fails.
        // 4 Strings - "6" + "-2", "6-2" + 4, id + ".", final concatenation string are the formed strings in this call.
        // "6" + "-2", "6-2" + 4, id + "." and the final output are ready for garbage collection, and is the earliest moment.
        method("5", "6" + "5" + "4", "6" + (4 - 6)  + 4);
        // True - The literal formed in the newString variable is same as the literal variable which means they share
        // the same reference which results in a True.
        // 2 Strings - id+".", and the final concatenated output are the strings created here.
        // id + "." and the final output are ready for garbage collection, and is the earliest moment.
        method("6", "6" + "5" + "4", "6" + 5 + 4);
    }
}
