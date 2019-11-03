/**
 * WordCounter.java
 *
 * @version: 1.0
 * Revision: 1
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class has the functionality to count the number of occurrences of each word in the file given by the user as the
 * input.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */


public class WordCounter {
    static String filename;
    static BufferedReader buffer;

    /**
     * This method takes the filename as input from the user if not entered as the command line argument.
     *
     * @param args String[] Command Line Arguments
     */
    public static void takeInput(String[] args) {
        if (args.length > 0)
            filename = args[0];
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a file name to be searched: ");
            filename = sc.next();
            sc.close();
        }
    }

    /**
     * This method is used to count the number of occurrences of each word in the given file. This method uses hash map
     * to store the words read from the file and prints all the occurrences of the words along with the number of times
     * it appeared in the file.
     *
     * @throws IOException Exception handling for buffer reader if file is not valid
     */
    public static void wordCount() throws IOException {
        try {
            buffer = new BufferedReader(new FileReader(filename));

            String st;
            String[] wordsFromLine;
            Map<String, Integer> wordMeter = new HashMap<>();

            while ((st = buffer.readLine()) != null) {
                if (st.length() > 0) {
                    wordsFromLine = st.strip().toLowerCase().replaceAll(("[^a-z'\\s]"),
                            "").split("\\s+");
                    for (String word : wordsFromLine) {
                        if (wordMeter.get(word) == null) {
                            wordMeter.put(word, 1);
                        } else {
                            int val = wordMeter.get(word);
                            wordMeter.put(word, val + 1);
                        }
                    }
                }
            }

            for (String key : wordMeter.keySet()) {
                System.out.printf("%d\t%s\n", wordMeter.get(key), key);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Entered file " + filename + " is not available! Try again..");
        } catch (Exception e) {
            System.out.println("Problem with the execution!!");
            e.printStackTrace();
        } finally {
            if (buffer != null)
                buffer.close();
        }
    }

    /**
     * This is the main function.
     *
     * @param args input args for the main function
     * @throws IOException Exception handled for buffered reader if the file is not valid and it throws a null pointer
     *                      exception
     */
    public static void main(String[] args) throws IOException {
        takeInput(args);
        wordCount();
    }
}