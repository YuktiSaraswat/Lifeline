package com.example.lifelinetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    Button go;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = findViewById(R.id.logo);
        go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getInstance().getCurrentUser() == null){
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreen.this, logo, ViewCompat.getTransitionName(logo));
                    startActivity(i , options.toBundle());
                } else {
                    Intent i = new Intent(SplashScreen.this, dashboard.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreen.this, logo, ViewCompat.getTransitionName(logo));
                    startActivity(i , options.toBundle());
                }
            }
        });
    }
}