package com.global.demo.api;

import com.global.demo.github.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GithubApi {

    @Headers({"Accept: application/json", "Content-type: application/json"})
    @GET("users")
    Call<List<User>> users();

}
