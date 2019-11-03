/**
 * Find.java
 *
 * @version: 1.0
 * Revision: 1
 */

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class has the functionality to verifies whether the entered command is a valid or not. If valid then finds the
 * directory name and then checks if the directory exists or not. If the directory exists then it prints all the sub
 * directories of that directory and the respective sub directories according to the command entered. It can print the
 * size, path and last modified date of the directory in EDT or GMT.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */

public class Find {
    static File fileName = null;
    static File[] filesList;

    /**
     *  This method checks for the sub directories for the given directory and the sub directories for the directories
     *  found respectively and prints the size, path, last modified time in EDT or GMT.
     * @param args     String[] Command Line Arguments
     * @param fileName File Name of the directory
     */
    public static void printOutput(String[] args, File fileName) {
        try {
            boolean nextLine = false;
            boolean isGMT = false;
            filesList = fileName.listFiles();
            if (filesList == null) {
                return;
            } else {
                for (File fileVal : filesList) {
                    printOutput(args, fileVal);
                    for (int index = 0; index < args.length; index++) {
                        String val = args[index];
                        if (val.matches("^--printFile$")) {
                            System.out.printf(getFileName(fileVal) + "\t");
                            nextLine = true;
                        } else if (val.matches("^--printDate$")) {
                            nextLine = true;
                            if (String.join("", args).contains("--printGMT")) {
                                isGMT = true;
                                System.out.print(gmtFormat(fileVal) + "\t");
                            } else {
                                System.out.print(edtFormat(fileVal) + "\t");
                            }
                        } else if (val.matches("^--printLength$")) {
                            nextLine = true;
                            System.out.print(fileVal.length() + "\t");
                        } else if (args[index].matches("^--printGMT$") && !isGMT) {
                            System.out.print(gmtFormat(fileVal) + "\t");
                        }
                    }
                    if (nextLine)
                        System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Error in printing the file contents!");
        }
    }

    /**
     * This method gets the path of the file.
     * @param fileName File Name of the directory
     * @return filename Returns the file path
     */
    public static String getFileName(File fileName) {
        return fileName.getPath().contains("\\") ? fileName.getPath().replace("\\", ":") :
                fileName.getPath().replace("/", ":");
    }

    /**
     * This method gets the last modified date of the file. The date is printed in EDT format.
     * @param fileName File Name of the directory
     * @return Date Returns the date in EDT format
     */
    public static String edtFormat(File fileName) {
        long date = fileName.lastModified();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        return dateFormat.format(new Date(date));
    }

    /**
     * This method gets the last modified date of the file. The date is printed in GMT format.
     * @param fileName File Name of the directory
     * @return Date Returns the date in GMT format
     */
    public static String gmtFormat(File fileName) {
        long date = fileName.lastModified();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date(date));
    }

    /**
     * This is the main function.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length >= 2 && String.join("", args).contains("--directory")) {
            for (int index = 0; index < args.length; index++) {
                if (args[index].matches("^--directory$")) {
                    if (!args[index + 1].contains("--")) {
                        fileName = new File(args[index + 1]);
                        if (fileName.exists())
                            printOutput(args, fileName);
                        else
                            System.out.println("Entered path/directory doesn't exist..!");
                        break;
                    } else {
                        System.out.println("The command entered is not valid!! Usage: java Find [--printFile] " +
                                "--directory path [--printDate] [--printGMT] [--printLength]");
                    }
                }
            }
        } else {
            System.out.println("The command entered is not valid!! Usage: java Find [--printFile] --directory path"
                    + " [--printDate] [--printGMT] [--printLength]");
        }
    }
}
