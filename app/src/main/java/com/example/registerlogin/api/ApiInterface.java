package com.example.registerlogin.api;

import com.example.registerlogin.model.login.Login;
import com.example.registerlogin.model.register.Register;
import com.example.registerlogin.model.update.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<Update> updateResponse(
            @Field("id") String id,
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name
    );
}