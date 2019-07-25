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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewDonorActivity extends AppCompatActivity {
    private EditText donorNameET,donorPhoneET,donorLocationET,donorEmailET,donorBloodGrpET;
    private Button addDonorBTN;
    private String donorName,donorPhone,donorLocation,donorEmail,donorBloodGrp;
    private String uid;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_donor);

        setTitle("Add new Donor");
        init();

        uid = firebaseAuth.getCurrentUser().getUid();

        addDonorBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDonorInfoFromET();
                saveDonorInfo(donorName,donorPhone,donorLocation,donorEmail,donorBloodGrp);
            }
        });
    }

    private void saveDonorInfo(String donorName, String donorPhone, String donorLocation, String donorEmail, String donorBloodGrp) {
        //getDonorInfoFromET();
        DatabaseReference donorRef = databaseReference.child("Users(SaveInfoApp)").child(uid).child("DonorInfo");

        String donorId = donorRef.push().getKey();
        Donor newDonor = new Donor(donorName,donorPhone,donorLocation,donorEmail,donorBloodGrp,donorId);

        donorRef.child(donorId).setValue(newDonor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddNewDonorActivity.this, "New Donor Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNewDonorActivity.this,MainActivity.class));
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewDonorActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDonorInfoFromET() {
        if(donorNameET.getText().toString().isEmpty() || donorPhoneET.getText().toString().isEmpty() || donorLocationET.getText().toString().isEmpty() || donorEmailET.getText().toString().isEmpty() || donorBloodGrpET.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Fill up all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            donorName = donorNameET.getText().toString();
            donorPhone = donorPhoneET.getText().toString();
            donorLocation = donorLocationET.getText().toString();
            donorEmail = donorEmailET.getText().toString();
            donorBloodGrp = donorBloodGrpET.getText().toString();
        }
    }

    private void init() {
        donorNameET = findViewById(R.id.donorNameET);
        donorPhoneET = findViewById(R.id.donorPhoneET);
        donorLocationET = findViewById(R.id.donorLocationET);
        donorEmailET = findViewById(R.id.donorEmailET);
        donorBloodGrpET = findViewById(R.id.donorBloodGroupET);
        addDonorBTN = findViewById(R.id.addDonorBTN);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
