package com.indev.calculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.indev.calculator.R;

public class PairsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View palindromeView = inflater.inflate(R.layout.fragment_pairs, container, false);

        return palindromeView;

    }

}
