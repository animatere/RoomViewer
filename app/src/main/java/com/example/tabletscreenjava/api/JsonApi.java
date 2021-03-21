package com.example.tabletscreenjava.api;


import com.example.tabletscreenjava.objects.Occupancy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("api/database/read/occupancies")
    Call<List<Occupancy>> getOccupancy();
}
