package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.tabletscreenjava.api.JsonApi;
import com.example.tabletscreenjava.objects.Occupancy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tabletscreenjava.changeRoom.EXTRA_TEXT_ChangeRoom;

public class MainActivity extends AppCompatActivity {

    public TextView currentDayTextView;
    public Button changeRoom_Btn;
    public TextView[] teacherList = new TextView[9];
    public TextView[] statusList = new TextView[9];
    public TextView versionName;
    // Mockserver
    public String MOCK_URL = "http://172.17.0.3:8080/castlemock/mock/rest/project/UxI733/application/AlDWMD/";
    // RealServer
    public String BASE_URL = "http://patzab.de:3080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate all necessary objects
        initObjects();

        // event-handling
        versionName.setText("Version " + BuildConfig.VERSION_NAME);
        changeRoom_Btn.setOnClickListener(v -> openChangeRoom());
        currentDayTextView.setText(getCurrentDate());
        String example = getIntent().getStringExtra(EXTRA_TEXT_ChangeRoom);
        changeRoom_Btn.setText(example);
        if(changeRoom_Btn.getText().equals("")) {
            changeRoom_Btn.setText("Room 106");
        }

        // get data from API
        getOccupancyWithSlots(MOCK_URL);
        fillEmptyColumns();

    }

    public void openChangeRoom(){
        Intent intent = new Intent(this, changeRoom.class);
        startActivity(intent);
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now =  LocalDateTime.now();
        return dtf.format(now);
    }

    public void getOccupancy(String URL){
        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retroFit.create(JsonApi.class);

        Call<List<Occupancy>> call = jsonApi.getOccupancy();
        call.enqueue(new Callback<List<Occupancy>>() {
            @Override
            public void onResponse(Call<List<Occupancy>> call, Response<List<Occupancy>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                }

                List<Occupancy> slots = response.body();


                for(Occupancy slot : slots) {
                    if (slot.getOccupancydate().equals(getCurrentDate()) & ("Room " + slot.getRoomNumber()).equals(changeRoom_Btn.getText())  ) {

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Occupancy>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void getOccupancyWithSlots(String URL){
        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retroFit.create(JsonApi.class);

        Call<List<Occupancy>> call = jsonApi.getOccupancy();
        call.enqueue(new Callback<List<Occupancy>>() {
            @Override
            public void onResponse(Call<List<Occupancy>> call, Response<List<Occupancy>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                }

                List<Occupancy> slots = response.body();


                for(Occupancy slot : slots) {
                    if (slot.getOccupancydate().equals(getCurrentDate()) & ("Room " + slot.getRoomNumber()).equals(changeRoom_Btn.getText())  ) {
                        int slotNumber = getSlotNumber(slot.getStart_time() + " - "+slot.getEnd_time());
                        if(slotNumber > 8){
                            System.out.println("Wrong Start or End time, cannot reach slot number with wrong times");
                            continue;
                        }
                        teacherList[slotNumber].setText(slot.getLast_name());
                        statusList[slotNumber].setText(slot.getStatus());
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Occupancy>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void fillEmptyColumns(){
        for(int i = 0; i < this.teacherList.length; i++){
            if(this.teacherList[i].getText().equals("")){
                teacherList[i].setText("Frei");
                statusList[i].setText("Nicht Belegt");
            }
            else{
                System.out.println("Already filled");
            }
        }
    }

    public int getSlotNumber(String time){


        switch(time){
            case "08:00 - 08:45":
                return 0;
            case "08:45 - 09:30":
                return 1;
            case "09:30 - 10:15":
                return 2;
            case "10:35 - 11:20":
                return 3;
            case "11:20 - 12:05":
                return 4;
            case "12:05 - 12:50":
                return 5;
            case "13:35 - 14:20":
                return 6;
            case "14:20 - 15:05":
                return 7;
            case "15:05 - 15:50":
                return 8;
        }
        return 99;
    }

    public void initObjects(){

        currentDayTextView = findViewById(R.id.currentDay_textView);
        changeRoom_Btn = findViewById(R.id.changeRoom_Btn);

        versionName = findViewById(R.id.appVersion_textView);
        teacherList[0] = findViewById(R.id.Teacher_1);
        teacherList[1] = findViewById(R.id.Teacher_2);
        teacherList[2] = findViewById(R.id.Teacher_3);
        teacherList[3] = findViewById(R.id.Teacher_4);
        teacherList[4] = findViewById(R.id.Teacher_5);
        teacherList[5] = findViewById(R.id.Teacher_6);
        teacherList[6] = findViewById(R.id.Teacher_7);
        teacherList[7] = findViewById(R.id.Teacher_8);
        teacherList[8] = findViewById(R.id.Teacher_9);

        statusList[0] = findViewById(R.id.Status_1);
        statusList[1] = findViewById(R.id.Status_2);
        statusList[2] = findViewById(R.id.Status_3);
        statusList[3] = findViewById(R.id.Status_4);
        statusList[4] = findViewById(R.id.Status_5);
        statusList[5] = findViewById(R.id.Status_6);
        statusList[6] = findViewById(R.id.Status_7);
        statusList[7] = findViewById(R.id.Status_8);
        statusList[8] = findViewById(R.id.Status_9);
    }
}