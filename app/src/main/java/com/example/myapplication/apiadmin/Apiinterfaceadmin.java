package com.example.myapplication.apiadmin;

import com.example.myapplication.model.login.Login;
import com.example.myapplication.modeladmin.loginadmin.Loginadmin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiinterfaceadmin {

    @FormUrlEncoded
    @POST("loginadmin.php")
    Call<Loginadmin> loginResponse(
            @Field("namaa") String username,
            @Field("usera") String password
    );

}
