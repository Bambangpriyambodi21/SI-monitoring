package com.example.myapplication.modeloguru.loginguru;

import com.google.gson.annotations.SerializedName;

public class Loginguru {

    @SerializedName("data")
    private LoginDataguru loginDataguru;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setLoginData(LoginDataguru loginDataguru){
        this.loginDataguru = loginDataguru;
    }

    public LoginDataguru getLoginData(){
        return loginDataguru;
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
