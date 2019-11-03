/**
 * BattleShip.java
 *
 * @version 1.0
 * Revision 1
 */

import java.io.File;
import java.util.Scanner;

/**
 * This class has the functionality to take the battleship file from the user and check if a file exists or not. If the
 * file exists then x and y co-ordinates are taken as a input from user to find the ship in the file along the
 * horizontal and vertical lines. When all the ships are hit by the user then the program terminates.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */

public class BattleShip {
    static Scanner scannerObj = new Scanner(System.in);
    static char[][] shipArray;
    static int lineLength = 0;
    static int shipCounter = 0;

    /**
     * This is the main function.
     *
     * @param args
     */

    public static void main(String args[]) {
        takeInput();
    }

    /**
     * This method is designed to take a file name from the command prompt i.e., the user.
     * If the entered file is not valid then the appropriate message is given and will prompt to enter another file name
     * If the input file is valid, the file contents are read line by line (i.e Battle Field) and whenever a digit is
     * identified in the file contents we count it as a ship identified. Till all the ships are hit by the user
     * attachShip function is called.
     * When all the ships are hit by the user the program is terminated.
     */
    public static void takeInput() {
        System.out.print("Enter the battle ship scenario file name: ");
        String fileToBeScanned = scannerObj.next();
        if (fileToBeScanned.equals("exit")) {
            System.exit(0);
        } else {
            try{
                Scanner fileObj = new Scanner(new File(fileToBeScanned));
                Scanner fileObj1 = new Scanner(new File(fileToBeScanned));
                while (fileObj1.hasNextLine()){
                    String line = fileObj1.nextLine().trim();
                    lineLength = line.length();
                    for (char c : line.toCharArray()){
                        if (Character.isDigit(c))
                            shipCounter++;
                    }
                }
                shipArray = new char[lineLength][lineLength];
                int counter = 0;
                while(fileObj.hasNextLine()){
                    shipArray[counter] = fileObj.next().trim().toCharArray();
                    counter++;
                }
                while(shipCounter > 0)
                    attackShip();
                System.out.println("No more ships left..!");
            } catch (Exception e){
                System.out.println("Entered file is not valid!!.. Try again.." + e);
                takeInput();
            }
        }
    }

    /**
     * This method prompts the user to enter the x co-ordinate within the range of the file length. If the entered value
     * is out of range, then we prompt again to enter the new value for x co-ordinate.
     * @return yCord Value of x co-ordinate
     */

    public static int getX() {
        System.out.print("Enter the x Co-ordinate ( 0 <= x < " + lineLength + " ): ");
        int xCord = scannerObj.nextInt();
        if ((xCord < 0) || (xCord >= lineLength)) {
            System.out.println("Entered x co-ordinate is out of range.. try again!!");
            getX();
            return 0;
        } else {
            return xCord;
        }
    }

    /**
     * This method prompts the user to enter the y co-ordinate within the range of the file length. If the entered value
     * is out of range, then we prompt again to enter the new value for y co-ordinate.
     * @return yCord Value of y co-ordinate
     */

    public static int getY() {
        System.out.print("Enter the y Co-ordinate ( 0 <= y < " + lineLength + " ): ");
        int yCord = scannerObj.nextInt();
        if ((yCord < 0) || (yCord >= lineLength)) {
            System.out.println("Entered y co-ordinate is out of range.. try again!!");
            getY();
            return 0;
        } else {
            return yCord;
        }
    }

    /**
     * This method calls the getX and getY functions to get the values of the x and y co-ordinates.
     * If the value in the shipArray with these co-ordinates is a digit, it is identified as a ship and then we
     * calculate the ship length identifying the occurrences of the same digit either horizontally or vertically.
     * If a ship is identified with odd length the we check whether the position hit by the user is the middle position
     * or not. If the position hit is the middle position then we remove the whole ship from the battlefield or else the
     * occurrence of the ship location is identified as a hit in the battlefield.
     */

    public static void attackShip(){
        int xCord = getX();
        int yCord = getY();
        int partCounter = 0;
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;
        if ((Character.isDigit(shipArray[xCord][yCord]))){
            System.out.println("HIT");
            // Keeping the x coordinate constant and checking with y varying on the right side
            for (int index = yCord; index < lineLength; index++){
                if((shipArray[xCord][index] != shipArray[xCord][yCord])||(shipArray[xCord][index] == 'x'))
                    break;
                else{
                    endY = index;
                    partCounter++;
                }
            }

            // Keeping the x coordinate constant and checking with y varying on the left side
            for (int index = yCord; index >= 0; index--){
                if((shipArray[xCord][index] != shipArray[xCord][yCord])||(shipArray[xCord][index] == 'x'))
                    break;
                else{
                    startY = index;
                    partCounter++;
                }
            }

            // Keeping the y coordinate constant and checking with x varying on the down side
            for (int index = xCord; index < lineLength; index++){
                if((shipArray[index][yCord] != shipArray[xCord][yCord])||(shipArray[index][yCord] == 'x'))
                    break;
                else{
                    partCounter++;
                    endX = index;
                }
            }

            // Keeping the x coordinate constant and checking with y varying on the top side
            for (int index = xCord; index >= 0; index--){
                if((shipArray[index][yCord] != shipArray[xCord][yCord])||(shipArray[index][yCord] == 'x'))
                    break;
                else{
                    partCounter++;
                    startX = index;
                }
            }

            partCounter -= 3;
            if (partCounter > 1 && partCounter%2 != 0){
                if((startX + partCounter/2) == xCord){
                    for(int index = startX; index <= endX; index++)
                        shipArray[index][yCord] = 'x';
                }else if ((startY + partCounter/2) == yCord){
                    for(int index = startY; index <= endY; index++)
                        shipArray[xCord][index] = 'x';
                }else{
                    shipArray[xCord][yCord] = 'x';
                    partCounter = 1;
                }
            }else{
                shipArray[xCord][yCord] = 'x';
                partCounter = 1;
            }
            shipCounter -= partCounter;
        }
    }
}