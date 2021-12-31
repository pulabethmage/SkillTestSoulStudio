package com.pulasthi.googlebooksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent;
                mainIntent = new Intent(MainActivity.this, LoadBooks.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}