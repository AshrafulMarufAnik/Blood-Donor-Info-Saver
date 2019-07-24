package com.example.saveinfofirebasedemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Donor> donorList;
    private DonorAdapter donorAdapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private RecyclerView donorListRV;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        uid = firebaseAuth.getCurrentUser().getUid();
        configDonorListRV();
        getUserDataFromDB();
        getAllDonorsDataFromDBThroughModelClass();

    }

    private void getUserDataFromDB() {
        DatabaseReference userRef = databaseReference.child("Users(SaveInfoApp)").child(uid).child("user information");
        
    }

    private void getAllDonorsDataFromDBThroughModelClass() {
        DatabaseReference donorRef = databaseReference.child("Users(SaveInfoApp)").child(uid).child("DonorInfo");
        donorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    donorList.clear();

                    for(DataSnapshot donorData: dataSnapshot.getChildren()){
                       donorData.getValue(Donor.class);
                       donorAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void configDonorListRV() {
        donorListRV.setLayoutManager(new LinearLayoutManager(this));
        donorListRV.setAdapter(donorAdapter);
    }

    private void init() {
        donorList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        donorAdapter = new DonorAdapter(donorList,this);
        donorListRV = findViewById(R.id.donorListRV);
    }

    public void goToAddNewDonorActivity(View view) {
    }
}
