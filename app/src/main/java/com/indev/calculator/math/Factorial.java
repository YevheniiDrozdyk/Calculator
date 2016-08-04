package com.indev.calculator.math;

import java.math.BigInteger;

public class Factorial {

    /**
     * To find the sum of the digits in the number, just call a method getAnswer()
     */

    private int inputNumber; //input number of factorial

    public Factorial(int inputNumber) {
        this.inputNumber = inputNumber;
    }

    public int getAnswer() {
        return getSum(getNumberFact(inputNumber));
    }

    /**
     * To find a factorial
     * @param number - input number
     * @return
     */
    private BigInteger getNumberFact(int number) {
        BigInteger factorialRes = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorialRes = factorialRes.multiply(BigInteger.valueOf(i));
        }
        return factorialRes;
    }

    /**
     * To find a sum of numbers of factorial
     * @param factorialRes - factorial of number
     * @return
     */
    private int getSum(BigInteger factorialRes) {
        int sum = 0;
        while (factorialRes.compareTo(BigInteger.ZERO) > 0) {
            sum += factorialRes.mod(BigInteger.TEN).longValue();
            factorialRes = factorialRes.divide(BigInteger.TEN);
        }
        return sum;
    }
}