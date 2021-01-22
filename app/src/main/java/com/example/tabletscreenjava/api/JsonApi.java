package com.example.tabletscreenjava.api;


import com.example.tabletscreenjava.objects.Occupancy;
import com.example.tabletscreenjava.objects.Post;
import com.example.tabletscreenjava.objects.Slots;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("api/database/read/occupancy")
    Call<List<Occupancy>> getOccupancy();
}
