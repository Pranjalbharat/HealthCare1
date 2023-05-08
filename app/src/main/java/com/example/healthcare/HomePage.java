package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {
    private ImageButton Doctor_profile;
    private ImageButton Chat_message;
    private Button Test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Doctor_profile=findViewById(R.id.imageview);
        Chat_message=findViewById(R.id.chat_button);
        Test = findViewById(R.id.center_button);


        Doctor_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, Doctor_profile.class));
            }
        });


        Chat_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, Chat_page.class));
            }
        });

        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,Testpage.class));
            }
        });
    }
}