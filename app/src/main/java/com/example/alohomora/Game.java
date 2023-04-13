// Import necessary classes and packages

package com.example.alohomora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {

    private int secretNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Generate a random number between 1 and 4
        Random random = new Random();
        secretNumber = random.nextInt(4) + 1;

        // Set up click listeners for the buttons
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess(1);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess(2);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess(3);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess(4);
            }
        });
    }

    private void checkGuess(int guess) {
        TextView textView = findViewById(R.id.textView);
        if (guess == secretNumber) {
            textView.setText("You guessed correctly!");
        } else {
            textView.setText("Wrong guess. Try again.");
        }
    }
}
