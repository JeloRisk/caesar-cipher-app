package com.example.alohomora;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class GuessingGame extends AppCompatActivity {
    private String[] myStrings;
    private EditText messageEditText;
    private EditText shiftEditText;
    private TextView resultTextView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);
        myStrings = getResources().getStringArray(R.array.my_strings);
        messageEditText = findViewById(R.id.message_edit_text);
        shiftEditText = findViewById(R.id.shift_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        radioGroup = findViewById(R.id.radio_group);

        Button convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String message = messageEditText.getText().toString();
                int shift = Integer.parseInt(shiftEditText.getText().toString());
                String result;
                if(selectedRadioButton.getText().toString().equals("Encrypt")) {
                    result = encrypt(message, shift);
                } else {
                    result = decrypt(message, shift);
                }
                resultTextView.setText(result);
            }
        });
    }

    // Encrypts the given message using Caesar cipher and the given shift
    private String encrypt(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ((c + shift - 65) % 26 + 65));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c + shift - 97) % 26 + 97));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private String decrypt(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ((c - shift - 65 + 26) % 26 + 65));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ((c - shift - 97 + 26) % 26 + 97));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
