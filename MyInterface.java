/**
 * MyInterface.java
 *
 * @version: 1.0
 * Revision: 1
 */

/**
 * We create this interface to define the necessary methods to test the generic list created.
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */

/**
 * Generic interface of element type.
 * @param <E> Element type
 */
public interface MyInterface<E> {
    void add(E x);
    E get(E element);
    boolean find(E x);
    boolean includesNull();
    boolean delete(E x);
    int indexOf(E x);
}