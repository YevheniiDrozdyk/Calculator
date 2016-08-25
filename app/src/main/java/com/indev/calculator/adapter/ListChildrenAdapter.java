package com.indev.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.indev.calculator.EditTextChildValue;
import com.indev.calculator.R;
import com.indev.calculator.model.ListChildrenModel;

import java.util.ArrayList;

public class ListChildrenAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ViewHolder mHolder;
    private ArrayList<ListChildrenModel> mListChildren;
    private int mLastPosition;
    private float mInitialTranslation;

    public ListChildrenAdapter(Context context, ArrayList<ListChildrenModel> listChildren) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListChildren = listChildren;
    }

    @Override
    public int getCount() {
        return mListChildren.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_list_children, null);
            mHolder.editFirstName = (EditText) convertView.findViewById(R.id.editFirstNameChild);
            mHolder.editBithday = (EditText) convertView.findViewById(R.id.editBirthday);
            mHolder.editPhone = (EditText) convertView.findViewById(R.id.editPhone);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        for (int i = 0; i < mListChildren.size(); i++) {
            mListChildren.get(position).getArrayList().add(new EditTextChildValue(""));
        }
        if (!mListChildren.get(position).getArrayList().get(position).getValue().equals("")) {
            mHolder.editFirstName.setText(mListChildren.get(position).getArrayList().get(position).getValue());
        } else {
            mHolder.editFirstName.setText("");
        }
        mHolder.editFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final EditText Caption = (EditText) v;
                    mListChildren.get(position).getArrayList().get(position).setValue(Caption.getText().toString());
                }
            }
        });
        mInitialTranslation = (mLastPosition <= position ? 500f : -500f);
        convertView.setTranslationY(mInitialTranslation);
        convertView.animate()
                .setInterpolator(new DecelerateInterpolator(1.0f))
                .translationY(0f)
                .setDuration(300l)
                .setListener(null);
        mLastPosition = position;

        return convertView;
    }

    class ViewHolder {
        EditText editFirstName;
        EditText editBithday;
        EditText editPhone;
    }
}