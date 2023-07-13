package com.example.voicecalculator;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ActivityNotFoundException;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.speech.RecognizerIntent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import net.objecthunter.exp4j.Expression;
//import net.objecthunter.exp4j.ExpressionBuilder;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class MainActivity extends AppCompatActivity {
//    private TextView txtScreen;
//    private ImageView btnSpeak;
//    private  boolean lastNumeric;
//    private boolean stateError;
//    private boolean lastDot;
//
//    private int[] numericButton={R.id.btnZero,R.id.btnOne,R.id.btnTwo,R.id.btnThree,R.id.btnFour,R.id.btnFive,R.id.btnSix,R.id.btnSeven,
//            R.id.btnEight,R.id.btnNine};
//    private int[] operatorsButton={R.id.btnAdd,R.id.btnMin,R.id.btnMult,R.id.btnDevi};
//    private final int REQ_CODE_SPEECH_INPUT=100;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnSpeak=findViewById(R.id.btnSpeak);
//        txtScreen=findViewById(R.id.txtScreen);
//
//       setNumericOnClickListener();
//        setOperatorOnClickListener();
//
//    }
//
//
//
//    private void setNumericOnClickListener() {
//        View.OnClickListener listener=new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Button button=(Button) view;
//                if(stateError){
//                    txtScreen.setText(button.getText());
//                    stateError=false;
//                }
//                else {
//                    txtScreen.append(button.getText());
//                }
//                lastNumeric=true;
//            }
//        };
//        for(int id:numericButton){
//            findViewById(id).setOnClickListener(listener);
//        }
//    }
//    private void setOperatorOnClickListener() {
//        View.OnClickListener listener=new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(lastNumeric &&! stateError){
//                    Button button =(Button) view;
//                    txtScreen.append(button.getText());
//                    lastNumeric=false;
//                    lastDot=false;
//                }
//            }
//        };
//        for(int id:operatorsButton) {
//            findViewById(id).setOnClickListener(listener);
//        }
//
//          findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View view) {
//                  if(lastNumeric && ! stateError &&! lastDot) {
//                      txtScreen.append(".");
//                      lastNumeric = false;
//                      lastDot = false;
//                  } }
//          });
//
//
//            findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    txtScreen.setText("");
//                    lastNumeric=false;
//                    stateError=false;
//                    lastDot=false;
//                }
//            });
//
//            findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    opEqual();
//
//                }
//            });
//            findViewById(R.id.btnSpeak).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(stateError){
//                        txtScreen.setText("Try Again");
//                        stateError=false;
//                    }
//                    else {
//                        speachInput();
//                    }
//                    lastNumeric=true;
//                }
//            });
//        }
//
//
//
//
//    private void opEqual() {
//        if (lastNumeric && !stateError) {
//            String text = txtScreen.getText().toString();
//
//            try {
//                Expression expression = null;
//                try {
//                    expression = new ExpressionBuilder(text).build();
//                    double result = expression.evaluate();
//                    txtScreen.setText(Double.toString(result));
//
//
//                } catch (Exception e) {
//                    txtScreen.setText("Error");
//                }
//                } catch (ArithmeticException ex) {
//                    txtScreen.setText("Error");
//                    stateError = true;
//                    lastNumeric = false;
//                }
//            }
//        }
//        private void speachInput () {
//
//            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_promot));
//
//            try {
//                startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
//            } catch (ActivityNotFoundException a) {
//
//                Toast.makeText(getApplicationContext(), getString(R.string.speech_promot), Toast.LENGTH_LONG).show();
//            }
//        }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_CODE_SPEECH_INPUT:{
//                if (requestCode == RESULT_OK && null != data) {
//                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//
//                    String change = result.get(0);
//
//                    change = change.replace("divide by", "/");
//                    change = change.replace("into", "*");
//
//                    change = change.replace("X", "*");
//                    change = change.replace("x", "*");
//
//                    change = change.replace("add", "+");
//                    change = change.replace("plus", "+");
//
//                    change = change.replace("subtract", "-");
//                    change = change.replace("subtract by", "-");
//
//                    change = change.replace("equal", "=");
//                    change = change.replace("equals", "=");
//
//                    change = change.replace("sin 30 degree", "0.5");
//                    change = change.replace("sin 30 degree", "0.5");
//                                       change = change.replace("sin 90 degree", "1");
////
////                    change = change.replace("cos 30 degree", "0.866025403784");
////                    change = change.replace("cos 30 degree", "0.866025403784");
////                    change = change.replace("cos 45 degree", "0.707106781187");
////                    change = change.replace("cos 45 degree", "0.707106781187");
////                    change = change.replace("cos 60 degree ", "0.5");
////                    change = change.replace("cos 60 degree", "0.5");
////                    change = change.replace("cos 90 degree", "0");
////                    change = change.replace("cos 90 degree", "0");
////
////                    change = change.replace("tan 30 degree", "0.57735026919");
////                    change = change.replace("tan 30 degree", "0.57735026919");
////                    change = change.replace("tan 45 degree", "1");
////                    change = change.replace("tan 45 degree", "1");
////                    change = change.replace("tan 60 degree", "1.73205080757");
////                    change = change.replace("tan 60 degree", "1.73205080757");
////                    change = change.replace("tan 90 degree", "Undefined");
////                    change = change.replace("tan 90 degree", "Undefined");
// hange = change.replace("sin 45 degree", "0.707106781187");
//                    change = change.replace("sin 45 degree", "0.707106781187");
//                    change = change.replace("sin 60 degree", "0.866025403784");
//                    change = change.replace("sin 60 degree", "0.866025403784");
//                    change = change.replace("sin 90 degree", "1");

