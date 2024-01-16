package com.example.myapplication.modeladmin.loginadmin;

import com.google.gson.annotations.SerializedName;

public class Loginadmin{

    @SerializedName("data")
    private Logindataadmin loginData;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setLoginData(Logindataadmin logindataadmin){
        this.loginData = logindataadmin;
    }

    public Logindataadmin getLoginData(){
        return loginData;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }
}
