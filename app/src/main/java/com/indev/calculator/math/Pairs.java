package com.indev.calculator.math;

import java.util.ArrayList;

/**
 * @author E.Drozdyk
 * @version 1.0 27 Aug 2016
 */
public class Pairs {

    private String mInputPairs;

    private ArrayList<Integer> mPair = new ArrayList<>();
    private ArrayList<Integer> mFirstIndexOfPair = new ArrayList<>();
    private ArrayList<Integer> mSecondIndexOfPair = new ArrayList<>();

    private int mAscIndex = 0; // Variable for finding index of max sub-list in ascending order
    private int mDescIndex = 0; // Variable for finding index of max sub-list in descending order
    private int mAscSizeOfSubList = 0; // Variable for finding length of max sub-list in ascending order
    private int mDescSizeOfSubList = 0; // Variable for finding length of max sub-list in descending order

    private static final int RESPONSE_CODE_LUCK = 1;
    private static final int RESPONSE_CODE_FAIL = 0;
    private static final int FIRST_INDEX_OF_PAIR = 0;
    private static final int SECOND_INDEX_OF_PAIR = 1;

    /**
     * Constructor initializes input pairs which will be calculate
     *
     * @param inputPairs
     */
    public Pairs(String inputPairs) {
        this.mInputPairs = inputPairs;
    }

    /**
     * It checks input pairs on symbols and if one or more symbol exists it return "0", else "1"
     *
     * @return RESPONSE_CODE (Luck or fail)
     */
    public int checkInputPairs() {
        String[] splitPairs = mInputPairs.split(" ");
        for (int i = 0; i < splitPairs.length; i++) {
            try {
                mPair.add(Integer.parseInt(splitPairs[i]));
            } catch (NumberFormatException e) {
                return RESPONSE_CODE_FAIL;
            }
        }
        return RESPONSE_CODE_LUCK;
    }

    /**
     * It checks input pairs on existence of two digit number and if it true it adds to array list
     *
     * @return
     */
    private boolean convertPairs() {
        boolean twoDigitNumber = false;
        for (int onePair : mPair) {
            if (String.valueOf(onePair).length() == 2) {
                twoDigitNumber = true;
                char firstIndex = String.valueOf(onePair).charAt(FIRST_INDEX_OF_PAIR);
                mFirstIndexOfPair.add(Integer.parseInt(firstIndex + ""));
                char secondIndex = String.valueOf(onePair).charAt(SECOND_INDEX_OF_PAIR);
                mSecondIndexOfPair.add(Integer.parseInt(secondIndex + ""));
            }
        }
        return twoDigitNumber;
    }

    public String getAnswer() {
        String answer = "";
        if (convertPairs()) {
            for (int i = 0; i < mFirstIndexOfPair.size(); i++) {
                if (mFirstIndexOfPair.get(i) < mSecondIndexOfPair.get(i)) {
                    int tmpSize = mSecondIndexOfPair.get(i) - mFirstIndexOfPair.get(i) + 1;
                    if (tmpSize >= mAscSizeOfSubList) {
                        mAscSizeOfSubList = tmpSize;
                        mAscIndex = i;
                    }
                } else {
                    int tmpSize = mFirstIndexOfPair.get(i) - mSecondIndexOfPair.get(i) + 1;
                    if (tmpSize >= mDescSizeOfSubList) {
                        mDescSizeOfSubList = tmpSize;
                        mDescIndex = i;
                    }
                }
            }
            answer = "Ascending order: (" + mFirstIndexOfPair.get(mAscIndex) + "," + mSecondIndexOfPair.get(mAscIndex) + ");"
                    + " Descending order: (" + mFirstIndexOfPair.get(mDescIndex) + "," + mSecondIndexOfPair.get(mDescIndex) + ").";
        }

        return answer;
    }

}
