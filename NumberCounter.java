/**
 * NumberCounter.java
 *
 * @version: 1.0
 * Revision: 1
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class has the functionality to count the number of occurrences of each digit from 0-9 using the number of
 * threads count specified by the user in the files specified by the user as command line arguments.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class NumberCounter extends Thread {
    static int TCounter = 0;
    static int fileCounter = 0;
    static String filepath;
    static int[][] threadFiles;
    int[] myNumberValues;
    static int[] finalCount = new int[10];
    int[] fileList;
    String openMe;

    /**
     * This is a constructor which takes buffered reader as the parameter. It initialises a buffered reader and a
     * integer array of size 10 for each object created.
     *
     * @param fileList List of number of files
     */
    NumberCounter(int[] fileList) {
        this.fileList = fileList;
        this.myNumberValues = new int[10]; // Each individual thread's storage for number count
    }

    /**
     * This method opens the file specified according to the file counter and creates the number of threads as specified
     * in the command line arguments by the user. Then creates a thread for each file and the file is read using a
     * buffered reader. Then each thread is joined such that all the threads completes their execution before the
     * program ends its execution. After the execution of each thread the value is updated into an array which has the
     * number of occurrences of each digit.
     */
    public static void init() throws InterruptedException {
        if (TCounter > 0) {
            int size = (int) Math.ceil(fileCounter / TCounter);
            threadFiles = new int[TCounter][size];
            boolean checkMe = false;
            NumberCounter[] threadList = new NumberCounter[TCounter];
            int counter = 0;
            int counter1 = 0;
            for (int index = 1; index <= fileCounter; index++) {
                if (counter == TCounter && checkMe) {
                    counter = 0;
                    counter1++;
                }
                threadFiles[counter++][counter1] = index;
                if (counter == TCounter)
                    checkMe = true;
            }
            for (int index1 = 0; index1 < TCounter; index1++) {
                threadList[index1] = new NumberCounter(threadFiles[index1]);
                threadList[index1].start();
            }
            for (int index1 = 0; index1 < TCounter; index1++) {
                threadList[index1].join();
            }
        }
    }

    /**
     * This method is run when the thread starts execution and counts the occurrences of digits 0-9 in the file while
     * the specific thread.
     */
    public void run() {
        try {
            for (int index2 = 0; index2 < fileList.length; index2++) {
                if (fileList[index2] == 0)
                    continue;
                openMe = filepath + fileList[index2];
                BufferedReader bufferInput = new BufferedReader(new FileReader(openMe));
                int number;
                while ((number = bufferInput.read()) != -1) {
                    if (Character.isDigit((char) number))
                        myNumberValues[Character.getNumericValue(number)] = myNumberValues
                                [Character.getNumericValue(number)] + 1;
                }
            }
            for (int index3 = 0; index3 < 10; index3++) {
                finalCount[index3] += myNumberValues[index3];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * This method takes the input from the user as command line arguments. It takes the number of threads, filepath and
     * the number of files.
     *
     * @param args String[] input args for the main function.
     */
    public static void takeInput(String[] args) {
        try {
            if (args.length > 0) {
                TCounter = Integer.parseInt(args[0]);
                filepath = args[1];
                fileCounter = Integer.parseInt(args[2]);
            } else {
                System.out.println("Arguments count not valid! Usage: java NumberCounter #threads filepath #files");
                System.exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Issue in input arguments processing: Usage -> java NumberCounter #threads path #files");
            System.exit(0);
        }
    }

    /**
     * This is the main method.
     *
     * @param args String[] input args for the main function
     */
    public static void main(String args[]) throws InterruptedException {
        takeInput(args);
        long startTime = System.nanoTime();
        init();
        long endTime = System.nanoTime();
        long total = endTime - startTime;
        System.out.println(total);
        for (int index = 0; index < finalCount.length; index++) {
            System.out.printf("%d\t%d\n", index, finalCount[index]);
        }
    }
}
