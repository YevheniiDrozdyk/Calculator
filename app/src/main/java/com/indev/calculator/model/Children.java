package com.indev.calculator.model;

import java.util.ArrayList;

/**
 * Entity of children.
 *
 * @author E.Drozdyk
 * @version 1.0 27 Aug 2016
 */
public class Children {

    private String mTitle;
    private ArrayList<EditTextChildValue> mArrayList = new ArrayList<>();

    public Children() {

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public ArrayList<EditTextChildValue> getArrayList() {
        return mArrayList;
    }

    public void setArrayList(ArrayList<EditTextChildValue> arrayList) {
        this.mArrayList = arrayList;
    }
}