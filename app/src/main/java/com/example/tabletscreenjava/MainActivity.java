package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.tabletscreenjava.changeRoom.EXTRA_TEXT_ChangeRoom;

public class MainActivity extends AppCompatActivity {

    public TextView currentDayTextView;
    public Button changeRoom_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDayTextView = findViewById(R.id.currentDay_textView);
        changeRoom_Btn = findViewById(R.id.changeRoom_Btn);
        changeRoom_Btn.setOnClickListener(v -> openChangeRoom());

        currentDayTextView.setText(getCurrentDate());
        String example = getIntent().getStringExtra(EXTRA_TEXT_ChangeRoom);
        changeRoom_Btn.setText(example);
        if(changeRoom_Btn.getText().equals("")) {
            changeRoom_Btn.setText("Room 106");
        }
    }

    public void openChangeRoom(){
        Intent intent = new Intent(this, changeRoom.class);
        startActivity(intent);
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now =  LocalDateTime.now();
        return dtf.format(now);
    }
}