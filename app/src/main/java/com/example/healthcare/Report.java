package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    private TextView Disease;
    private TextView Symptom;
    private TextView ilness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Declarations
        Disease = findViewById(R.id.Disease);
        Symptom = findViewById(R.id.symptom_1);
        ilness = findViewById(R.id.Potentialilness_text);

        Intent intent = getIntent();
        String disease = intent.getStringExtra("report");
        String symptom_data = intent.getStringExtra("symptoms");

        Disease.setText(">" + disease+ "<");

        Symptom.setText(symptom_data);

        ilness.setText(disease);
    }
}