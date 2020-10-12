package com.example.diagnoseme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private TextView mHeader;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mSignUp;
    private ProgressBar progressBar;
    private TextView mIHaveAnAccount;
    private FirebaseAuth mfAuth = FirebaseAuth.getInstance();
    private static final String TAG = "SignUpActivity";
    private static final String DEBUG_TAG = "NetworkStatusExample";
    private EditText mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mHeader = findViewById(R.id.textView_create_account);
        mUserName = findViewById(R.id.editTextT_user_name);
        mEmail = findViewById(R.id.editText_email_signup);
        mPassword = findViewById(R.id.editText_password_signUp);
        mConfirmPassword = findViewById(R.id.editText_confirm_password);
        mSignUp = findViewById(R.id.button_signUp);
        mIHaveAnAccount = findViewById(R.id.textView_gotoLogin);
        progressBar = findViewById(R.id.progressBar_reg);

        // To do check network
       checkForNetwork();

        registerUser();

        mIHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage();
            }
        });
    }



    private void checkForNetwork(){

        if(!isNetworkConnected()) {
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

    private void goToLoginPage() {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        finish();
    }
    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void registerUser(){
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mUserName.getText().toString();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();
                int passwordLength = 6;
                if (email.isEmpty()) {
                    mEmail.setError("Please provide an email address");
                    return;
                } else if (password.isEmpty()) {
                    mPassword.setError("Password is required");
                    return;
                } else if (confirmPassword.isEmpty()) {
                    mConfirmPassword.setError("please put in same password");
                    return;
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < passwordLength) {
                    mPassword.setError("password must be more that 6 character");
                    return;
                } else {


                    mfAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showProgressBar();
                                Log.d(TAG, "onComplete: Successful" + task.isSuccessful());
                                Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                mfAuth.signOut();
                                goToLoginPage();

                            } else {
                                Log.d(TAG, "onComplete: Not Successful" );
                                Toast.makeText(SignUpActivity.this, "Unable to Register", Toast.LENGTH_SHORT).show();
                                hideProgressBar();
                            }
                        }
                    });

                }
            }
        });
    }

}