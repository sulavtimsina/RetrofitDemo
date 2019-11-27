package com.sulav.retrofitdemosulav;

import com.sulav.retrofitdemosulav.model.Post;
import com.sulav.retrofitdemosulav.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id")int id);
}
