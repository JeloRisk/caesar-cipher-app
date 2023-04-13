package com.example.alohomora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    // Declare Variables
    Button buttonstart;
    Button button1;
    TextView textView;

    private EditText messageEditText;
    private EditText shiftEditText;
    private TextView resultTextView;

//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
//        Toolbar toolbar = findViewById(R.id.toolbar); // inflate your custom Toolbar
//        setSupportActionBar(toolbar); // set it as the action bar for the activity
        getSupportActionBar().hide();
        // get the button view and set an onClickListener
        buttonstart = (Button) findViewById(R.id.button);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextPage();
            }
        });

        button1 = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });


    }

    // method to go to the next page (GuessingGame activity)
//    public void goToNextPage() {
//        Intent intent = new Intent(this, GuessingGame.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
//    }

//    public void goToNextPage() {
//        Intent intent = new Intent(this, GuessingGame.class);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            // Create a new bundle for the transition options
//            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
//
//            // Start the activity with the transition options bundle
//            startActivity(intent, bundle);
//        } else {
//            // For older versions of Android, start the activity without the transition animation
//            startActivity(intent);
//        }
//    }

    public void goToNextPage() {
        Intent intent = new Intent(this, GuessingGame.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Create a new bundle for the transition options
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

            // Create a handler to post a delayed message to start the activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Start the activity with the transition options bundle after the delay
                    startActivity(intent, bundle);
                }
            }, 500); // Delay the message by 500 milliseconds (0.5 seconds)
        } else {
            // For older versions of Android, start the activity without the transition animation
            startActivity(intent);
        }
    }

}