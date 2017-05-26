package com.example.nathanwelch.coolcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalcActivity extends Activity {

    //still have to do it with the TextView because we will want to interact with it later
    TextView resultsView;

    public enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }

    String runningNumber = ""; //what will be the answer
    String leftValueStr = "";
    String rightValueStr = "";
    int result = 0;
    Operation currentOperation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //match a button/textView with a variable
        Button Button1 = (Button) findViewById(R.id.Button1);
        Button Button2 = (Button) findViewById(R.id.Button2);
        Button Button3 = (Button) findViewById(R.id.Button3);
        Button Button4 = (Button) findViewById(R.id.Button4);
        Button Button5 = (Button) findViewById(R.id.Button5);
        Button Button6 = (Button) findViewById(R.id.Button6);
        Button Button7 = (Button) findViewById(R.id.Button7);
        Button Button8 = (Button) findViewById(R.id.Button8);
        Button Button9 = (Button) findViewById(R.id.Button9);
        Button Button0 = (Button) findViewById(R.id.Button0);

        Button buttonClear = (Button) findViewById(R.id.buttonClear);

        ImageButton imageButtonEqual = (ImageButton) findViewById(R.id.imageButtonEqual);
        ImageButton imageButtonDiv = (ImageButton) findViewById(R.id.imageButtonDiv);
        ImageButton imageButtonMult = (ImageButton) findViewById(R.id.imageButtonMult);
        ImageButton imageButtonSub = (ImageButton) findViewById(R.id.imageButtonSub);
        ImageButton imageButtonAdd = (ImageButton) findViewById(R.id.imageButtonAdd);

        resultsView = (TextView) findViewById(R.id.answer);

        resultsView.setText("");


        //listens to the button and does something when it is clicked
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(1);
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(2);
            }
        });

        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(3);
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(4);
            }
        });

        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(5);
            }
        });

        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(6);
            }
        });

        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(7);
            }
        });

        Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(8);
            }
        });

        Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(9);
            }
        });

        Button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed(0);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightValueStr = "";
                leftValueStr = "";
                result = 0;
                runningNumber = "";
                currentOperation = null;
                resultsView.setText("0");
            }
        });

        imageButtonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperaton(Operation.EQUAL);
            }
        });

        imageButtonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperaton(Operation.DIVIDE);
            }
        });

        imageButtonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperaton(Operation.MULTIPLY);
            }
        });

        imageButtonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperaton(Operation.SUBTRACT);
            }
        });

        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperaton(Operation.ADD);
            }
        });

    }

    void processOperaton (Operation operation) {
        //if this is no the first operation -> math operations are not run until a second operation is hit
        if(currentOperation != null) {
            //but at least one operation has been pushed -> they don't have multiple digit numbers
            if(runningNumber != "") {
                rightValueStr = runningNumber;
                runningNumber = "";
                switch(currentOperation) {
                    case ADD:
                        result = Integer.parseInt(leftValueStr) + Integer.parseInt(rightValueStr);
                        break;
                    case SUBTRACT:
                        result = Integer.parseInt(leftValueStr) - Integer.parseInt(rightValueStr);
                        break;
                    case MULTIPLY:
                        result = Integer.parseInt(leftValueStr) * Integer.parseInt(rightValueStr);
                        break;
                    case DIVIDE:
                        result = Integer.parseInt(leftValueStr) / Integer.parseInt(rightValueStr);
                        break;
                }

                leftValueStr = String.valueOf(result);
                resultsView.setText(leftValueStr);
            }
        }
        else { //first time an operator has been pressed
            leftValueStr = runningNumber;
            runningNumber = "";
        }
        currentOperation = operation;
    }

    void numberPressed(int number) {
        runningNumber += String.valueOf(number);
        resultsView.setText(runningNumber);
    }
}
