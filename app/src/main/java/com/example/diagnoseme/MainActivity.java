package com.example.diagnoseme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void showToast(View view) {
        Toast.makeText(this, "Welcome to the Diagnose Me Help", Toast.LENGTH_LONG).show();
    }
}