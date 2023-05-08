package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private ImageButton HomePage;
    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;



    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        HomePage=findViewById(R.id.google_login_button);
        login=findViewById(R.id.login_button);
        email=findViewById(R.id.username_field);
        password=findViewById(R.id.password_field);
        auth= FirebaseAuth.getInstance();



        HomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, HomePage.class));
                finish();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email=email.getText().toString();
                String text_password=password.getText().toString();
                if(TextUtils.isEmpty(text_email)){
                    email.setError("Empty Credentials");
                }
                if(TextUtils.isEmpty(text_password)){
                    password.setError("Empty Credentials");
                }
//                else if(!isEmail(email)){
//                    email.setError("Email address is not correct.");
//                }
                else if(text_password.length() <6){
                    password.setError("password too short");
                }else{
                    loginUser(auth,text_email,text_password);
                }
            }
        });







    }

//    boolean isEmail(EditText text) {
//        String email = text.getText().toString();
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//    }


    private void loginUser(FirebaseAuth auth,String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    // Launch welcome activity
                    Intent intent = new Intent(Login.this, Welcome.class);
                    startActivity(intent);
                    finish();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homepageIntent = new Intent(Login.this, HomePage.class);
                            startActivity(homepageIntent);
                        }
                    }, 2000);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Log-in Failed :(",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }









}