package com.indev.calculator.math;

import android.util.Log;

import java.util.ArrayList;

public class Pairs {

    private String inputPairs;
    private ArrayList<Integer> pair = new ArrayList<>();
    public final String TAG = "myLogs";

    public Pairs(String inputPairs) {
        this.inputPairs = inputPairs;
    }

    public void strToArray() {
        String [] splitPairs = inputPairs.split(" ");
        for(int i=0; i<splitPairs.length; i++) {
            pair.add(Integer.parseInt(splitPairs[i]));
        }
    }





    private static final int[][] INPUT_DATES = {{1, 4},
            {2, 5},
            {7, 3},
            {4, 6},
            {7, 7}};

    private static int ascIndex = 0; // Variable for finding index of max sub-list in ascending order
    private static int descIndex = 0; // Variable for finding index of max sub-list in descending order
    private static int ascSizeOfSubList = 0; // Variable for finding length of max sub-list in ascending order
    private static int descSizeOfSubList = 0; // Variable for finding length of max sub-list in descending order

    public static void main(String[] args) {

        for (int i = 0; i < INPUT_DATES.length; i++) {

            for (int j = 0; j < INPUT_DATES[i].length - 1; j++) {

                if (INPUT_DATES[i][j] < INPUT_DATES[i][j + 1]) {

                    int tmpSize = INPUT_DATES[i][j + 1] - INPUT_DATES[i][j] + 1;

                    if (tmpSize >= ascSizeOfSubList) {

                        ascSizeOfSubList = tmpSize;
                        ascIndex = i;
                        break;
                    }

                } else {

                    int tmpSize = INPUT_DATES[i][j] - INPUT_DATES[i][j + 1] + 1;

                    if (tmpSize >= descSizeOfSubList) {
                        descSizeOfSubList = tmpSize;
                        descIndex = i;
                        break;
                    }

                }

            }

        }

        System.out.println("Ascending order: " + INPUT_DATES[ascIndex][0] + "," + INPUT_DATES[ascIndex][1]);
        System.out.println("Descending order: " + INPUT_DATES[descIndex][0] + "," + INPUT_DATES[descIndex][1]);
    }
}
