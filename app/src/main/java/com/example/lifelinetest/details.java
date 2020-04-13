package com.example.lifelinetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifelinetest.modules.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {

    String id;
    TextView name, wardNumber;
    DatabaseReference patientRef;
    Button forward, backward;
    WebView Forward,Backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        name = findViewById(R.id.name);
        wardNumber = findViewById(R.id.ward_no);
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.backward);
        Forward = findViewById(R.id.web_forward);
        Backward = findViewById(R.id.web_backward);
        patientRef = FirebaseDatabase.getInstance().getReference().child("patients").child(id);
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient patient = dataSnapshot.getValue(Patient.class);
                updateUI(patient);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(details.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cloud.boltiot.com/remote/8048e7f6-315e-45bf-999b-27e357d67636/serialWR?data=FORWARD&deviceName=BOLT3849125";
                Forward.loadUrl(url);
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cloud.boltiot.com/remote/8048e7f6-315e-45bf-999b-27e357d67636/serialWR?data=BACKWARD&deviceName=BOLT3849125";
                Backward.loadUrl(url);
            }
        });
    }

    private void updateUI(Patient patient) {
        if (patient != null){
            name.setText(patient.name);
            wardNumber.setText(patient.wardNumber);
        } else {
            Toast.makeText(this, "List is Null", Toast.LENGTH_SHORT).show();
        }
    }
}
