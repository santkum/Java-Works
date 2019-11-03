/**
 * Cut.java
 *
 * @version 1.0
 * Revision 1
 */
import java.io.File;
import java.util.Scanner;

/**
 * This code has the implementation of Cut functionality by mentioning fields, delimiters and an input filename.
 * (The execution happens similar to linux; the fields, delimiters and filename has to be given during
 * class file execution)
 * All the fields are not mandatory to at time the execution. If the fields and delimiters are not given during the
 * execution call some defaults are assigned to them and for input file the user is prompted to enter one.
 * Depending on the inputs the data is processed and the output is printed.
 *
 * @author Mouna Reddy Kallu (mn9014@rit.edu)
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 *
 */
public class Cut {
    static String[] fields = new String[2];
    static String delimiter;
    static String fileName;
    static boolean isFileThere = false;
    static boolean isFieldThere = false;
    static boolean isDLThere = false;
    static String[] fileLines = new String[100];
    static Scanner scanObj = new Scanner(System.in);
    static int counter = 0;

    /**
     * The main function
     * @param args The input values given during class file execution are in this array
     */
    public static void main(String[] args) {
        String val;
        for (int index = 0; index < args.length; index++) {
            val = args[index];
            if (val.matches("^-f.*$")) {
                fields = val.substring(2).split(",");
                isFieldThere = true;
            } else if (val.matches("^-d.*$")) {
                delimiter = val.substring(2);
                isDLThere = true;
            } else if (val.matches("^-input.*$")) {
                fileName = args[index + 1];
                isFileThere = true;
            }
        }
        checkArgs();
    }

    /**
     * In this function we check if the necessary inputs are provided by the user or not. If delimiter and fields are
     * not given we are assigning the defaults and if the file is not given we prompt the user here to enter an
     * input of choice or an exit to close and call the cut command function.
     */
    public static void checkArgs() {
        if (!isDLThere) {
            setDefaultDL();
            isDLThere = true;
        }
        if (!isFieldThere) {
            setDefaultField();
            isFieldThere = true;
        }
        if (!isFileThere){
            System.out.print("Enter the file name or exit to close: ");
            fileName = scanObj.next().trim();
            if (fileName.equals("exit"))
                System.exit(0);
            isFileThere = true;
        }
        cutCommand();
    }

    /**
     * This function assigns default values to the fields array
     */
    public static void setDefaultField() {
        fields[0] = "0";
        fields[1] = "1";
    }

    /**
     * This function assigns the default delimiter
     */
    public static void setDefaultDL() {
        delimiter = "@";
    }

    /**
     * This function reads through the file and processes the input with the required fields and delimiters provided.
     */
    public static void cutCommand(){
        try {
            if (isFileThere) {
                Scanner fileObj = new Scanner(new File(fileName));
                while (fileObj.hasNextLine()) {
                    fileLines[counter] = fileObj.nextLine();
                    counter++;
                }
            }
        }catch (Exception e){
                System.out.println("Entered file is not valid!");
                System.exit(0);
        }
        for (int index = 0; index < counter; index++) {
            String[] wordsDelimited;
            wordsDelimited = fileLines[index].split(delimiter);
            for (int index1 = Integer.valueOf(fields[0]); index1 <= Integer.valueOf(fields[1]); index1++) {
                try{
                    System.out.print(wordsDelimited[index1]);
                    System.out.print(" ");
                } catch (Exception e){
                    break;
                }
            }
            System.out.println();
        }
    }
}
