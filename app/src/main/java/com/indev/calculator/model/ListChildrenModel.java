package com.indev.calculator.model;

import java.util.ArrayList;

public class ListChildrenModel {
    private String mTitle;
    private ArrayList<EditTextChildrenValues> mArrayList = new ArrayList<>();

    public ListChildrenModel(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public ArrayList<EditTextChildrenValues> getArrayList() {
        return mArrayList;
    }

    public void setArrayList(ArrayList<EditTextChildrenValues> arrayList) {
        this.mArrayList = arrayList;
    }
}