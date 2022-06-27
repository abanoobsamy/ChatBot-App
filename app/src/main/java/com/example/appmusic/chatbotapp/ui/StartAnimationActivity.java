package com.example.appmusic.chatbotapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.appmusic.chatbotapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);

        LottieAnimationView lottieAnimationView = findViewById(R.id.animationViewRobot);
        lottieAnimationView.setSpeed(2);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 5000);
    }
}