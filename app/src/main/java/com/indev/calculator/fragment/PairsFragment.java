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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View pairsView = inflater.inflate(R.layout.fragment_pairs, container, false);
        editPairs = (EditText) pairsView.findViewById(R.id.editPairs);
        textAnswer = (TextView) pairsView.findViewById(R.id.textAnswer);
        calculatePairs = (Button) pairsView.findViewById(R.id.btnCalculatePairs);
        calculatePairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAnswer.setText("");
                if (!editPairs.getText().toString().equals("")) {
                    putPairs = editPairs.getText().toString().trim();
                    pairs = new Pairs(putPairs);
                    int response = pairs.checkInputPairs();
                    if (response == REQUEST_CODE) {
                        answer = pairs.getAnswer();
                        if (!answer.equals("")) {
                            textAnswer.setText(answer);
                        } else {
                            Toast.makeText(getContext(), "You don't have a two-digit number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Please, fill out this field correctly!", Toast.LENGTH_LONG).show();
                        editPairs.setText("");
                    }
                }
            }

        });

        return pairsView;

    }

}