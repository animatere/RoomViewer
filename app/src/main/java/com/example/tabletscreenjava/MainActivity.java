package com.example.tabletscreenjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.tabletscreenjava.api.JsonPlaceholderApi;
import com.example.tabletscreenjava.objects.Post;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tabletscreenjava.changeRoom.EXTRA_TEXT_ChangeRoom;

public class MainActivity extends AppCompatActivity {

    public TextView currentDayTextView;
    public Button changeRoom_Btn;
    public TextView teacher_1;
    public TextView teacher_2;
    public TextView teacher_3;
    public TextView teacher_4;
    public TextView teacher_5;
    public TextView teacher_6;
    public TextView teacher_7;
    public TextView teacher_8;
    public TextView teacher_9;
    public TextView status_1;
    public TextView status_2;
    public TextView status_3;
    public TextView status_4;
    public TextView status_5;
    public TextView status_6;
    public TextView status_7;
    public TextView status_8;
    public TextView status_9;

//    public TextView[] teacherList = new TextView[9];
//    public TextView[] statusList = new TextView[9];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDayTextView = findViewById(R.id.currentDay_textView);
        changeRoom_Btn = findViewById(R.id.changeRoom_Btn);

        // initiate all necessary objects
        initObjects();

        // get data from API
        getDataAsPosts("https://jsonplaceholder.typicode.com/");
//        getDataAsSlots("http://172.17.0.3:8080/castlemock/mock/rest/project/UxI733/application/ffdtMI/");

        // event-handling
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now =  LocalDateTime.now();
        return dtf.format(now);
    }

    public void getDataAsPosts(String URL){

        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceholderApi = retroFit.create(JsonPlaceholderApi.class);

        Call<List<Post>> call = jsonPlaceholderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                }

                List<Post> posts = response.body();

                // All this values must be implemented in the table
                for(Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

//    public void getDataAsSlots(String URL){
//        Retrofit retroFit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonPlaceholderApi jsonPlaceholderApi = retroFit.create(JsonPlaceholderApi.class);
//
//        Call<List<Slots>> call = jsonPlaceholderApi.getSlots();
//        call.enqueue(new Callback<List<Slots>>() {
//            @Override
//            public void onResponse(Call<List<Slots>> call, Response<List<Slots>> response) {
//                if(!response.isSuccessful()){
//                    resultTextView.setText("Code: " + response.code());
//                }
//
//                List<Slots> posts = response.body();
//                for(Slots slot : posts){
//                    String content = "";
//                    content += "Slot: " + slot.getSlot() + "\n";
//                    content += "Start Time: " + slot.getStart_time() + "\n";
//                    content += "End Time: " + slot.getEnd_time() + "\n";
//                    content += "Status: " + slot.getStatus() + "\n\n";
//                    resultTextView.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Slots>> call, Throwable t) {
//                resultTextView.setText(t.getMessage());
//            }
//        });
//    }

    public void initObjects(){


//        resultTextView = findViewById(R.id.textView_result);
//        teacher_1 = findViewById(R.id.Teacher_1);
//        teacher_2 = findViewById(R.id.Teacher_2);
//        teacher_3 = findViewById(R.id.Teacher_3);
//        teacher_4 = findViewById(R.id.Teacher_4);
//        teacher_5 = findViewById(R.id.Teacher_5);
//        teacher_6 = findViewById(R.id.Teacher_6);
//        teacher_7 = findViewById(R.id.Teacher_7);
//        teacher_8 = findViewById(R.id.Teacher_8);
//        teacher_9 = findViewById(R.id.Teacher_9);
//
//        status_1 = findViewById(R.id.Status_1);
//        status_2 = findViewById(R.id.Status_2);
//        status_3 = findViewById(R.id.Status_3);
//        status_4 = findViewById(R.id.Status_4);
//        status_5 = findViewById(R.id.Status_5);
//        status_6 = findViewById(R.id.Status_6);
//        status_7 = findViewById(R.id.Status_7);
//        status_8 = findViewById(R.id.Status_8);
//        status_9 = findViewById(R.id.Status_9);

//        teacherList[0] = findViewById(R.id.Teacher_1);
//        teacherList[1] = findViewById(R.id.Teacher_2);
//        teacherList[2] = findViewById(R.id.Teacher_3);
//        teacherList[3] = findViewById(R.id.Teacher_4);
//        teacherList[4] = findViewById(R.id.Teacher_5);
//        teacherList[5] = findViewById(R.id.Teacher_6);
//        teacherList[6] = findViewById(R.id.Teacher_7);
//        teacherList[7] = findViewById(R.id.Teacher_8);
//        teacherList[8] = findViewById(R.id.Teacher_9);
//
//        statusList[0] = findViewById(R.id.Status_1);
//        statusList[1] = findViewById(R.id.Status_2);
//        statusList[2] = findViewById(R.id.Status_3);
//        statusList[3] = findViewById(R.id.Status_4);
//        statusList[4] = findViewById(R.id.Status_5);
//        statusList[5] = findViewById(R.id.Status_6);
//        statusList[6] = findViewById(R.id.Status_7);
//        statusList[7] = findViewById(R.id.Status_8);
//        statusList[8] = findViewById(R.id.Status_9);
    }
}