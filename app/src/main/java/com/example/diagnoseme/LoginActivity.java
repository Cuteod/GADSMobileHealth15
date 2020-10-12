package com.example.diagnoseme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView signUp;
    private FirebaseAuth mfAuth = FirebaseAuth.getInstance();
    Button login ;
    EditText email;
    EditText passWord;
    private ProgressBar progressBar;
    private static final String TAG = "LoginActivity";
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUp = findViewById(R.id.textView_signUp);
        login = findViewById(R.id.button_login);
        email = findViewById(R.id.editTextT_email);
        passWord = findViewById(R.id.editText_password_login);
        progressBar = findViewById(R.id.progressBar);
        constraintLayout = findViewById(R.id.constraintLayout);
        hideProgressBar();

        checkForNetwork();

        checkIfUserIsSignedIn();

        signUp();

        loginUSer();


    }


    public void signUp(){
        signUp.setOnClickListener((view)->{
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });
    }

    private  void sendResetPassword(){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }
    private void checkForNetwork() {

        if (!isNetworkConnected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage("No Network Available");
            builder.show();
        }
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void loginUSer(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = email.getText().toString().trim();
                String password = passWord.getText().toString();


                if(newEmail.isEmpty()){
                    email.setError("Email is required");
                    return;
                }
                else if(password.isEmpty()){
                    passWord.setError("password is required");
                    return;
                }


                mfAuth.signInWithEmailAndPassword(newEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showProgressBar();
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: login Successful" + task.isSuccessful());
                            Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mfAuth.getCurrentUser();
                            goToMainPage();
                        }

                        else  {
                                Log.d(TAG, "You are not Registered");
                            Toast.makeText(LoginActivity.this, "Unable to Login", Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    }
                });
            }
        });
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void checkIfUserIsSignedIn(){
        if(mfAuth.getCurrentUser()!= null){
            goToMainPage();

        }

    }

    private void goToMainPage(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}