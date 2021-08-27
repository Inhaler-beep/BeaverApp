package com.inhaler.beaverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.*;

public class RegisterActivity extends AppCompatActivity {

    private Button sendOtp;
    private EditText phoneNumber;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sendOtp = (Button) findViewById(R.id.send_otp);
        phoneNumber = (EditText) findViewById(R.id.phone_input);
        progressBar = (ProgressBar) findViewById(R.id.register_progressbar);

        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = Validate();
                if(flag)
                {
                    sendOtp.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+91"+phoneNumber.getText().toString())       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(RegisterActivity.this)                 // Activity (for callback binding)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            Toast.makeText(getApplicationContext(), "Otp Success", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Toast.makeText(getApplicationContext(), "Otp Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            sendOtp.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(verificationId, forceResendingToken);


                                            progressBar.setVisibility(View.GONE);
                                            Intent verifyIntent = new Intent(getApplicationContext(),VerificationActivity.class);
                                            verifyIntent.putExtra("phonenumber",phoneNumber.getText().toString());
                                            verifyIntent.putExtra("backendotp",verificationId);
                                            startActivity(verifyIntent);
                                        }
                                    })          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent homeIntent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }

    private Boolean Validate() {

        boolean flag = true;
        String phonenumber = phoneNumber.getText().toString();
        if(phonenumber.length() != 10)
        {
            if(phonenumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"))
            {
                flag = false;
                Toast.makeText(RegisterActivity.this, "Please Enter in Correct Format", Toast.LENGTH_SHORT).show();
            }
            else
            {
                flag = false;
                Toast.makeText(RegisterActivity.this, "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            }

        }




        return flag;
    }
}