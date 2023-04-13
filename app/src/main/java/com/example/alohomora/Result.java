package com.example.alohomora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    private Button button;
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result);
//        getSupportActionBar().hide();
//        Toolbar toolbar = findViewById(R.id.toolbar); // inflate your custom Toolbar
//        setSupportActionBar(toolbar); // set it as the action bar for the activity
        getSupportActionBar().hide();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextPage();
            }
        });
    }
    public void goToNextPage() {
        Intent intent = new Intent(this, GuessingGame.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }
}