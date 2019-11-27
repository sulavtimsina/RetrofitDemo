package com.sulav.retrofitdemosulav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sulav.retrofitdemosulav.model.Post;
import com.sulav.retrofitdemosulav.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends BaseActivity {

    Post post;
    private static final String TAG = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getPost();
    }

    private void getPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<Post> repos = service.getPostById(1);
        repos.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(PostActivity.this, "here", Toast.LENGTH_SHORT).show();
                post = response.body();
                ((TextView)findViewById(R.id.tvPost)).setText(post.title);
                Log.d(TAG, "onResponse: body="+post.body);
                Log.d(TAG, "onResponse: title="+post.title);
                Log.d(TAG, "onResponse: id="+post.id);
                Log.d(TAG, "onResponse: userid="+post.userId);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