//
//                    if (change.contains("=")) {
//                        change = change.replace("=", "");
//                        txtScreen.setText(change);
//                        opEqual();
//                    } else {
//                        txtScreen.setText(change);
//                    }
//                }
//                break;
//        }
//    }
//    }
//
//private void openResponse(String msg) {
//
//        String msgs= msg.toLowerCase(Locale.ROOT);
//        if (msgs.indexOf("open")!=1){
//        if(msgs.indexOf("google")!=1){
//        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.google.com")));
//        startActivity(intent);
//        }
//        if(msgs.indexOf("crom")!=1){
//        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.google.com")));
//        startActivity(intent);
//        }
//        if(msgs.indexOf("youtube")!=1){
//        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.youtube.com")));
//        startActivity(intent);
//        }if(msgs.indexOf("facebook")!=1){
//        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.facebook.com")));
//        startActivity(intent);
//        }
//        }
//    }


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity{
    // IDs of all the numeric buttons
    private int[] numericButtons = {R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine};
    // IDs of all the operator buttons
    private int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
    // TextView used to display the output
    private TextView txtScreen;
    // Represent whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // Represent that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;
    private ImageView btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnSpeak= (ImageView) findViewById(R.id.btnSpeak);
        // Find the TextView
        this.txtScreen = (TextView) findViewById(R.id.txtScreen);
        // Find and set OnClickListener to numeric buttons
        setNumericOnClickListener();
        // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
    }

    /**
     * Find and set OnClickListener to numeric buttons.
     */
    private void setNumericOnClickListener() {
        // Create a common OnClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                Button button = (Button) v;
                if (stateError) {
                    // If current state is Error, replace the error message
                    txtScreen.setText(button.getText());
                    stateError = false;
                } else {
                    // If not, already there is a valid expression so append to it
                    txtScreen.append(button.getText());
                }
                // Set the flag
                lastNumeric = true;
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    /**
     * Find and set OnClickListener to operator buttons, equal button and decimal point button.
     */
    private void setOperatorOnClickListener() {
        // Create a common OnClickListener for operators
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the current state is Error do not append the operator
                // If the last input is number only, append the operator
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    txtScreen.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;    // Reset the DOT flag
                }
            }
        };
        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
        // Decimal point
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    txtScreen.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtScreen.setText("");  // Clear the screen
                // Reset all the states and flags
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });
        // Equal button
        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
        findViewById(R.id.btnSpeak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stateError) {
                    // If current state is Error, replace the error message
                    txtScreen.setText("Try Again");
                    stateError = false;
                } else {
                    // If not, already there is a valid expression so append to it
                    promptSpeechInput();
                }
                // Set the flag
                lastNumeric = true;

            }
        });
    }

    /**
     * Logic to calculate the solution.
     */
    private void onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = txtScreen.getText().toString();
            // Create an Expression (A class from exp4j library)
            try {
                Expression expression=null;
                try{
                    expression = new ExpressionBuilder(txt).build();
                    double result = expression.evaluate();
                    txtScreen.setText(Double.toString(result));
                }catch (Exception e){
                    txtScreen.setText("Error");
                }
                lastDot = true; // Result contains a dot
            } catch (ArithmeticException ex) {
                // Display an error message
                txtScreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_promot));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String change=result.get(0);
                    change=change.replace("x","*");
                    change=change.replace("X","*");
                    change=change.replace("add","+");
                    change=change.replace("sub","-");
                    change=change.replace("to","2");
                    change=change.replace(" plus ","+");
                    change=change.replace(" minus ","-");
                    change=change.replace(" times ","*");
                    change=change.replace(" into ","*");
                    change=change.replace(" in2 ","*");
                    change=change.replace(" multiply by ","*");
                    change=change.replace(" divide by ","/");
                    change=change.replace("divide","/");
                    change=change.replace("equal","=");
                    change=change.replace("equals","=");
                    change = change.replace("sin 30", "0.5");


                  change = change.replace("sin 90 ", "1");

                    change = change.replace("cos 30", "0.866025403784");
                    change = change.replace("cos 30", "0.866025403784");
                    change = change.replace("cos 45", "0.707106781187");
                    change = change.replace("cos 45", "0.707106781187");
                    change = change.replace("cos 60", "0.5");
                    change = change.replace("cos 60", "0.5");
                    change = change.replace("cos 90", "0");
                    change = change.replace("cos 90", "0");

                    change = change.replace("tan 30", "0.57735026919");

                    change = change.replace("tan 45", "1");

                    change = change.replace("tan 60", "1.73205080757");
                    change = change.replace("tan 90", "Undefined");



                    if(change.contains("=")){
                        change=change.replace("=","");
                        txtScreen.setText(change);
                        onEqual();
                    }else{
                        txtScreen.setText(change);
                    }
                }
                break;
            }

        }
    }
}