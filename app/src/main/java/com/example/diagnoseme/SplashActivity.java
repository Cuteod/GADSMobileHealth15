package com.example.diagnoseme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes notification bar

        setContentView(R.layout.activity_splash);

        loadSplash();
    }

    private void loadSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent splashIntent = new Intent(SplashActivity.this, LoginNowActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}