package com.example.tabletscreenjava.api;

import com.example.tabletscreenjava.objects.Players;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FirebaseApi {

    @GET("table")
    Call<List<Players>> getPosts();
}
