package com.example.foodplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodplaner.OnBoarding.OnBoarding;

public class Splash1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash1);
        final SharedPreferences[] onBoardingScreen = new SharedPreferences[1];
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                onBoardingScreen[0] = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen[0].getBoolean("firstTime", true);

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen[0].edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    startActivity(new Intent(Splash1.this, OnBoarding.class));
                    finish();
                }
                else {
                    startActivity(new Intent(Splash1.this, MainActivity.class));
                    finish();
                }

            }
        },1500);
    }
}