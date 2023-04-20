package com.example.alohomora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class CaesarCipher extends AppCompatActivity {
    private String[] myStrings;
    private EditText messageEditText;
    private EditText shiftEditText;
    private TextView resultTextView;
    private LinearLayout resultLayout;
    private RadioGroup radioGroup;

    private static final int READ_REQUEST_CODE = 42;
    private TextView mTextView;

    @Override
// Called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        // Call the superclass constructor and set the layout for the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);

        // Get the string array from resources and find views by ID
        myStrings = getResources().getStringArray(R.array.my_strings);
        messageEditText = findViewById(R.id.message_edit_text);
        shiftEditText = findViewById(R.id.shift_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        radioGroup = findViewById(R.id.radio_group);
        resultLayout = findViewById(R.id.result);

        // Hide the result layout if the result text view is empty
        TextView resultTextView = findViewById(R.id.result_text_view);
        if (resultTextView.getText().toString().isEmpty()) {
            resultLayout.setVisibility(View.GONE);
        }

        // Set up button listeners
        Button button = findViewById(R.id.select_file_button);
        Button convertButton = findViewById(R.id.convert_button);

        // Set up listener for the convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button and input values
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String message = messageEditText.getText().toString();
                String shiftStr = shiftEditText.getText().toString();

                // Check if the message and shift inputs are not empty
                if (message.trim().isEmpty()) {
                    resultTextView.setText("Please enter a message");
                    return;
                }
                if (shiftStr.trim().isEmpty()) {
                    resultTextView.setText("Please enter a shift");
                    return;
                }

                // Convert shift string to integer and encrypt or decrypt the message
                int shift = Integer.parseInt(shiftStr);
                String result;
                if (selectedRadioButton.getText().toString().equals("Encrypt")) {
                    result = encrypt(message, shift);
                } else {
                    result = decrypt(message, shift);
                }

                // Show the result layout and display the result text
                resultLayout.setVisibility(View.VISIBLE);
                resultTextView.setText(result);
            }
        });

        // Set up listener for the select file button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locateFile();
            }
        });



    }


    private void locateFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                Uri uri = resultData.getData();
                try {
                    String content = readTextFromUri(uri);
                    messageEditText.setText(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        inputStream.close();
        return stringBuilder.toString();
    }

    String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";



    /**
     * Encrypts a message using a Caesar cipher with the specified shift.
     *
     * @param message the message to be encrypted
     * @param shift   the number of positions to shift each letter in the alphabet
     * @return the encrypted message
     */
    private String encrypt(String message, int shift) {
        // Convert message to lowercase
        message = message.toLowerCase();

        // Create a StringBuilder to store the encrypted message
        StringBuilder result = new StringBuilder();

        // Iterate over each character in the message
        for (char c : message.toCharArray()) {
            // Check if the character is a space, newline, symbol, lowercase letter, or digit
            if (alphabet.indexOf(c) == -1) {
                // Append the character to the result without encrypting it
                result.append(c);
            } else {
                // Get the index of the character in the alphabet
                int index = alphabet.indexOf(c);

                // If the character is in the alphabet, encrypt it
                if (index != -1) {
                    // Calculate the new index after shifting
                    int newIndex = (index + shift) % alphabet.length();

                    // Append the encrypted character to the result
                    result.append(alphabet.charAt(newIndex));
                } else {
                    // If the character is not in the alphabet, append it as is
                    result.append(c);
                }
            }
        }

        return result.toString();
    }


    /**
     * Decrypts a message using the Caesar cipher with the specified shift.
     *
     * @param message the message to be decrypted
     * @param shift   the shift used to encrypt the message
     * @return the decrypted message
     */
    private String decrypt(String encryptedMessage, int shift) {
        // Convert the encrypted message to lowercase
        encryptedMessage = encryptedMessage.toLowerCase();

        // Create a StringBuilder to store the decrypted message
        StringBuilder result = new StringBuilder();

        // Iterate over each character in the encrypted message
        for (char c : encryptedMessage.toCharArray()) {
            // Check if the character is a space, newline, symbol, lowercase letter, or digit
            if (alphabet.indexOf(c) == -1) {
                // Append the character to the result without decrypting it
                result.append(c);
            } else {
                // Get the index of the character in the alphabet
                int index = alphabet.indexOf(c);

                // If the character is in the alphabet, decrypt it
                if (index != -1) {
                    // Calculate the new index after shifting backwards
                    int newIndex = (index - shift + alphabet.length()) % alphabet.length();

                    // Append the decrypted character to the result
                    result.append(alphabet.charAt(newIndex));
                } else {
                    // If the character is not in the alphabet, append it as is
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

}