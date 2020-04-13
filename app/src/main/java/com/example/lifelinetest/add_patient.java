package com.example.lifelinetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class add_patient extends AppCompatActivity {
    EditText Id,Name,Ward,Age,DeviceId;
    Button submit;
    RadioButton r1, r2, rb1;
    RadioGroup rg1;
    Spinner bloodgroup;
    FirebaseAuth mfirebaseAuth;
    FirebaseFirestore fstore;
    DatabaseReference databaseReference1;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Id = (EditText) findViewById(R.id.edit_id);
        Name = (EditText) findViewById(R.id.edit_name);
        Ward = (EditText) findViewById(R.id.edit_ward);
        Age = (EditText) findViewById(R.id.edit_age);
        bloodgroup = (Spinner) findViewById(R.id.edit_blood);
        DeviceId = (EditText) findViewById(R.id.edit_deviceid);
        submit = (Button) findViewById(R.id.button_submit);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Patient_Details");
        mfirebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        ArrayAdapter<String> myadapter3 = new ArrayAdapter<String>(add_patient.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nature));
        myadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodgroup.setAdapter(myadapter3);
        if (mfirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Id1 = Id.getText().toString().trim();
                final String Name1= Name.getText().toString().trim();
                final String Ward1 = Ward.getText().toString().trim();
                final String Age1 = Age.getText().toString().trim();
                final String bloodgroup1 = bloodgroup.getSelectedItem().toString().trim();
                final String gender = rb1.getText().toString().trim();
                final String deviceid1 = DeviceId.getText().toString().trim();
                if (Id1.isEmpty()) {
                    Id.setError("Please Enter Id");
                    Id.requestFocus();
                    return;
                }
                if (Name1.isEmpty()) {
                    Name.setError("Please Enter Name");
                    Name.requestFocus();
                    return;
                }
                if (Ward1.isEmpty()) {
                    Ward.setError("Please Enter Ward Number");
                    Ward.requestFocus();
                    return;
                }
                if (Age1.isEmpty()) {
                    Age.setError("Please Enter Age");
                    Age.requestFocus();
                    return;
                }
                if (deviceid1.isEmpty()) {
                    DeviceId.setError("Please Enter Age");
                    DeviceId.requestFocus();
                    return;
                }
                String id = databaseReference1.push().getKey();
                User user = new User(Id1,Name1,gender,Age1,Ward1,bloodgroup1,deviceid1);
                databaseReference1.child(id).setValue(user);
                Toast.makeText(add_patient.this, "Submit Successfully!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(add_patient.this, MainActivity.class));
            }
        });
    }
    public void rbclick2(View view) {
        // t5=(TextView)findViewById(R.id.t5);
        //editText3=(EditText)findViewById(R.id.editText3);
        int rid = rg1.getCheckedRadioButtonId();
        rb1 = (RadioButton) findViewById(rid);
        /*if(rid==R.id.r2)
        {
            t5.setVisibility(View.VISIBLE);
            editText3.setVisibility(View.VISIBLE);
        }*/
        switch (rid) {
            case R.id.r2:
                break;
            case R.id.r1:
                break;
        }
    }
}
