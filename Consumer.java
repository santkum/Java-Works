/**
 * Consumer.java
 *
 * @version: 1.0
 * Revision: 1
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class has the functionality to remove the random values inserted into the array list by the producers, i.e.,
 * a consumer consumes its id amount of elements each time a consumer consumes.Whenever the array list has elements less
 * than the id of the consumer, the consumer goes into the waiting state.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class Consumer extends Thread {
    ArrayList<Integer> storageC;
    int storageSizeC;
    int soOftenToConsumeC;
    int idC;

    /**
     * This is a constructor.
     * @param storage ArrayList Array List to store the value produced
     * @param storageSize int Maximum size of the array list
     * @param soOftenToConsume int Number of times the consumer has to consumer the values
     * @param id int ID of the consumer
     */
    Consumer(ArrayList<Integer> storage, int storageSize, int soOftenToConsume, int id) {
        this.storageC = storage;
        this.storageSizeC = storageSize;
        this.soOftenToConsumeC = soOftenToConsume;
        this.idC = id;
    }
    void test()   {
        if ( storageC.size() > storageSizeC  )      {
            System.out.println("overflow " + storageC.size() );
            System.exit(0);
        }
        if ( storageC.size() < 0 )       {
            System.out.println("underflow " + storageC.size() );
            System.exit(0);
        }
    }
    /**
     * This method removes number of elements as per the id of the consumer. Is the consumer id is less the number of
     * elements in the array list, then the consumer goes to the waiting state.After each consumption of the random
     * values from the array list the list is sorted. After completion of each thread, it notifies all the waiting
     * threads.
     */
    public void run() {
        System.out.println("Consumer id: " + idC);
        for (int index1 = 0; index1 < soOftenToConsumeC; index1++) {
            synchronized (storageC) {
                try {
                    while (storageC.size() < idC) {
                        System.out.println("C " + idC + ": Waiting");
                        storageC.wait();
                        System.out.println("C " + idC + ": Woke up");
                    }
                    System.out.println("C " + idC + ": ===> Removing");
                    for (int index = 0; index < idC; index++) {
                        storageC.remove(0);
                    }
                    test();
                    Collections.sort(storageC);
                    System.out.println("C " + idC + ": <=== Removed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storageC.notifyAll();
            }
        }
    }
}
