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

public class RegisterUser extends AppCompatActivity {


    EditText rname, remail, rpass;
    Button rbutton;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        rname = findViewById(R.id.username);
        remail = findViewById(R.id.useremail);
        rpass = findViewById(R.id.userpassword);
        rbutton = findViewById(R.id.register);
        fauth = FirebaseAuth.getInstance();

       rbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String email = remail.getText().toString().trim();
               String password = rpass.getText().toString().trim();

               if (TextUtils.isEmpty(email)) {
                   remail.setError("Email is required");
                   return;
               }

               if (TextUtils.isEmpty(password)) {
                   rpass.setError("Password is Required");
                   return;
               }

               if (password.length() < 6)    {
                 rpass.setError("Password must contain 6 or more Characters");
               }

               fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if (task.isSuccessful()) {
                           Toast.makeText(RegisterUser.this, "User Created", Toast.LENGTH_SHORT).show();

                       }  else {
                              Toast.makeText(RegisterUser.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });


           }
       });







    }




}