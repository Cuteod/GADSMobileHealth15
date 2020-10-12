package com.example.diagnoseme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginNowActivity extends AppCompatActivity {

    private Button loginNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_now);
        loginNow = findViewById(R.id.button_login_now);


        gotoLoginPage();

    }
    private void gotoLoginPage(){
        loginNow.setOnClickListener((view)->{
            startActivity(new Intent(LoginNowActivity.this, LoginActivity.class));
            finish();
        });

    }
}