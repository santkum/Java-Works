/**
 * NumberCounter.java
 *
 * @version: 1.0
 * Revision: 1
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * This class has the functionality to count the number of occurrences of each digit from 0-9 in a file containing the
 * value of pi.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */

public class NumberCounter {
    static String filename;
    static BufferedReader bufferInput;
    static InputStream inputBuffer;

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
     * This method is used to count the number of occurrences of each digit from 0-9 in the given file. This method
     * check whether the file is compressed or not and reads the file using GZipInputStream if the file is compressed or
     * reads the file using the buffered reader. Then prints the number of occurrences of each digit in the file.
     *
     * @throws IOException Exception handling for buffer reader if file is not valid
     */
    public static void numberCounter() throws IOException {
        if (filename.endsWith(".gz")) {
            try {
                inputBuffer = new GZIPInputStream(new BufferedInputStream(new FileInputStream(filename)));
                int number;
                Map<Integer, Integer> numberMeter = new HashMap<>();
                while ((number = inputBuffer.read()) != -1) {
                    if (Character.isDigit((char) number)) {
                        if (numberMeter.get(Character.getNumericValue(number)) == null) {
                            numberMeter.put(Character.getNumericValue(number), 1);
                        } else {
                            int val = numberMeter.get(Character.getNumericValue(number));
                            numberMeter.put(Character.getNumericValue(number), val + 1);
                        }
                    }
                }
                for (Integer key : numberMeter.keySet()) {
                    System.out.printf("%d\t%s\n", numberMeter.get(key), key);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File " + filename + " is not found! Try again..");
            } catch (IOException e) {
                System.out.println("File corrupted! Try again..");
            } finally {
                if (inputBuffer != null)
                    inputBuffer.close();
            }
        } else {
            try {
                 bufferInput = new BufferedReader(new FileReader(filename));
                int number;
                Map<Integer, Integer> numberMeter = new HashMap<>();
                while ((number = bufferInput.read()) != -1) {
                    if (Character.isDigit((char) number)) {
                        if (numberMeter.get(Character.getNumericValue(number)) == null) {
                            numberMeter.put(Character.getNumericValue(number), 1);
                        } else {
                            int val = numberMeter.get(Character.getNumericValue(number));
                            numberMeter.put(Character.getNumericValue(number), val + 1);
                        }
                    }
                }
                for (Integer key : numberMeter.keySet()) {
                    System.out.printf("%d\t%s\n", numberMeter.get(key), key);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File " + filename + " is not found! Try again..");
            } catch (IOException e) {
                System.out.println("File corrupted! Try again..");
            } finally {
                if (bufferInput != null)
                    bufferInput.close();
            }
        }
    }

    /**
     * This is the main function.
     *
     * @param args input args for the main function
     * @throws IOException Exception handled for buffered reader if the file is not valid and it throws a null pointer
     *                      exception
     */
    public static void main(String args[]) throws IOException {
        takeInput(args);
        long startTime = System.nanoTime();
        numberCounter();
        long endTime = System.nanoTime();
        float duration = (endTime - startTime);
        System.out.printf("Time taken %.4f seconds\n", (duration * 0.000000001));
    }
}
