/**
 * ProducerConsumer.java
 *
 * @version: 1.0
 * Revision: 1
 */
import java.util.ArrayList;

/**
 * This class has the functionality to create threads for the producer and consumer class as per the input arguments
 * given by the user through the command line arguments.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class ProducerConsumer extends Thread {
    static int soManyP;
    static int soMuchToProduce;
    static int soOftenToProduce;
    static int soManyC;
    static int soOftenToConsume;
    static int storageSize;
    static ArrayList<Integer> storage;

    /**
     * This method creates number of threads as specified by the user for the number of producers and number of
     * consumers. And joins each of the threads for the producer and consumer class.
     */
    public static void init() {
        try {
            Producer[] producerList = new Producer[soManyP];
            Consumer[] consumerList = new Consumer[soManyC];
            for (int index = 0; index < soManyP; index++) {
                producerList[index] = new Producer(storage, storageSize, soMuchToProduce, soOftenToProduce, index +
                        1);
                producerList[index].start();
            }
            for (int index = 0; index < soManyC; index++) {
                consumerList[index] = new Consumer(storage, storageSize, soOftenToConsume, index + 1);
                consumerList[index].start();
            }
            for (int index = 0; index < soManyP; index++)
                producerList[index].join();

            for (int index = 0; index < soManyC; index++)
                consumerList[index].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method prints the values.
     */
    public static void printValues() {
        System.out.println("# Producer = " + soManyP);
        System.out.println("# soMuchToProduce = " + soMuchToProduce);
        System.out.println("# soOftenToProduce = " + soOftenToProduce);
        System.out.println("# Consumer = " + soManyC);
        System.out.println("# soOftenToConsume = " + soOftenToConsume);
        System.out.println("# storageSize = " + storageSize);
    }

    /**
     * This method takes inputs from the command line arguments.
     * @param args input arguments to the main function
     */
    public static void takeInputs(String[] args) {
        try {
            soManyP = Integer.parseInt(args[0]);
            soMuchToProduce = Integer.parseInt(args[1]);
            soOftenToProduce = Integer.parseInt(args[2]);
            soManyC = Integer.parseInt(args[3]);
            soOftenToConsume = Integer.parseInt(args[4]);
            storageSize = Integer.parseInt(args[5]);
            storage = new ArrayList<>(storageSize);
        } catch (Exception e) {
            System.out.println("Command wrong! Usage: java PC #producer #soOftenToProduce #soMuchToProduce #Consumer" +
                    " #soOftenToConsume storageSize");
        }
    }

    /**
     * This is the main function.
     * @param args input arguments to the main function
     */
    public static void main(String[] args) {
        takeInputs(args);
        printValues();
        init();
    }
}
