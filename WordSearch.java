/**
 * WordSearch.java
 *
 * @version 1.0
 * Revision 1
 */

import java.util.*;
import java.io.File;

/**
 * This class has the functionality to take the search file from the user and check if a file exists or not. If the file
 * exists then a word is taken as a input from user to search in the file along the horizontal and vertical lines in
 * both forward and reverse order. The line numbers in which the string or word is found is printed.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class WordSearch {
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
     * word to be searched among the file. The file and the word to be searched is filled in and the searchWord
     * function is called.
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
                    // Taking the users input
                    System.out.print("Enter the word to be searched for: ");
                    findWord = scan_obj.next();
                    searchWord(fileLines, findWord);
                }
                if (findWord.equals("exit"))
                    System.exit(0);
              // Catching the file validity exception
            } catch (Exception e) {
                System.out.println("Entered file is not valid!!");
                takeInput();
            }
        }
    }

    /**
     * This method searches the word to be found is present in the file contents in both directions or not (i.e.
     * considering the contents a 6x6 grid and checking in rows and columns) and prints all the occurrences of
     * the word along with the row or column number.
     *
     * @param arrayToBeSearched Array An array of the file contents
     * @param wordToBeFound String This is the word to be searched in the file
     */
    public static void searchWord(String[] arrayToBeSearched, String wordToBeFound) {
        for (int index = 0; index < arrayToBeSearched.length; index++) {
            String reverseString = "";
            for (int index1 = arrayToBeSearched[index].length() - 1; index1 >= 0; index1--)
                reverseString += arrayToBeSearched[index].charAt(index1);
            // Word is being checked in both the directions
            if ((arrayToBeSearched[index].contains(wordToBeFound)) || (reverseString.contains(wordToBeFound))) {
                // index less than 6 are the rows
                if (index < 6)
                    System.out.println("Found '" + wordToBeFound + "' in row: " + index + " " +
                            arrayToBeSearched[index]);
                // index more than 6 are the columns
                else
                    System.out.println("Found '" + wordToBeFound + "' in column: " + (index - 6) + " " +
                            arrayToBeSearched[index]);
            }
        }
    }
}