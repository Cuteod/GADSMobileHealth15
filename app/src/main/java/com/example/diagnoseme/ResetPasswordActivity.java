package com.example.diagnoseme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "ResetPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = findViewById(R.id.editText_email_reset);
        resetPassword = findViewById(R.id.button_reset_password);
        firebaseAuth = FirebaseAuth.getInstance();


        sendResetPassword();
    }

    public void sendResetPassword(){
        resetPassword.setOnClickListener((view)->{
            String email_reset = email.getText().toString();

            if(TextUtils.isEmpty(email_reset)) {
                email.setError("Provide an Email");
                return;
            }

            firebaseAuth.sendPasswordResetEmail(email_reset).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete: success");
                        Toast.makeText(ResetPasswordActivity.this, "Check email to Reset your password", Toast.LENGTH_SHORT).show();
                        email.setText("");
                    }
                    else {
                        Log.d(TAG, "onComplete: failed");
                        Toast.makeText(ResetPasswordActivity.this, "Failed  to send reset password to email", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        });


    }
}