/**
 * BalanceScaleLeftRight.java
 *
 * @version 1.0
 * Revision 1
 */

import java.util.*;

/**
 * This code has the compatibility to balance the weight (unknown) on the left hand side
 * with different combinations, with arranging the known weights on both the sides of the
 * balance.
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 */
public class BalanceScaleLeftRight {
    static int[] availableWeightsPositive = {1, 3, 8, 12, 35, 61}; // List of known weights
    // We add the negative set to show differentiate as left side weights and positive to be placed on the right
    static int[] availableWeights = {-1, -2, -3, -8, -12, -35, -61, 1, 2, 3, 8, 12, 35, 61};
    static ArrayList<Integer> weightsAvailable = new ArrayList<>();
    static int sizeOfCombination = 0;   // Initialise a size variable to find the largest set combination
    static ArrayList<Integer> bestCombination = new ArrayList<>();  // A arraylist to store the weights combination

    /**
     * This function moves the known or available weights into an arraylist and calls the
     * findWeightsCombination to perform the permutations and return back the list of
     * best combination.
     *
     * @param targetWeight integer The target weight which is to be balanced using the known weights
     */
    public static void balancedWeightCombination(int targetWeight) {
        for (int index = 0; index < availableWeights.length; index++)
            weightsAvailable.add(availableWeights[index]);
        ArrayList<Integer> weightsCombo = new ArrayList<>();
        // Call the function with necessary inputs
        findWeightsCombination(weightsAvailable, targetWeight, weightsCombo);
        // If the combination length is more than 0 then it has a valid combination else its not possible to balance
        if (bestCombination.size() > 0)
            System.out.printf("%dgm:\tYes; used weights = %s\n", targetWeight, bestCombination.toString());
        else
            System.out.printf("%dgm:\tNo\n", targetWeight);
    }

    /**
     * This function picks up the weights available and performs recursive
     * combinations to find all the suitable balancing weights on both the
     * sides of the balance.
     *
     * @param weightsAvailable1 ArrayList The list of available weights used for balancing
     * @param targetWeight      integer The target weight that is to be balanced
     * @param weightsCombo      ArrayList The combination of weights that balance the left and right sides
     */
    public static void findWeightsCombination(ArrayList<Integer> weightsAvailable1, int targetWeight,
                                              ArrayList<Integer> weightsCombo) {
        int sumOfWeightsCombo = 0;
        int checkWeight = 0;
        boolean isCheckWeight = false;
        for (int i = 0; i < weightsCombo.size(); i++)
            sumOfWeightsCombo += weightsCombo.get(i);
        if (sumOfWeightsCombo == targetWeight) {
            ArrayList<Integer> tempArray = new ArrayList<>(weightsCombo);
            ArrayList<Integer> tempArray1 = new ArrayList<>();
            ArrayList<Integer> tempArray2 = new ArrayList<>();
            tempArray1 = tempArray;
            for (int z = 0; z < tempArray1.size(); z++)
                if (tempArray1.get(z) != 0)
                    tempArray2.add(tempArray1.get(z));
            if (tempArray2.size() > sizeOfCombination) {
                sizeOfCombination = tempArray2.size();
                bestCombination = tempArray2;
            }
        }
        // If the sum of the combination is more than the target then the recursion is returned
        if (sumOfWeightsCombo >= targetWeight) {
            return;
        }
        for (int j = 0; j < weightsAvailable1.size(); j++) {
            int firstWeight = weightsAvailable1.get(j);
            ArrayList<Integer> weightsAvailablePlus = new ArrayList<>();
            // A weight can be used only once either on the left or right. So whenever a weight is picked,
            // it is checked if it is placed on the other side, if used then its discarded.
            checkWeight = firstWeight * -1;
            for (int l = 0; l < weightsCombo.size(); l++)
                if (checkWeight == weightsCombo.get(l))
                    isCheckWeight = true;
            // Updating the weights available to proceed with the recursion
            for (int k = j + 1; k < weightsAvailable1.size(); k++)
                weightsAvailablePlus.add(weightsAvailable1.get(k));
            if (isCheckWeight)
                weightsCombo.add(0);
            else
                weightsCombo.add(firstWeight);
            // The recursive call with updated weights and combination
            findWeightsCombination(weightsAvailablePlus, targetWeight, weightsCombo);
            // When the recursion is returned we remove the weight added to trace back
            weightsCombo.remove(weightsCombo.size() - 1);
        }
    }

    /**
     * The main function where the inputs are looped over for the entire operation
     * to find the best balancing combinations.
     * The global variable are set to defaults for every iteration
     */
    public static void main(String args[]) {
        int[] toWeigh = {1, 2, 6, 9, 24, 110, 115, 62, 202, 203, 204, 205};
        for (int index = 0; index < toWeigh.length; index++) {
            ArrayList<Integer> weightsCombo = new ArrayList<>();
            balancedWeightCombination(toWeigh[index]);
            weightsAvailable = new ArrayList<>();
            sizeOfCombination = 0;
            bestCombination = new ArrayList<>();
        }
    }
}