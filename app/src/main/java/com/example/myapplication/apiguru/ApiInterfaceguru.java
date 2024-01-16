package com.example.myapplication.apiguru;

import com.example.myapplication.modeloguru.loginguru.Loginguru;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceguru {

    @FormUrlEncoded
    @POST("loginguru.php")
    Call<Loginguru> loginResponse(
            @Field("namag") String username,
            @Field("nipg") String password
    );

//    @FormUrlEncoded
//    @POST("loginguru.php")
//    Call<Loginguru> loginResponseguru(
//            @Field("namag") String username,
//            @Field("nipg") String password
//    );
}

