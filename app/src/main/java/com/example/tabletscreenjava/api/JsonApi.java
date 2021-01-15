package com.example.tabletscreenjava.api;


import com.example.tabletscreenjava.objects.Post;
import com.example.tabletscreenjava.objects.Slots;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("slots")
    Call<List<Slots>> getSlots();
}
