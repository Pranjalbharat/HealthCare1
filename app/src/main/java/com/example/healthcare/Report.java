package com.example.healthcare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Report extends AppCompatActivity {

    private TextView Disease;
    private String Disease_data;
    private TextView Symptom;
    private String Symptom_data;
    private TextView ilness;
    private static final String TAG = "MainActivity";
    private static final String SERVER_URL = "http://34.31.38.209/receive_data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        new SendDataToServerTask().execute();

        // Declarations
        Disease = findViewById(R.id.Disease);
        Symptom = findViewById(R.id.symptom_1);
        ilness = findViewById(R.id.Potentialilness_text);

        Intent intent = getIntent();
        String disease = intent.getStringExtra("report");
        String symptom_data = intent.getStringExtra("symptoms");

        Disease_data = disease;
        Symptom_data = symptom_data;

        Disease.setText(">" + disease+ "<");

        Symptom.setText(symptom_data);

        ilness.setText(disease);
    }
        // AsyncTask to send data to the server in the background
        private class SendDataToServerTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // Create a JSONObject with the data to be sent to the server
                    JSONObject data = new JSONObject();
                    data.put("get_illness", Disease_data);
                    data.put("get_symptom", Symptom_data);
                    // Create the connection to the server
                    URL url = new URL(SERVER_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    // Send the data to the server
                    conn.getOutputStream().write(data.toString().getBytes("UTF-8"));
                    // Get the response from the server
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();
                    // Log the response from the server
                    Log.d(TAG, "Response from server: " + response.toString());
                } catch (IOException e) {
                    Log.e(TAG, "Error sending data to server: " + e.getMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Error: " + e.getMessage());
                }
                return null;
            }
        }



}

