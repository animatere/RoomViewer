package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


public class changeRoom extends AppCompatActivity {

    public static final String EXTRA_TEXT_ChangeRoom = "com.example.application.example.EXTRA_TEXT_ChangeRoom";
    public Button confirmBtn;
    public Button cancelBtn;
    public TextInputEditText newRoomName;
    public TextView versionName_changeRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room);

        confirmBtn = findViewById(R.id.confirmBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        newRoomName = findViewById(R.id.newRoomName);
        versionName_changeRoom = findViewById(R.id.appVersion_textView2);


        versionName_changeRoom.setText("Version " + BuildConfig.VERSION_NAME);
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