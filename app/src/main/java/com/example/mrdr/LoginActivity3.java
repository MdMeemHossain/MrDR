package com.example.mrdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity3 extends AppCompatActivity {
    EditText lUsername,lPassword;
    Button lButton;
    TextView lsignuphere;

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lUsername = findViewById(R.id.username2);
        lPassword = findViewById(R.id.password2);
        lButton = findViewById(R.id.loginbutton);
        lsignuphere = findViewById(R.id.signuphere);


        fAuth = FirebaseAuth.getInstance();


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login for MR.Dr.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //Set the login process
        lButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lUsername.getText().toString().trim();
                String password = lPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    lUsername.setError("Username or Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    lPassword.setError("Password is Required");
                    return;
                }
                if (password.length() <6){
                    lPassword.setError("Password Must be greater than 6 Characters");
                    return;
                }


                //Authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity3.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomepageActivity4.class));
                        } else {

                            Toast.makeText(LoginActivity3.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            }

        });
        //Set the sign_up_here
        lsignuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSignUpActivity2();}
        });
        //close keyboard
        closekeyboard(lButton);
    }
    public void closekeyboard(Button lButton) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    //Set the sign_up_here
    public void openSignUpActivity2 (){
        Intent intent = new Intent(this, SignUpActivity2.class);
        startActivity(intent);
    }

    public void signuphere(View view) {
    }

    public void login(View view) {
    }
}