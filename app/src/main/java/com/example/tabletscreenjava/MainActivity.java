package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        changeRoom_Btn.setOnClickListener(v -> openChangeRoom());
        currentDayTextView.setText(getCurrentDate());
        String example = getIntent().getStringExtra(EXTRA_TEXT_ChangeRoom);
        changeRoom_Btn.setText(example);
        if(changeRoom_Btn.getText().equals("")) {
            changeRoom_Btn.setText("Room 106");
        }

        // get data from API
        getOccupancy(MOCK_URL);
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
                int counter = 0;

                for(Occupancy slot : slots) {
                    if (slot.getOccupancydate().equals(getCurrentDate())) {
                        teacherList[slot.getSlot_nr()].setText(slot.getLast_name());
                        statusList[slot.getSlot_nr()].setText(slot.getStatus());
                    } else {

                    }
                    counter++;
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

    public void initObjects(){

        currentDayTextView = findViewById(R.id.currentDay_textView);
        changeRoom_Btn = findViewById(R.id.changeRoom_Btn);

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