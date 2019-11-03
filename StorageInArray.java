/**
 * StorageInArray.java
 *
 * @verison: 1.0
 * Revision: 1.0
 */

/**
 * The Storage class implements the array functionality for different Element types. Different functionality's
 * such as adding, removing, getting an element, checking for null, indexOf an element, finding an element
 * are tested for the given type of array.
 *
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @param <E> Element type
 */

public class StorageInArray<E> implements MyInterface<E> {
    Object[] myArray = new Object[3];
    private static int position = 0;

    /**
     * To add an input or value of type element into the array
     * @param x input value
     */

    @Override
    public void add(E x) {
        try{
            myArray[position++] = x;
        } catch (Exception ArrayIndexOutOfBoundsException) {
            position--;
            Object[] myArray1 = new Object[position+10];
            for(int index=0;index < position; index++){
                myArray1[index] = myArray[index];
            }
            myArray1[position++] = x;
            myArray = myArray1;
        }
    }

    /**
     * To get an input or value of type element from the array. If an input is provided it will be returned else
     * a null is given back.
     * @param element input value
     * @return Element
     */
    // Consequences of get()??
    @Override
    public E get(E element) {
        for (int index = 0; index < position; index++) {
            if (myArray[index] == element)
                return element;
        }
        System.out.print("Input: " + element + " not found in the list > ");
        return null;
    }

    /**
     * This method is used to find whether the given input is present in the list or not.
     * @param x input value to be found
     * @return boolean True if found, False if not found.
     */
    @Override
    public boolean find(E x) {
        for (int index = 0; index < position; index++) {
            if (myArray[index] == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * Here in this function we check if there is null present in our list of element type.
     * @return boolean, True if null is present, False if not.
     */
    @Override
    public boolean includesNull() {
        for (int index = 0; index < position; index++){
            if(myArray[index] == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function is used to remove an element from the array list
     * @param x The element to be deleted
     * @return boolean, True if element is deleted, False if not.
     */
    @Override
    public boolean delete(E x) {
        for (int index = 0; index < position; index++){
            if(myArray[index] == x) {
                for(int index1 = index; index1 < position; index1++){
                    myArray[index1] = myArray[index1 + 1];
                }
                myArray[position] = null;
                position--;
                return true;
            }
          }
        System.out.print("Element to be deleted not found > ");
        return false;
    }

    /**
     * This function is used to find the index of the element in the array list.
     * @param x The element of which the index has to be found
     * @return Int The index of the element x
     */
    @Override
    public int indexOf(E x) {
        for (int index = 0; index < position; index++) {
            if (myArray[index] == x)
                return index;
        }
        System.out.print("Element " + x + " not found > ");
        return -1;
    }

    /**
     * This function returns the values present in the array list whenever the object of the class is printed
     * @return ArrayList Returns the array list
     */
    public String toString(){
        String printMe = "";
        for (int index = 0; index < position; index++)
            printMe += myArray[index] + " ";
        return printMe;
    }
}

