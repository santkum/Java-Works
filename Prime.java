/**
 * Prime.java
 *
 * @author Santosh Kumar Nunna (sn7916@rit.edu)
 * @version 1.0
 * Revision 1
 * <p>
 * This program is used to find the sum of prime factors for a number,
 * on a condition that no more than two prime factors exist for the same.
 */
public class Prime {
    /**
     * This function has the capability to find whether the
     * given input integer is a prime number or not
     * Returns 'true' if Prime else 'false'
     *
     * @param number integer input integer which is to be checked
     * @return boolean
     */
    public static boolean isPrime(int number) {
        // A number is prime if it is not divided by any other number other than 1 and itself
        for (int index = 2; index < number; index++) {
            // If the number is divisible by any other number it is not prime
            if (number % index == 0)
                return false;
        }
        // returns true on prime verification
        return true;
    }

    /**
     * This function has the capability to calculate the sum of
     * the prime factors of the input integer
     *
     * @param inputNumber integer The input integer for which
     *                    the sum of prime factors has to be calculated.
     */
    public static void sumOfPrime(int inputNumber) {
        int copyOfInput = inputNumber; // Copying into another integer variable
        int factorCount = 0;    // Initialising the factors count
        int sumOfFactors = 0;   // Initialising the sum of factors
        String factors = "";    // A string variable to store the set of factors
        // Checking if the given number is positive and greater than 1 and further calculating sum of prime factors
        if (inputNumber > 1) {
            for (int index = 2; index <= inputNumber - 1; index++) {
                if (isPrime(index)) {
                    // The factors are calculated only if the divisor is a prime value
                    boolean checkForFactors = true;
                    while (checkForFactors) {
                        //if the input is divisible by the index or a prime number in range of input,
                        // it is added to the factor list, the counter is incremented and the copy of input is modified
                        if (copyOfInput % index == 0) {
                            factorCount++;
                            // Creating a string of factors which is added to the system print
                            if (factors == "")
                                factors = factors + index;
                            else
                                factors = factors + " + " + index;
                            sumOfFactors = sumOfFactors + index;
                            copyOfInput = copyOfInput / index;
                        } else
                            checkForFactors = false;
                    }
                }
                // If the number of prime factors are more than 2 (which is against expectation) is turned to 0
                if (factorCount > 2) {
                    sumOfFactors = 0;
                    break;
                }
            }
            // Print the sum of prime factors only if the sum is greater than zero
            if (sumOfFactors > 0)
                System.out.println("The sum of factors of " + inputNumber + " is " + sumOfFactors + " (" + factors + ")");
        }
        // Error message for numbers which are negative or less than 1
        else
            System.out.println("The input number is expected to be > 1");
    }

    /**
     * The main function where the code is being tested for the required range of inputs
     */
    public static void main(String args[]) {
        // for every input in range of 2 to 20, checking the sum of prime factors
        for (int index = 2; index <= 20; index++)
            sumOfPrime(index);
    }
}