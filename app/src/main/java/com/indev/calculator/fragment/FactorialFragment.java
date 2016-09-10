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
import com.indev.calculator.math.Factorial;

public class FactorialFragment extends Fragment {

    private Button calculateFact;
    private EditText editNumberFact;
    private TextView textAnswer;
    private Factorial factorial;
    private int putNumber;
    private int answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View factorialView = inflater.inflate(R.layout.fragment_factorial, container, false);
        editNumberFact = (EditText) factorialView.findViewById(R.id.editNumberFact);
        textAnswer = (TextView) factorialView.findViewById(R.id.textAnswer);
        calculateFact = (Button) factorialView.findViewById(R.id.btnCalculateFact);
        calculateFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAnswer.setText("");
                if (!editNumberFact.getText().toString().equals("")) {
                    putNumber = Integer.parseInt(editNumberFact.getText().toString());
                    factorial = new Factorial(putNumber);
                    answer = factorial.getAnswer();
                    textAnswer.setText(answer + "");
                }
            }
        });

        return factorialView;

    }

}
