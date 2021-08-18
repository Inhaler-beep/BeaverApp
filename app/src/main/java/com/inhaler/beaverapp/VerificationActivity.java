package com.inhaler.beaverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        final Button verifyButton = (Button) findViewById(R.id.verify_button);
        code = (EditText) findViewById(R.id.otp_input);
        mAuth = FirebaseAuth.getInstance();
        String otp = code.getText().toString();

        phonenumber = getIntent().getStringExtra("phonenumber");
        backendotp = getIntent().getStringExtra("backendotp");

        Toast.makeText(VerificationActivity.this, "OTP is "+backendotp, Toast.LENGTH_LONG).show();

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = code.getText().toString();
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendotp,otp);
                mAuth.signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(), "Verification COmpleted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Verification Failed"+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }
}