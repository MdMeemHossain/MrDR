package com.example.mrdr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private View signup;
    private View login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MR.Dr.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        signup = (View) findViewById(R.id.signup);
        login = (View) findViewById(R.id.login);

        //login Icon setup
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity3();
            }
        });

        //Signup Icon setup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity2();
            }
        });

    }
    public void openSignUpActivity2 () {
        Intent intent = new Intent(this, SignUpActivity2.class);
        startActivity(intent);
    }
    public void openLoginActivity3 () {
        Intent intent = new Intent(this, LoginActivity3.class);
        startActivity(intent);
    }

    }

