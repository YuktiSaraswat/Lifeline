package com.example.lifelinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button view_patient,add_patient,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(dashboard.this, MainActivity.class));
            }
        });
        add_patient = findViewById(R.id.add_patient);
        add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, add_patient.class));
            }
        });
        view_patient = findViewById(R.id.view_patients);
        view_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, view_patient.class));
            }
        });
    }
    public void emergency(View view)
    {
        Intent intentd = new Intent(dashboard.this, Emergency_Section.class);
        startActivity(intentd);
    }
}
