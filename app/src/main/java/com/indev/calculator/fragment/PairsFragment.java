package com.indev.calculator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indev.calculator.R;
import com.indev.calculator.math.Pairs;

public class PairsFragment extends Fragment {

    private Button calculatePairs;
    private EditText editPairs;
    private TextView textAnswer;
    private Pairs pairs;
    private String putPairs;
    private String answer;

    private final int REQUEST_CODE = 1;
    private final String INPUT_ERROR = "Please, fill out the correct input field";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View palindromeView = inflater.inflate(R.layout.fragment_pairs, container, false);
        editPairs = (EditText) palindromeView.findViewById(R.id.editPairs);
        textAnswer = (TextView) palindromeView.findViewById(R.id.textAnswer);
        calculatePairs = (Button) palindromeView.findViewById(R.id.btnCalculatePairs);
        calculatePairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editPairs.getText().toString().equals("")) {
                    putPairs = editPairs.getText().toString();
                    pairs = new Pairs(putPairs);
                    int response = pairs.checkInputPairs();
                    if (response == REQUEST_CODE) {
                        answer = pairs.getAnswer();
                        textAnswer.setText(answer);
                    } else {
                        Toast.makeText(getContext(), INPUT_ERROR, Toast.LENGTH_LONG).show();
                        editPairs.setText("");
                    }
                }
            }

        });

        return palindromeView;

    }

}
