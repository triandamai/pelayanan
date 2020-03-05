package com.core;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    String accept_urlencoded = "Accept: application/x-www-form-urlencoded";
    String accept_json = "Accept: application/json";
    String api_key = "X-API-KEY: your api key";

    @Headers({accept_urlencoded, api_key})
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseLogin> prosesLogin(
            @Field("username") @NonNull String username,
            @Field("password") @NonNull String password);
}
