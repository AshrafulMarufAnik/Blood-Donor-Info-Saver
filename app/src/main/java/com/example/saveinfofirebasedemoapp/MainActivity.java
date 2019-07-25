package com.example.saveinfofirebasedemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Donor> donorList;
    private User currentUser;
    private DonorAdapter donorAdapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private RecyclerView donorListRV;
    private FloatingActionButton addDonorFABTN;

    private String uid;
    private TextView userNameTV,userEmailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Home");
        init();
        uid = firebaseAuth.getCurrentUser().getUid();
        getUserDataFromDB();
        getAllDonorsDataFromDBThroughModelClass();

        addDonorFABTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddNewDonorActivity.class));
            }
        });

    }

    private void getUserDataFromDB() {
        DatabaseReference userRef = databaseReference.child("Users(SaveInfoApp)").child(uid).child("user information");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    currentUser = dataSnapshot.getValue(User.class);
                    userNameTV.setText(currentUser.getUserName());
                    userEmailTV.setText(currentUser.getUserEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getAllDonorsDataFromDBThroughModelClass() {
        DatabaseReference donorRef = databaseReference.child("Users(SaveInfoApp)").child(uid).child("DonorInfo");
        donorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    donorList.clear();

                    for(DataSnapshot donorData: dataSnapshot.getChildren()){
                       Donor newDonor = donorData.getValue(Donor.class);
                       donorList.add(newDonor);
                       donorAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        addDonorFABTN = findViewById(R.id.addNewDonorFABTN);
        userNameTV = findViewById(R.id.userNameTV);
        userEmailTV = findViewById(R.id.userEmailTV);
        donorList = new ArrayList<>();
        currentUser = new User();

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        donorAdapter = new DonorAdapter(donorList,this);
        donorListRV = findViewById(R.id.donorListRV);
        donorListRV.setLayoutManager(new LinearLayoutManager(this));
        donorListRV.setAdapter(donorAdapter);

    }

}
