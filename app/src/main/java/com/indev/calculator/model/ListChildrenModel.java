package com.indev.calculator.model;

import java.util.ArrayList;

public class ListChildrenModel {
    private String mTitle;
    private ArrayList<EditTextChildValue> mArrayList = new ArrayList<>();

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