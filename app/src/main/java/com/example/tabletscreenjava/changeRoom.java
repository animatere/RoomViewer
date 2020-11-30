package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class changeRoom extends AppCompatActivity {

    public static final String EXTRA_TEXT_ChangeRoom = "com.example.application.example.EXTRA_TEXT_ChangeRoom";
    public Button confirmBtn;
    public Button cancelBtn;
    public TextInputEditText newRoomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room);

        confirmBtn = findViewById(R.id.confirmBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        newRoomName = findViewById(R.id.newRoomName);


        confirmBtn.setOnClickListener(v -> {
            changeRoom();
        });

        cancelBtn.setOnClickListener(v -> {
                returnToMainActivity();
        });

    }

    public void changeRoom(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_TEXT_ChangeRoom, newRoomName.getEditableText().toString());
        startActivity(intent);
    }

    public void returnToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}