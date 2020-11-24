package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    public TextView currentDayTextView;
    public Button changeRoom_Btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDayTextView = findViewById(R.id.currentDay_textView);
        changeRoom_Btn = findViewById(R.id.changeRoom_Btn);

        changeRoom_Btn.setOnClickListener(v -> openChangeRoom());

    }


    public void openChangeRoom(){
        Intent intent = new Intent(this, changeRoom.class);
        intent.putExtra(EXTRA_TEXT, currentDayTextView.getText());
        startActivity(intent);
    }
}