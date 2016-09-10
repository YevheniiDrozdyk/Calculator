package com.indev.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.indev.calculator.R;
import com.indev.calculator.model.EditTextChildValue;
import com.indev.calculator.model.ListChildrenModel;

import java.util.ArrayList;

public class ListChildrenAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<ListChildrenModel> mListChildren;
    private static final int INDEX_FIRST_NAME = 0;
    private static final int INDEX_BIRTHDAY = 1;
    private static final int INDEX_PHONE = 2;
    private static final int COUNT_OF_EDIT_TEXTS = 3;
    private int mLastPosition;

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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_list_children, null);
            holder.editFirstName = (EditText) convertView.findViewById(R.id.editFirstNameChild);
            holder.editBirthday = (EditText) convertView.findViewById(R.id.editBirthday);
            holder.editPhone = (EditText) convertView.findViewById(R.id.editPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        for (int i = 0; i < mListChildren.size(); i++) {
            for (int j = 0; j < COUNT_OF_EDIT_TEXTS; j++) {
                mListChildren.get(position).getArrayList().add(new EditTextChildValue(""));
            }
        }
        if (!mListChildren.get(position).getArrayList().get(INDEX_FIRST_NAME).getValue().equals("")) {
            holder.editFirstName.setText(mListChildren.get(position).getArrayList().get(INDEX_FIRST_NAME).getValue());

        } else {
            holder.editFirstName.setText("");
        }

        if (!mListChildren.get(position).getArrayList().get(INDEX_BIRTHDAY).getValue().equals("")) {
            holder.editBirthday.setText(mListChildren.get(position).getArrayList().get(INDEX_BIRTHDAY).getValue());

        } else {
            holder.editBirthday.setText("");
        }

        if (!mListChildren.get(position).getArrayList().get(INDEX_PHONE).getValue().equals("")) {
            holder.editPhone.setText(mListChildren.get(position).getArrayList().get(INDEX_PHONE).getValue());

        } else {
            holder.editPhone.setText("");
        }

        holder.editFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final EditText Caption = (EditText) v;
                    mListChildren.get(position).getArrayList().get(INDEX_FIRST_NAME).setValue(Caption.getText().toString());
                }
            }
        });

        holder.editBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final EditText Caption = (EditText) v;
                    mListChildren.get(position).getArrayList().get(INDEX_BIRTHDAY).setValue(Caption.getText().toString());
                }
            }
        });

        holder.editPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final EditText Caption = (EditText) v;
                    mListChildren.get(position).getArrayList().get(INDEX_PHONE).setValue(Caption.getText().toString());
                }
            }
        });

        float mInitialTranslation = (mLastPosition <= position ? 500f : -500f);
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
        EditText editBirthday;
        EditText editPhone;
    }
}