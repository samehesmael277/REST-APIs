package com.sameh.retrofitapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sameh.retrofitapp.api.ApiInterface;
import com.sameh.retrofitapp.model.Comments;
import com.sameh.retrofitapp.model.Post;
import com.sameh.retrofitapp.R;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for store this object to the retrofit
        Post post = new Post("Sameh Esmael", "This is a line first post", 1);

        // for store this map to the retrofit
        HashMap<Object, Object> map = new HashMap<>();
        map.put("title", "Ahmed Esmael");
        map.put("body", "Hi this is a second map");
        map.put("userId", 2);

        // UI TextViews
        final TextView tv_name = findViewById(R.id.tv_name);
        final TextView tv_email = findViewById(R.id.tv_email);
        final TextView tv_body = findViewById(R.id.tv_body);
        final TextView tv_stored_post_title = findViewById(R.id.tv_stored_post_title);
        final TextView tv_stored_post_body = findViewById(R.id.tv_stored_post_body);
        final TextView tv_stored_map_title = findViewById(R.id.tv_stored_map_title);
        final TextView tv_stored_map_body = findViewById(R.id.tv_stored_map_body);

        // Access to Url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // To make retrofit fill the interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Call for get posts 1
        Call<List<Comments>> call = apiInterface.getComments("1");

        // for store post 2
        Call<Post> storePost = apiInterface.storePost(post);

        // for store map 3
        Call<Post> storeMap = apiInterface.storeMapPost(map);


        // set text from CallBack 1
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comments>> call, @NonNull Response<List<Comments>> response) {
                assert response.body() != null;
                tv_name.setText(response.body().get(0).getName());
                tv_email.setText(response.body().get(0).getEmail());
                tv_body.setText(response.body().get(0).getBody());
            }

            @Override
            public void onFailure(@NonNull Call<List<Comments>> call, @NonNull Throwable t) {
                tv_name.setText(t.getMessage());
                tv_email.setText(t.getMessage());
                tv_body.setText(t.getMessage());
            }
        });


        // set text from Stored Post 2
        storePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                assert response.body() != null;
                tv_stored_post_title.setText(response.body().getTitle());
                tv_stored_post_body.setText(response.body().getBody());
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                tv_stored_post_title.setText(t.getMessage());
                tv_stored_post_body.setText(t.getMessage());
            }
        });


        // set text from Stored Map 3
        storeMap.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                assert response.body() != null;
                tv_stored_map_title.setText(response.body().getTitle());
                tv_stored_map_body.setText(response.body().getBody());

            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                tv_stored_map_title.setText(t.getMessage());
                tv_stored_map_body.setText(t.getMessage());
            }
        });

    }
}