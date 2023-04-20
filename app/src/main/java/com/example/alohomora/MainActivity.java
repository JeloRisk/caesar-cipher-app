package com.example.alohomora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    private EditText messageEditText;
    private EditText shiftEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Declare Variables
        Button buttonstart = (Button) findViewById(R.id.button);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextPage();
            }
        });


    }


    public void goToNextPage() {
        Intent intent = new Intent(this, CaesarCipher.class);
        // Create a new bundle for the transition options
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent, bundle);
            }
        }, 500);
    }

}