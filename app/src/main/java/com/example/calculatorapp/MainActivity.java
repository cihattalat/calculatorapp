package com.example.calculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private TextView operandDisplay;
    private EditText newNumber;
    private String pendingOperation = "+";

    private Double operand1 = null;
    private Double operand2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.textResult);
        operandDisplay = (TextView) findViewById(R.id.textOperand);
        newNumber = (EditText) findViewById(R.id.editTextNumber);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonMul = (Button) findViewById(R.id.buttonMul);
        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);
        Button buttonLog = (Button) findViewById(R.id.buttonLog);
        Button buttonLn = (Button) findViewById(R.id.buttonLn);
        Button buttonSqrt = (Button) findViewById(R.id.buttonSqrt);
        Button buttonSin = (Button) findViewById(R.id.buttonSin);
        Button buttonCos = (Button) findViewById(R.id.buttonCos);
        Button buttonTan = (Button) findViewById(R.id.buttonTan);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                newNumber.append(button.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(newNumber.getText().length()<1){

                 }
                 else{
                     newNumber.setText(newNumber.getText().subSequence(0, newNumber.getText().length()-1));
                 }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("");
                operandDisplay.setText("");
                newNumber.setText("");
                operand1=null;
                operand2=null;
            }
        });

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = newNumber.getText().toString();

                try{
                    Double number = Double.valueOf(value);
                    calculateOperation(number, op);
                }
                catch (NumberFormatException e){
                    Log.d("ERROR: ",e.toString());
                }

                pendingOperation = op;
                operandDisplay.setText(pendingOperation);
            }
        };

        buttonPlus.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonEqual.setOnClickListener(operationListener);
        buttonMul.setOnClickListener(operationListener);
        buttonDivide.setOnClickListener(operationListener);
        buttonLog.setOnClickListener(operationListener);
        buttonLn.setOnClickListener(operationListener);
        buttonSqrt.setOnClickListener(operationListener);
        buttonSin.setOnClickListener(operationListener);
        buttonCos.setOnClickListener(operationListener);
        buttonTan.setOnClickListener(operationListener);





    }
    private void calculateOperation(Double value, String op){
        if(operand1==null){
            operand1=value;
        }
        else {
            operand2=value;
            if(pendingOperation.equals("=")){
                pendingOperation=op;
            }

            switch (pendingOperation){
                case "=":
                    operand1=operand2;
                    break;
                case "/":
                    if(operand2==0){
                        operand1=0.0;
                    }
                    else {
                        operand1/=operand2; // operand1 = operand1/operand2;
                    }
                    break;
                case "+":
                    operand1+=operand2;
                    break;
                case "-":
                    operand1-=operand2;
                    break;
                case "x":
                    operand1*=operand2;
                    break;
                case "log":
                    operand1=Math.log10(operand2);
                    break;
                case "ln":
                    operand1=Math.log(operand2);
                    break;
                case "sin":
                    operand1=Math.sin(operand2);
                    break;
                case "cos":
                    operand1=Math.cos(operand2);
                    break;
                case "tan":
                    operand1=Math.tan(operand2);
                    break;
                case "sqrt":
                    operand1=Math.sqrt(operand2);
                    break;
            }
        }

        result.setText(operand1.toString());

        newNumber.setText("");
        operandDisplay.setText(op);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("PendingOperation", pendingOperation);
        if(operand1!=null){
            outState.putDouble("Value", operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation=savedInstanceState.getString("PendingOperation");
        operand1=savedInstanceState.getDouble("Value");
        result.setText(operand1.toString());
        operandDisplay.setText(pendingOperation);
    }
}



