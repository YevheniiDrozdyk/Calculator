package com.indev.calculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.indev.calculator.R;
import com.indev.calculator.math.Palindrome;

public class PalindromeFragment extends Fragment
{
    private Button calculatePalindrome;
    private EditText editNumberPalindrome;
    private TextView textAnswer;
    private Palindrome palindrome;
    private int putNumber;
    private String answer;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View palindromeView = inflater.inflate(R.layout.fragment_palindrome, container, false);
        editNumberPalindrome = (EditText) palindromeView.findViewById(R.id.editNumberPalindrome);
        textAnswer = (TextView) palindromeView.findViewById(R.id.textAnswer);
        calculatePalindrome = (Button) palindromeView.findViewById(R.id.btnCalculatePalindrome);
        calculatePalindrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putNumber = Integer.parseInt(editNumberPalindrome.getText().toString());
                palindrome = new Palindrome(putNumber);
                answer = palindrome.getAnswer();
                textAnswer.setText(answer);
            }

        });

        return palindromeView;

    }

}
