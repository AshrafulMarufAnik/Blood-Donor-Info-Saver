package com.example.saveinfofirebasedemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private EditText emailET,passwordET;
    private Button logInBTN;
    private String email,password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");
        init();
        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(LogInActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        logInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()){
                    Toast.makeText(LogInActivity.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    email = emailET.getText().toString();
                    password = passwordET.getText().toString();

                    logInUser(email,password);
                }
            }
        });
    }

    private void logInUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogInActivity.this,MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LogInActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        logInBTN = findViewById(R.id.logInBTN);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void goToRegisterActivity(View view) {
        startActivity(new Intent(LogInActivity.this,RegistrationActivity.class));
    }
}
