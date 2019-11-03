/**
 * WordSearch2.java
 *
 * @version 1.0
 * Revision 1
 */

import java.util.*;
import java.io.File;
import java.util.regex.Pattern;

/**
 * This class has the functionality to take the search file from the user using the scanner class
 * and check if a file exists or not. If the file exists then a word or a pattern is taken as a input from user,
 * to search in the file. The word is searched along the horizontal and vertical lines in both forward and reverse
 * order. The row or column numbers in which the pattern or word is found is printed. If the file is not found,
 * an appropriate error message is given and the user will be prompted to enter another input.
 * In both the inputs from user, an exit is also accepted and the program is terminated.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class WordSearch2 {
    static String[] fileLines = new String[12];
    static String findWord = "";

    /**
     * This is the main function.
     *
     * @param args
     */
    public static void main(String args[]) {
        takeInput();
    }

    /**
     * This method is designed to take a file name from the command prompt i.e., the user.
     * If the entered file is not valid then the appropriate message is given and will prompt to enter another file name
     * If the input file is valid, the file contents are printed line by line and the user will be prompted to input the
     * word or the regex pattern to be searched among the file.
     * The file and the word/pattern to be searched is filled in and the searchWord function is called.
     * In both the input prompts if the user enters exit, program is terminated.
     */
    public static void takeInput() {
        Scanner scan_obj = new Scanner(System.in);
        System.out.print("Enter the file name to be searched for: ");
        String fileToBeScanned = scan_obj.next();
        if (fileToBeScanned.equals("exit")) {
            System.exit(0);
        } else {
            try {
                Scanner fileObj = new Scanner(new File(fileToBeScanned));
                System.out.println("Word search puzzle file name: " + fileToBeScanned);
                int counter = 0;
                while (fileObj.hasNextLine()) {
                    fileLines[counter] = fileObj.nextLine();
                    System.out.println(fileLines[counter]);
                    counter++;
                }
                int place = 0;
                for (int index = 6; index < fileLines.length; index++) {
                    String wordFormer = "";
                    for (int index1 = 0; index1 < 6; index1++) {
                        wordFormer += fileLines[index1].charAt(place);
                    }
                    fileLines[index] = wordFormer;
                    place++;
                }
                while (findWord.equals("exit") != true) {
                    // taking the word or regex pattern input from user
                    System.out.print("Enter the pattern or word to be searched for: ");
                    findWord = scan_obj.next();
                    searchWord(fileLines, findWord);
                }
                if (findWord.equals("exit"))
                    System.exit(0);
                // Exception handling for file not valid or not present
            } catch (Exception e) {
                System.out.println("Entered file is not valid!!");
                takeInput();
            }
        }
    }

    /**
     * This method searches the word/pattern to be found is present in the file contents in both directions or not (i.e.
     * considering the contents a 6x6 grid and checking in rows and columns) and prints all the occurrences of
     * the word/pattern along with the row or column number.
     *
     * @param arrayToBeSearched Array An array of the file contents
     * @param expressionGiven   String This is the word/pattern to be searched in the file
     */
    public static void searchWord(String[] arrayToBeSearched, String expressionGiven) {
        for (int index = 0; index < arrayToBeSearched.length; index++) {
            String reverseString = "";
            for (int index1 = arrayToBeSearched[index].length() - 1; index1 >= 0; index1--)
                reverseString += arrayToBeSearched[index].charAt(index1);
            // The pattern is compiled
            Pattern patternObj = Pattern.compile(expressionGiven);
            // The find function is used to check whether the pattern or word given is present in the strings of the
            // array in both the directions
            if (patternObj.matcher(arrayToBeSearched[index]).find() || patternObj.matcher(reverseString).find()) {
                // Row elements
                if (index < 6)
                    System.out.println("Found '" + expressionGiven + "' in row: " + index + " " +
                            arrayToBeSearched[index]);
                // Column elements
                else
                    System.out.println("Found '" + expressionGiven + "' in column: " + (index - 6) + " " +
                            arrayToBeSearched[index]);
            }
        }
    }
}