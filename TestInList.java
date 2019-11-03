/**
 * TestInList.java
 *
 * @version: 1.0
 * Revision: 1
 */

/**
 * This class has the functionality to test the class storage which contains the implementation of the array list.
 * This class tests the array list using generics with both the integer and string as the data type for the array list.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class TestInList {
    /**
     * This method adds, deletes , gets, finds, finds the index of the integers from the array list and also prints the
     * array list of integers.
     */
    void testInteger(){
        MyInterface<Integer> intStorage = new StorageInList<>();
        intStorage.add(1);
        intStorage.add(2);
        intStorage.add(null);
        intStorage.add(4);
        System.out.println("The integer list is :" + intStorage);
        System.out.println("Getting number 4: " + intStorage.get(4));
        System.out.println("Getting number 4: " + intStorage.get(3));
        System.out.println("Index of number 36 in the list: " + intStorage.indexOf(36));
        System.out.println("IncludesNull: " + intStorage.includesNull());
        System.out.println("Trying to delete 2: " + intStorage.delete(2));
        System.out.println("The integer list is: " + intStorage);
        System.out.println("Is 20 in the list? " + intStorage.find(20));
        System.out.println("-----------------------------------------");
    }
    /**
     * This method adds, deletes , gets, finds, finds the index of the strings from the array list and also prints the
     * array list of strings.
     */
    void testString(){
        MyInterface<String> stringStorage = new StorageInList<>();
        stringStorage.add("Hello");
        stringStorage.add(null);
        stringStorage.add("World");
        stringStorage.add("abc");
        stringStorage.add("def");
        System.out.println("The String List is: " + stringStorage);
        System.out.println("Getting the null: " + stringStorage.get(null));
        System.out.println("Index of string 'this' in the list: " + stringStorage.indexOf("this"));
        System.out.println("IncludesNull: " + stringStorage.includesNull());
        System.out.println("Trying to delete '2': " + stringStorage.delete("2"));
        System.out.println("The string list is: " + stringStorage);
        System.out.println("Is 'Hello' in the list? " + stringStorage.find("Hello"));
        System.out.println("Finding null in the list: " + stringStorage.find(null));
        System.out.println("-----------------------------------------");
    }

    /**
     * This is the main function.
     * @param args
     */
    public static void main(String args[]) {
        TestInList testMe = new TestInList();
        testMe.testInteger();
        testMe.testString();
    }
}
