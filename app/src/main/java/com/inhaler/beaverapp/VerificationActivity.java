package com.inhaler.beaverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificationActivity extends AppCompatActivity {
    String backendotp,phonenumber;
    private FirebaseAuth mAuth;
    private EditText code;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        final Button verifyButton = (Button) findViewById(R.id.verify_button);
        code = (EditText) findViewById(R.id.otp_input);
        progressBar = (ProgressBar) findViewById(R.id.verify_progressbar);
        mAuth = FirebaseAuth.getInstance();
        String otp = code.getText().toString();

        phonenumber = getIntent().getStringExtra("phonenumber");
        backendotp = getIntent().getStringExtra("backendotp");

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                String otp = code.getText().toString();
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendotp,otp);
                mAuth.signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                    verifyButton.setVisibility(View.GONE);
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "OTP Verification Completed :", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(VerificationActivity.this,MainActivity.class);
                                    startActivity(homeIntent);
                                }
                                else
                                {
                                    verifyButton.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Verification Failed"+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    int length = code.length();
                    if(length == 6)
                    {
                        verifyButton.callOnClick();
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}