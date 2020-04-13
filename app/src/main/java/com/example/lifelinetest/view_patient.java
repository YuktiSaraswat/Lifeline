package com.example.lifelinetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lifelinetest.modules.MyAdapter;
import com.example.lifelinetest.modules.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class view_patient extends AppCompatActivity implements MyAdapter.OnItemClicked {

    public static int position;
    ArrayList<Patient> list;
    RecyclerView patientsList;
    DatabaseReference reference;
    MyAdapter adapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        patientsList = findViewById(R.id.patientsList);
        progressBar = findViewById(R.id.progressBar);
        patientsList.setLayoutManager(new LinearLayoutManager(this));
        patientsList.setHasFixedSize(true);
        list = new ArrayList<Patient>();
        reference = FirebaseDatabase.getInstance().getReference().child("patients");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Patient p = dataSnapshot1.getValue(Patient.class);
                    list.add(p);
                }
                adapter = new MyAdapter(view_patient.this, list);
                progressBar.setVisibility(View.INVISIBLE);
                patientsList.setAdapter(adapter);
                adapter.setOnClick(view_patient.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view_patient.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemClick(String id){
        Intent i = new Intent(view_patient.this, details.class);
        i.putExtra("id",id);
        startActivity(i);
    }
}
