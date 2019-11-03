/**
 * StorageInList.java
 *
 * @verison: 1.0
 * Revision: 1.0
 */
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class implements the arrayList functionality for different Element types. Different functionality's
 * such as adding, removing, getting an element, checking for null, indexOf an element, finding an element
 * are tested for the given type of arrayList type.
 *
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @param <E> Element type
 */

public class StorageInList<E> implements MyInterface<E> {
    public List<E> myArray = new ArrayList<>();

    /**
     * To add an input or value of type element into the arrayList
     * @param x input value
     */
    @Override
    public void add(E x) {
        myArray.add(x);
    }

    /**
     * To get an input or value of type element from the arrayList. If an input is provided it will be returned else
     * a null is given back.
     * @param element input value
     * @return Element
     */
    // Consequences of get()??
    @Override
    public E get(E element) {
        if(myArray.contains(element))
            return element;
        else
            System.out.println("Input: " + element + " not found in the list.");
            return null;
    }

    /**
     * This method is used to find whether the given input is present in the list or not.
     * @param x input value to be found
     * @return boolean True if found, False if not found.
     */
    @Override
    public boolean find(E x) {
        if (myArray.indexOf(x) == -1)
            return false;
        else return true;
    }

    /**
     * Here in this function we check if there is null present in our list of element type.
     * @return boolean, True if null is present, False if not.
     */
    @Override
    public boolean includesNull() {
        return myArray.contains(null);
    }

    /**
     * This function is used to remove an element from the array list
     * @param x The element to be deleted
     * @return boolean, True if element is deleted, False if not.
     */
    @Override
    public boolean delete(E x) {
        return myArray.remove(x);
    }

    /**
     * This function is used to find the index of the element in the array list.
     * @param x The element of which the index has to be found
     * @return Int The index of the element x
     */
    @Override
    public int indexOf(E x) {
        return myArray.indexOf(x);
    }

    /**
     * This function returns the values present in the array list whenever the object of the class is printed
     * @return ArrayList Returns the array list
     */
    public String toString(){
        return myArray.toString();
    }
}

