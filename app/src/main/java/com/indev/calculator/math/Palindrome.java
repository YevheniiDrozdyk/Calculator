package com.indev.calculator.math;

public class Palindrome {
    /**
     * To check the input number on palindrome, just call a method getAnswer()
     */
    private int mInputNumber;

    /**
     * Constructor initializes input number which will be calculate
     *
     * @param inputNumber
     */
    public Palindrome(int inputNumber) {
        this.mInputNumber = inputNumber;
    }

    /**
     * It returns "Palindrome" if input number tests on palindrome, else - "Not palindrome"
     * @return
     */
    public String getAnswer() {
        String result;
        if (isPalindromeNumber(mInputNumber)) {
            result = "Palindrome";
        } else {
            result = "Not palindrome";
        }

        return result;
    }

    /**
     * It checks after reversing input number on equality
     *
     * @param number
     * @return
     */
    private boolean isPalindromeNumber(int number) {
        boolean result;
        int reversedNumber = reverseNumber(number);
        result = (number == reversedNumber);

        return result;

    }

    /**
     * It revers input number
     *
     * @param number - input
     * @return
     */
    private int reverseNumber(int number) {

        char[] digitsArray = String.valueOf(number).toCharArray();
        char[] reversedDigitsArray = new char[digitsArray.length];

        for (int i = digitsArray.length - 1, j = 0; i >= 0; i--) {
            reversedDigitsArray[j++] = digitsArray[i];
        }

        return Integer.parseInt(new String(reversedDigitsArray));
    }
}