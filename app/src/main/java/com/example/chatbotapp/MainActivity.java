package com.example.chatbotapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbotapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText inputEditText;
    private Button sendButton;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        inputEditText = findViewById(R.id.inputEditText);
        sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> {
            String userMessage = inputEditText.getText().toString().trim();
            if (!userMessage.isEmpty()) {
                messageList.add(new Message(userMessage, true));
                String botResponse = getBotResponse(userMessage);
                messageList.add(new Message(botResponse, false));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageList.size() - 1);
                inputEditText.setText("");
            }
        });
    }

    private String getBotResponse(String userInput) {
        if (userInput.toLowerCase().contains("hello")) {
            return "Hello! How can I help you?";
        } else if (userInput.toLowerCase().contains("how are you")) {
            return "I'm doing well, thank you!";
        } else {
            return "Sorry, I didn't understand that.";
        }
    }
}
