package com.example.myapplication.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData  {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("nis")
    private String name;

    @SerializedName("namas")
    private String username;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setName(String nis){
        this.name = nis;
    }

    public String getName(){
        return name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
