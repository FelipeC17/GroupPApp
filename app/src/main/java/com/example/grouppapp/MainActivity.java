package com.example.grouppapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText passL, emaiL;
    Button bttnL;
    FirebaseAuth Fiauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        emaiL = findViewById(R.id.EmailL);
        passL = findViewById(R.id.PasswordL);
        bttnL = findViewById(R.id.buttonL);
        Fiauth = FirebaseAuth.getInstance();

        bttnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailL = emaiL.getText().toString().trim();
                String passwordL = passL.getText().toString().trim();


                if (TextUtils.isEmpty(emailL)) {
                    emaiL.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(passwordL)) {
                    passL.setError("Password is Required");
                    return;
                }

                if (passwordL.length() < 6) {
                    passL.setError("Password must contain 6 or more characters");
                    return;
                }


                Fiauth.signInWithEmailAndPassword(emailL,passwordL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });





    }
}