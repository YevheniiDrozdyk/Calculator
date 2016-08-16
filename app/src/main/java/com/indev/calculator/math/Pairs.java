package com.indev.calculator.math;

import java.util.ArrayList;

public class Pairs {

    private String inputPairs;

    private ArrayList<Integer> pair = new ArrayList<>();
    private ArrayList<Integer> firstIndexOfPair = new ArrayList<>();
    private ArrayList<Integer> secondIndexOfPair = new ArrayList<>();

    private final int RESPONSE_CODE_LUCK = 1;
    private final int RESPONSE_CODE_FAIL = 0;

    private final int FIRST_INDEX_OF_PAIR = 0;
    private final int SECOND_INDEX_OF_PAIR = 1;

    private int ascIndex = 0; // Variable for finding index of max sub-list in ascending order
    private int descIndex = 0; // Variable for finding index of max sub-list in descending order
    private int ascSizeOfSubList = 0; // Variable for finding length of max sub-list in ascending order
    private int descSizeOfSubList = 0; // Variable for finding length of max sub-list in descending order

    public Pairs(String inputPairs) {
        this.inputPairs = inputPairs;
    }

    public int checkInputPairs() {
        String[] splitPairs = inputPairs.split(" ");
        for (int i = 0; i < splitPairs.length; i++) {
            try {
                pair.add(Integer.parseInt(splitPairs[i]));
            } catch (NumberFormatException e) {
                return RESPONSE_CODE_FAIL;
            }
        }

        return RESPONSE_CODE_LUCK;
    }

    private boolean convertPairs() {
        boolean twoDigitNumber = false;
        for (int onePair : pair) {
            if (String.valueOf(onePair).length() == 2) {
                twoDigitNumber = true;
                char firstIndex = String.valueOf(onePair).charAt(FIRST_INDEX_OF_PAIR);
                firstIndexOfPair.add(Integer.parseInt(firstIndex + ""));
                char secondIndex = String.valueOf(onePair).charAt(SECOND_INDEX_OF_PAIR);
                secondIndexOfPair.add(Integer.parseInt(secondIndex + ""));
            }
        }
        return twoDigitNumber;
    }

    public String getAnswer() {
        String answer = "";
        if (convertPairs()) {
            for (int i = 0; i < firstIndexOfPair.size(); i++) {
                if (firstIndexOfPair.get(i) < secondIndexOfPair.get(i)) {
                    int tmpSize = secondIndexOfPair.get(i) - firstIndexOfPair.get(i) + 1;
                    if (tmpSize >= ascSizeOfSubList) {
                        ascSizeOfSubList = tmpSize;
                        ascIndex = i;
                    }
                } else {
                    int tmpSize = firstIndexOfPair.get(i) - secondIndexOfPair.get(i) + 1;
                    if (tmpSize >= descSizeOfSubList) {
                        descSizeOfSubList = tmpSize;
                        descIndex = i;
                    }
                }
            }
            answer = "Ascending order: (" + firstIndexOfPair.get(ascIndex) + "," + secondIndexOfPair.get(ascIndex) + ");"
                    + " Descending order: (" + firstIndexOfPair.get(descIndex) + "," + secondIndexOfPair.get(descIndex) + ").";
        }

        return answer;
    }

}
