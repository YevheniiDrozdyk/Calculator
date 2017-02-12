package com.indev.calculator.math;

import java.math.BigInteger;

/**
 * @author E.Drozdyk
 * @version 1.0 27 Aug 2016
 */
public class Factorial {
    /**
     * To find the sum of the digits in the number, just call a method getAnswer()
     */
    private int mInputNumber;

    /**
     * Constructor initializes input number which will be calculate
     *
     * @param inputNumber
     */
    public Factorial(int inputNumber) {
        this.mInputNumber = inputNumber;
    }

    public int getAnswer() {
        return getSum(getNumberFact(mInputNumber));
    }

    /**
     * To find a factorial
     *
     * @param number - input number
     * @return factorial result of input number
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
     *
     * @param factorialRes - factorial of number
     * @return sum of numbers of factorial result
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