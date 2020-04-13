package com.example.lifelinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.lang.String;
import android.app.AlertDialog;
import android.app.MediaRouteButton;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Emergency_Section extends AppCompatActivity {
    TextView a,b,c;
    Spinner State,City,Hospital;
    DatabaseReference ref;
    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__section);
        a = (TextView) findViewById(R.id.t1);
        b = (TextView) findViewById(R.id.t2);
        c = (TextView) findViewById(R.id.t3);
        State=(Spinner)findViewById(R.id.state);
        City=(Spinner)findViewById(R.id.city);
        Hospital=(Spinner)findViewById(R.id.hospital);
        ArrayAdapter<String> myadapter3=new ArrayAdapter<String>(Emergency_Section.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.state));
        myadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        State.setAdapter(myadapter3);
        ArrayAdapter<String> myadapter4=new ArrayAdapter<String>(Emergency_Section.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.city));
        myadapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        City.setAdapter(myadapter4);
        ArrayAdapter<String> myadapter5=new ArrayAdapter<String>(Emergency_Section.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.hospital));
        myadapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Hospital.setAdapter(myadapter5);
        b3=(Button)findViewById(R.id.btn);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String state = State.getSelectedItem().toString().trim();
                final String city = City.getSelectedItem().toString().trim();
                final String hospital =Hospital.getSelectedItem().toString().trim();
                ref=FirebaseDatabase.getInstance().getReference().child("Emergency").child(state).child(city);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            String number1=dataSnapshot.child(hospital).child("Beds").getValue().toString();
                            String number2=dataSnapshot.child(hospital).child("ICU").getValue().toString();
                            a.setText("Number of beds available are: "+number1);
                            b.setText("Number of beds available in ICU are: "+number2);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("abc","OnCancelled",databaseError.toException());
                    }
                });
            }
        });





    }

}
