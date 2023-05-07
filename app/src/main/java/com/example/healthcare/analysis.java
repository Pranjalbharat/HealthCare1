package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class analysis extends AppCompatActivity {

    private Button Response_1;
    private Button Response_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        Button Yes = findViewById(R.id.my_button);
        Button No = findViewById(R.id.my_button2);

        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(analysis.this,Report.class));
                finish();
            }
        });
    }
}