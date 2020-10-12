package com.example.diagnoseme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



        registerUser();

        mIHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPage();
            }
        });
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

                if(email.isEmpty()){
                    mEmail.setError("Please provide an email address");
                    return;
                }
                if(password.isEmpty()){
                    mPassword.setError("Password is required");
                    return;
                }
                if(confirmPassword.isEmpty()){
                    mConfirmPassword.setError("please put in same password");
                    return;
                }

                if(!password.equals(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mfAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showProgressBar();
                            Log.d(TAG, "onComplete: Succesful" +task.isSuccessful());
                            Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            mfAuth.signOut();
                            goToLoginPage();

                        }else{
                            Log.d(TAG, "onComplete: Not Successful" +task.getResult());
                            Toast.makeText(SignUpActivity.this, "Unable to Register", Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    }
                });

            }
        });
    }

}