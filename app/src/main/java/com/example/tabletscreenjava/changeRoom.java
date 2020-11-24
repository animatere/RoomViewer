package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class changeRoom extends AppCompatActivity {


    public TextInputLayout changeTextLayout;
    public Button confirmChangeRoomBtn;
    public Button cancelChangeRoomBtn;
    public TextView ineedtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_room);

        Intent i_get = getIntent();
        String text = getIntent().getStringExtra(MainActivity.EXTRA_TEXT);

        ineedtext = findViewById(R.id.ineedtext);
        ineedtext.setText(text);

        changeTextLayout = findViewById(R.id.changeTextLayout);
        confirmChangeRoomBtn = findViewById(R.id.confirmChangeRoomBtn);
        cancelChangeRoomBtn = findViewById(R.id.cancelChangeRoomBtn);

        // ToDo: add change room function here
        confirmChangeRoomBtn.setOnClickListener(v -> {
            returnToMainActivity();
        });

        cancelChangeRoomBtn.setOnClickListener(v -> {
                returnToMainActivity();
        });

    }

    public void returnToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}