package com.example.sayed.myapplication.Ui.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sayed.myapplication.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        EasySplashScreen config=new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundResource(R.drawable.ic_splash)
                .withLogo(R.drawable.ic_logo);

        View easySplashScreen =config.create();
        setContentView(easySplashScreen);
    }
}
