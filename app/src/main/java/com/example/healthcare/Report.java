package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    private TextView Disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Declarations
        Disease = findViewById(R.id.Disease);

        Intent intent = getIntent();
        String disease = intent.getStringExtra("report");

        Disease.setText(">" + disease+ "<");
    }
}