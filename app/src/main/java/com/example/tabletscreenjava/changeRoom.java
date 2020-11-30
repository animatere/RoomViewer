package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.tabletscreenjava.MainActivity.EXTRA_TEXT;

public class changeRoom extends AppCompatActivity {

    public static final String EXTRA_TEXT_ChangeRoom = "com.example.application.example.EXTRA_TEXT_ChangeRoom";
    public TextInputLayout changeTextLayout;
    public Button confirmChangeRoomBtn;
    public Button cancelChangeRoomBtn;
    public TextView ineedtext;
    public TextInputEditText newRoomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room);

        Intent i_get = getIntent();
        String text = getIntent().getStringExtra(EXTRA_TEXT);

        ineedtext = findViewById(R.id.ineedtext);
        ineedtext.setText(text);

        changeTextLayout = findViewById(R.id.changeTextLayout);
        confirmChangeRoomBtn = findViewById(R.id.confirmChangeRoomBtn);
        cancelChangeRoomBtn = findViewById(R.id.cancelChangeRoomBtn);
        newRoomName = findViewById(R.id.newRoomName);


        // ToDo: add change room function here
        confirmChangeRoomBtn.setOnClickListener(v -> {
            changeRoom();
        });

        cancelChangeRoomBtn.setOnClickListener(v -> {
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