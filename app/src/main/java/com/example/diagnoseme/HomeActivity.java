package com.example.diagnoseme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button signOut;
    private FirebaseAuth mFAuth = FirebaseAuth.getInstance();
    private TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        signOut=  findViewById(R.id.button_sign_out);
        welcome = findViewById(R.id.textView_welcome);

        signOutUser();
    }

    private void signOutUser(){
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();

            }
        });
    }
}