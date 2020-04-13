package com.example.training;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextView mWeightText;
    private EditText mWeightEdit;
    private TextView mHeightText;
    private EditText mHeightEdit;
    private TextView mResult;
    private TextView mResultText;
    private Button mImcButton;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeightText = findViewById(R.id.weightText);
        mWeightText.setText(R.string.weight_text);

        mWeightEdit = findViewById(R.id.weightEdit);
        mWeightEdit.setHint(R.string.weight_edit);

        mHeightText = findViewById(R.id.heigthText);
        mHeightText.setText(R.string.height_text);

        mHeightEdit = findViewById(R.id.heightEdit);
        mHeightEdit.setHint(R.string.height_edit);

        mResult = findViewById(R.id.result);
        mResult.setText(R.string.result);

        mResultText = findViewById(R.id.resultText);

        mImcButton = findViewById(R.id.buttonImc);
        mImcButton.setText(R.string.imc_button);

        mResetButton = findViewById(R.id.buttonReset);

        final String mFirstSentence = getResources().getString(R.string.response_sentence);
        final String mUnderweight = getResources().getString(R.string.underweight);
        final String mNormalweight = getResources().getString(R.string.normal_weight);
        final String mOverheight = getResources().getString(R.string.overweight);
        final String mObesity = getResources().getString(R.string.obesity);




        // On attribue un listener adapté aux vues qui en ont besoin

        mHeightEdit.addTextChangedListener(textWatcher);
        mWeightEdit.addTextChangedListener(textWatcher);

        mImcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = mHeightEdit.getText().toString();

                String w = mWeightEdit.getText().toString();
                float hValue;
                float wValue;
                boolean hDigitsOnly = TextUtils.isDigitsOnly(mHeightEdit.getText());
                boolean wDigitsOnly = TextUtils.isDigitsOnly(mWeightEdit.getText());

                if (hDigitsOnly == false || wDigitsOnly == false || mHeightEdit.getText().toString() == "" || mWeightEdit.getText().toString() == "") {
                    Toast.makeText(MainActivity.this, R.string.error_value, Toast.LENGTH_SHORT).show();
                    mWeightEdit.getText().clear();
                    mHeightEdit.getText().clear();
                    mResultText.setText(R.string.instruction);
                } else {
                    hValue = Float.valueOf(h) /100;
                    wValue = Float.valueOf(w);

                    if (hValue == 0 || wValue == 0) {
                        Toast.makeText(MainActivity.this, R.string.error_value, Toast.LENGTH_SHORT).show();
                        mWeightEdit.getText().clear();
                        mHeightEdit.getText().clear();
                        mResultText.setText(R.string.instruction);
                    } else {
                        // On met au carré la taille
                        hValue = (float) Math.pow(hValue, 2);
                        float imc = wValue / hValue;

                        if (imc < 18.5f) {
                            mResultText.setText(mFirstSentence + String.valueOf(imc) + mUnderweight);
                        } else if (imc >= 18.5f && imc < 25) {
                            mResultText.setText(mFirstSentence + String.valueOf(imc) + mNormalweight);
                        } else if (imc >= 25 && imc < 35) {
                            mResultText.setText(mFirstSentence + String.valueOf(imc) + mOverheight);
                        } else {
                            mResultText.setText(mFirstSentence + String.valueOf(imc) + mObesity);
                        }
                    }
                }
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeightEdit.getText().clear();
                mHeightEdit.getText().clear();
                mResultText.setText(R.string.instruction);
            }
        });


    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mResultText.setText(R.string.instruction);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}