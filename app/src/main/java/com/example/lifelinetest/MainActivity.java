package com.example.lifelinetest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelinetest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText Phone,Code;
    TextView Login;
    Button btn_register,sendCode;
    FirebaseAuth mfirebaseAuth;
    FirebaseFirestore fstore;
    String userId,codeSent;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Phone=(EditText)findViewById(R.id.edit_phone);
        Code=(EditText)findViewById(R.id.edit_code);
        btn_register=(Button) findViewById(R.id.button_register);
        sendCode=(Button) findViewById(R.id.btn_code);
        Login=(TextView) findViewById(R.id.register1);
        progressBar=(ProgressBar)findViewById(R.id.progress_circular1);
        mfirebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        final FirebaseUser user1=mfirebaseAuth.getCurrentUser();
        progressBar.setVisibility(View.INVISIBLE);
        if (mfirebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                progressBar.setVisibility(View.VISIBLE);
                                                verifySignInCode();

                                            }
                                        }
        );
        private void verifySignInCode()
        {
            String code = Code.getText().toString().trim();
            if (code.isEmpty()) {
                Code.setError("Please Enter Phone No.");
                Code.requestFocus();
                return;
            }
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
            signInWithPhoneAuthCredential(credential);
        }
        private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                            } else {
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    // The verification code entered was invalid
                                    Toast.makeText(MainActivity.this, "Verification Code is Invalid!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }
                    }
        });
    }

    private void sendVerificationCode()
    {
        String phone = Phone.getText().toString().trim();
        if (phone.length() < 10) {
            Phone.setError("Please Enter Valid Phone Number");
            Phone.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            Phone.setError("Please Enter Phone No.");
            Phone.requestFocus();
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }
        @Override
        public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s,ForceResendingToken);
            codeSent=s;

        }
    };


}
}
