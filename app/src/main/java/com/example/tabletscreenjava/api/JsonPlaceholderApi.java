package com.example.tabletscreenjava.api;


import com.example.tabletscreenjava.objects.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
}
