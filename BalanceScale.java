/**
 * BalanceScale.java
 *
 * @version 1.0
 * Revision 1
 */

import java.util.*;

/**
 * This code has the capability to find the possible weights combination
 * from the list of given weights (to be placed on the right) that equalise
 * the target weight on the left side.The combination is declared on the most
 * number of weights used to balance with the target.
 *
 * @author Mouna Reddy Kallu (mk9014@rit.edu)
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 */

public class BalanceScale {
    static int[] availableWeights = {1, 3, 5, 8, 12, 35, 61};   // the list of available weights
    static ArrayList<Integer> weightsAvailable = new ArrayList<>(); // An arraylist to store the available weights
    static int sizeOfCombination = 0;   // Integer to store the length of maximum available combination
    // An arraylist to store the best available combination of the weights
    static ArrayList<Integer> bestCombination = new ArrayList<>();

    /**
     * The findBalanceWeights function will first check if the input weight is
     * in the adequate range of available weights and calls the findWeightsCombination.
     * Prints the necessary output.
     *
     * @param targetWeight integer The target or input weight that needs to be balanced
     */
    public static void findBalancedWeights(int targetWeight) {
        int minimumWeight = 0;
        int sumOfWeightsAvailable = 0;
        for (int index = 0; index < availableWeights.length; index++) {
            sumOfWeightsAvailable += availableWeights[index];
            if (minimumWeight > availableWeights[index])
                minimumWeight = availableWeights[index];
        }
        if ((targetWeight < minimumWeight) || (targetWeight > sumOfWeightsAvailable))
            System.out.println(targetWeight + "gm:\tNo");
        else {
            for (int index = 0; index < availableWeights.length; index++)
                weightsAvailable.add(availableWeights[index]);
            ArrayList<Integer> weightsCombo = new ArrayList<>();
            findWeightsCombination(weightsAvailable, targetWeight, weightsCombo);
            if (bestCombination.size() > 0)
                System.out.printf("%dgm:\tYes; used weights = %s\n", targetWeight, bestCombination.toString());
            else
                System.out.printf("%dgm:\tNo\n", targetWeight);
        }
    }

    /**
     * This function checks for the different available permutations that match
     * the weight to be balanced. It later checks for the best desirable combination
     * with the most number of weights as a group.
     *
     * @param weightsAvailable1 ArrayList Which has the set of known weights that can be used for combinations
     * @param targetWeight      Integer The target weight that needs to be balanced with the combinations
     * @param weightsCombo      ArrayList The combo list where suitable weights are picked
     */
    public static void findWeightsCombination(ArrayList<Integer> weightsAvailable1, int targetWeight,
                                              ArrayList<Integer> weightsCombo) {
        int sumOfWeightsCombo = 0;
        // Calculate the sum of weights in the weightsCombo
        for (int i = 0; i < weightsCombo.size(); i++)
            sumOfWeightsCombo += weightsCombo.get(i);
        // if the sum of weights from combo balances the target weight, it is checked for best combination
        if (sumOfWeightsCombo == targetWeight) {
            // The length of the current and the previous combination are checked to find the most weighted combo
            if (weightsCombo.size() > sizeOfCombination) {
                sizeOfCombination = weightsCombo.size();
                ArrayList<Integer> tempArray = new ArrayList<>(weightsCombo);
                // Created a temporary arraylist to reverse the set of combinations to print in descending order.
                ArrayList<Integer> tempArray1 = new ArrayList<>();
                for (int index = tempArray.size() - 1; index >= 0; index--)
                    tempArray1.add(tempArray.get(index));
                bestCombination = tempArray1;
            }
        }
        // When the weights cross the target weight the recursion is discarded
        if (sumOfWeightsCombo >= targetWeight) {
            return;
        }
        for (int j = 0; j < weightsAvailable1.size(); j++) {
            int firstWeight = weightsAvailable1.get(j);
            ArrayList<Integer> weightsAvailablePlus = new ArrayList<>();
            for (int k = j + 1; k < weightsAvailable1.size(); k++)
                weightsAvailablePlus.add(weightsAvailable1.get(k));
            weightsCombo.add(firstWeight);
            findWeightsCombination(weightsAvailablePlus, targetWeight, weightsCombo);
            weightsCombo.remove(weightsCombo.size() - 1);
        }
    }

    /**
     * The main function which iterates over the list of
     * weights to be balanced using the given functionality.
     * <p>
     * After each weight balancing the values are set to default to avoid errors.
     */
    public static void main(String args[]) {
        int[] toWeigh = {1, 6, 9, 24, 110, 115, 62, 202, 203, 204, 205};
        for (int index = 0; index < toWeigh.length; index++) {
            findBalancedWeights(toWeigh[index]);
            weightsAvailable = new ArrayList<>();
            sizeOfCombination = 0;
            bestCombination = new ArrayList<>();
        }
    }
}