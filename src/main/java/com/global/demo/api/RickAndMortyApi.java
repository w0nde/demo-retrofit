package com.global.demo.api;

import com.global.demo.github.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

public interface RickAndMortyApi {
    @Headers({"Accept: application/json", "Content-type: application/json"})
    @GET("character")
    Call<Map<String,Object>> allCharacters(@Query("page") int page);

    @Headers({"Accept: application/json", "Content-type: application/json"})
    @GET("character/{id}")
    Call<Map<String,Object>> singleCharacter(@Path(value = "id") int id);
}
