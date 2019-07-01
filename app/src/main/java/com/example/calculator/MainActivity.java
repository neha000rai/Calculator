package com.example.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.String;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    double result, currentValue, historyValue;
    boolean flagconcat = true;
    EditText bEdit, bEdit1;
    String currentOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b0 = findViewById(R.id.btn0);
        Button b1 = findViewById(R.id.btn1);
        Button b2 = findViewById(R.id.btn2);
        Button b3 = findViewById(R.id.btn3);
        Button b4 = findViewById(R.id.btn4);
        Button b5 = findViewById(R.id.btn5);
        Button b6 = findViewById(R.id.btn6);
        Button b7 = findViewById(R.id.btn7);
        Button b8 = findViewById(R.id.btn8);
        Button b9 = findViewById(R.id.btn9);
        Button bDot = findViewById(R.id.btnDot);
        bEdit = findViewById(R.id.editText);
        bEdit1 = findViewById(R.id.editText1);
        Button bEqual = findViewById(R.id.btnEqual);
        Button bBackspace = findViewById(R.id.btnBackspace);
        Button bDivide = findViewById(R.id.btnDivide);
        Button bAdd = findViewById(R.id.btnAdd);
        Button bSub = findViewById(R.id.btnSub);
        Button bMultiply = findViewById(R.id.btnMultiply);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bSub.setOnClickListener(this);
        bMultiply.setOnClickListener(this);
        bDivide.setOnClickListener(this);
        bBackspace.setOnClickListener(this);
        bEqual.setOnClickListener(this);
        bDot.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button b;
        if (v instanceof Button) {
            b = (Button) v;
            String str = b.getText().toString();
            if (str.equals("0") || str.equals("1") || str.equals("2")
                    || str.equals("3") || str.equals("4") ||
                    str.equals("5") || str.equals("6") ||
                    str.equals("7") || str.equals("8") ||
                    str.equals("9")) {
                if (flagconcat) {
                    setCurrentValue(bEdit1.getText() + str);
                } else {
                    setCurrentValue(str);
                    flagconcat = true;
                }
                currentValue = Double.parseDouble(bEdit1.getText().toString());
            } else if (str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")) {
                flagconcat = false;
                if (currentOperator == null) {
                    historyValue = currentValue;
                } else {
                    switch (currentOperator) {
                        case "+":
                            result = historyValue + currentValue;
                            break;
                        case "-":
                            result = historyValue - currentValue;
                            break;
                        case "*":
                            result = historyValue * currentValue;
                            break;
                        case "/":
                            result = historyValue / currentValue;
                            break;
                    }
                    setCurrentValue(result + "");
                    historyValue = result;
                }
                currentOperator = str;
                bEdit.setText(bEdit.getText() + formatCurrent(currentValue + "") + currentOperator);
            } else if (str.equals("=")) {
                currentValue = Double.parseDouble(bEdit1.getText() + "");
                switch (currentOperator) {
                    case "+":
                        currentValue = historyValue + currentValue;
                        break;
                    case "-":
                        currentValue = historyValue - currentValue;
                        break;
                    case "*":
                        currentValue = historyValue * currentValue;
                        break;
                    case "/":
                        currentValue = historyValue / currentValue;
                        break;
                }
                setCurrentValue(currentValue + "");
                historyValue = 0;
                bEdit.setText("");
                currentOperator = null;
                flagconcat = false;
            } else if (str.equals(".")) {
                if (!bEdit1.getText().toString().contains("."))
                    bEdit1.setText(bEdit1.getText() + ".");
            } else if (str.equals("DEL")) {
                bEdit1.setText("");
                bEdit.setText("");
                currentValue = 0;
                historyValue = 0;
                currentOperator = null;
                flagconcat = false;
            }
        }
    }

    void setCurrentValue(String str) {
        if (str.isEmpty())
            str = "0";
        double n = Double.parseDouble(str);
        if (n == (int) n)
            str = (int) n + "";
        else
            str = n + "";
        bEdit1.setText(str);
    }

    String formatCurrent(String str) {
        if (str.isEmpty())
            return "0";
        double n = Double.parseDouble(str);
        if (n == (int) n)
            return (int) n + "";
        else
            return n + "";
    }

}

