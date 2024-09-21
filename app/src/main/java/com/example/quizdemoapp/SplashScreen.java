package com.example.quizdemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    private TextView appName;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        appName= findViewById(R.id.app_name);

        Typeface typeface= ResourcesCompat.getFont(this,R.font.blacklist);
        appName.setTypeface(typeface);

        Animation animation= AnimationUtils.loadAnimation(this, R.anim.myanim);
        appName.setAnimation(animation);

        mAuth = FirebaseAuth.getInstance();

        new Thread(){

            public void run(){

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(mAuth.getCurrentUser() != null){
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    SplashScreen.this.finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    SplashScreen.this.finish();
                }
            }

        }.start();

    }
}