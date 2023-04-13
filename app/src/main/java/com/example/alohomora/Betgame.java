package com.example.alohomora;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Betgame extends AppCompatActivity {

    private TextView timerTextView;
    private EditText guessEditText;
    private Button submitButton;
    private int targetNumber;
    private CountDownTimer timer;

    private static final int TIMER_DURATION = 20000; // 20 seconds
    private static final int TIMER_INTERVAL = 1000; // 1 second
    private static final int ADDITIONAL_TIME = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betgame);

        timerTextView = findViewById(R.id.timerTextView);
        guessEditText = findViewById(R.id.guessEditText);
        submitButton = findViewById(R.id.submitButton);

        generateTargetNumber();

        timer = new CountDownTimer(TIMER_DURATION, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(getString(R.string.timer_format, millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(Betgame.this, R.string.time_up_message, Toast.LENGTH_SHORT).show();
                resetGame();
            }
        }.start();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int guess = Integer.parseInt(guessEditText.getText().toString());

                if (guess == targetNumber) {
                    Toast.makeText(Betgame.this, R.string.correct_guess_message, Toast.LENGTH_SHORT).show();
                    long remainingTime = timerTextView.getText().toString().isEmpty() ? 0 : Long.parseLong(timerTextView.getText().toString()) * 1000;
                    timer.cancel();
                    timer = new CountDownTimer(remainingTime + ADDITIONAL_TIME, TIMER_INTERVAL) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timerTextView.setText(getString(R.string.timer_format, millisUntilFinished / 1000));
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(Betgame.this, R.string.time_up_message, Toast.LENGTH_SHORT).show();
                            resetGame();
                        }
                    }.start();
                    generateTargetNumber();
                } else {
                    Toast.makeText(Betgame.this, R.string.incorrect_guess_message, Toast.LENGTH_SHORT).show();
                }

                guessEditText.setText("");
            }
        });
    }

    private void generateTargetNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(3) + 1;
    }

    private void resetGame() {
        generateTargetNumber();
        timer.cancel();
        timer = new CountDownTimer(TIMER_DURATION, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(getString(R.string.timer_format, millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(Betgame.this, R.string.time_up_message, Toast.LENGTH_SHORT).show();
                resetGame();
            }
        }.start();
    }
}
