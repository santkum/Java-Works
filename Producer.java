/**
 * Producer.java
 *
 * @version: 1.0
 * Revision: 1
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class has the functionality to produce random values and insert them into a array list. Whenever the array list
 * size reaches the maximum limit or the production rate may exceed the maximum limit, the producer goes into the
 * waiting state.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */

public class Producer extends Thread {
    ArrayList<Integer> storageP;
    int storageSizeP;
    int soMuchToProduceP;
    int soOftenToProduceP;
    int idP;
    Random rand = new Random();

    /**
     * This is a constructor.
     * @param storage ArrayList Array List to store the value produced
     * @param storageSize int Maximum size of the array list
     * @param soMuchToProduce int Number of values a generated and inserted into the list
     * @param soOftenToProduce int Number of times the producer has to produce the values
     * @param id int ID of the producer
     */
    Producer(ArrayList<Integer> storage, int storageSize, int soMuchToProduce, int soOftenToProduce, int id) {
        this.storageP = storage;
        this.storageSizeP = storageSize;
        this.soMuchToProduceP = soMuchToProduce;
        this.soOftenToProduceP = soOftenToProduce;
        this.idP = id;
    }
    void test()   {
        if ( storageP.size() > storageSizeP  )      {
            System.out.println("overflow " + storageP.size() );
            System.exit(0);
        }
        if ( storageP.size() < 0 )       {
            System.out.println("underflow " + storageP.size() );
            System.exit(0);
        }
    }
    /**
     * This method inserts a random value into the array list whenever the array list has enough space to fit all the
     * produced random numbers into the array list. After each production of the random values into the array list the
     * list is sorted. Whenever the array list size reaches the maximum storage limit or when the number of productions
     * may exceed the maximum storage limit the thread goes into waiting state. After completion of each thread, it
     * notifies all the waiting threads.
     */
    public void run() {
        System.out.println("Producer id: " + idP);
        for (int index1 = 0; index1 < soOftenToProduceP; index1++) {
            synchronized (storageP) {
                try {
                    while (storageP.size() == storageSizeP || (storageSizeP - storageP.size()) < soMuchToProduceP) {
                        System.out.println("P " + idP + ":  Waiting");
                        storageP.wait();
                        System.out.println("P " + idP + ":  woke up");
                    }
                    System.out.println("P " + idP + ": ===> Producing");
                    for (int index = 0; index < soMuchToProduceP; index++) {
                        storageP.add(rand.nextInt(1000));
                    }
                    test();
                    Collections.sort(storageP);
                    System.out.println("P " + idP + ": <=== Produced");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storageP.notifyAll();
            }
        }
    }
}
