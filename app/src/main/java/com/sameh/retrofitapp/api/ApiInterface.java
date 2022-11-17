package com.sameh.retrofitapp.api;

import com.sameh.retrofitapp.model.Comments;
import com.sameh.retrofitapp.model.Post;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("comments")
    public Call<List<Comments>> getComments(@Query("postId") String postId);

    /*
    // if i want to pass a postId as parameter
    @GET("posts/{id}")
    public Call<Post> getPosts(@Path("id") int postId);
     */

    // pass object to upload it
    @POST("posts")
    public Call<Post> storePost(@Body Post post);

    // pass map to upload it
    @POST("posts")
    public Call<Post> storeMapPost(@Body HashMap<Object, Object> map);

}
