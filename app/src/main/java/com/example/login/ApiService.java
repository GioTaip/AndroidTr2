package com.example.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("authorizationLogin")
    Call<Answer> ResquestLogin (@Body UserRequestLogin user);
    @POST("InsertUser")
    Call<AddUserData> InsertUser(@Body AddUserData user);
}
