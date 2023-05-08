package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Chat_page extends AppCompatActivity {

    private ListView mChatListView;
    private EditText mChatInputEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        mChatListView = findViewById(R.id.chat_listview);
        mChatInputEditText = findViewById(R.id.chat_input_text);
        mSendButton = findViewById(R.id.send_button);

        // Set up adapter for chat messages list view
        List<String> chatMessages = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.chat_message, R.id.messageTextView, chatMessages);
        mChatListView.setAdapter(adapter);

        // Add send button click listener
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mChatInputEditText.getText().toString();
                chatMessages.add(message);
                adapter.notifyDataSetChanged();
                mChatInputEditText.getText().clear();
            }
        });

    }
}