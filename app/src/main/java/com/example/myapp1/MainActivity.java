package com.example.myapp1;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1EditText, num2EditText;
    Spinner operationSpinner;
    Button calculateButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1EditText = findViewById(R.id.num1EditText);
        num2EditText = findViewById(R.id.num2EditText);
        operationSpinner = findViewById(R.id.operationSpinner);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        try {
            int num1 = Integer.parseInt(num1EditText.getText().toString());
            int num2 = Integer.parseInt(num2EditText.getText().toString());
            String operation = operationSpinner.getSelectedItem().toString();
            int result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                case "^":
                    result = (int) Math.pow(num1, num2);
                    break;
                default:
                    Toast.makeText(this, "Unknown operation", Toast.LENGTH_SHORT).show();
                    return;
            }

            resultTextView.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid integers", Toast.LENGTH_SHORT).show();
        }
    }
}