package com.example.mrdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class SignUpActivity2 extends AppCompatActivity {
    EditText sUsername,sPassword,sConfirm_Password;
    Button sButton;
    TextView sLoginhere;
    FirebaseAuth fAuth;
    private View sProgressbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        sUsername = findViewById(R.id.username);
        sPassword = findViewById(R.id.password);
        sConfirm_Password = findViewById(R.id.confirm_password);
        sButton = findViewById(R.id.signupbutton);
        sLoginhere = findViewById(R.id.loginhere);
        sProgressbar = (View) findViewById(R.id.progressbar);
        fAuth = FirebaseAuth.getInstance();



        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up for MR.Dr.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Set the login_here
        sLoginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openLoginActivity3 ();}
        });

    }
    public void openLoginActivity3 (){
        Intent intent = new Intent(this, LoginActivity3.class);
        startActivity(intent);



        //Set the registration process
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sUsername.getText().toString().trim();
                String password = sPassword.getText().toString().trim();
                String confirmpassword = sConfirm_Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    sUsername.setError("Username or Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    sPassword.setError("Password is Required");
                    return;
                }
                if (password.length() <6){
                    sUsername.setError("Password Must be greater than 6 Characters");
                    return;
                }
                if (TextUtils.isEmpty(confirmpassword)){
                    sConfirm_Password.setError("Confirm Password is Required");
                    return;
                }
              sProgressbar.setVisibility(View.VISIBLE);

                //user Registration

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity2.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(SignUpActivity2.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        });




    }

}