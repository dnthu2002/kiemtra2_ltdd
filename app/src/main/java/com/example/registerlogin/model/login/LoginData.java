package com.example.registerlogin.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("pass")
    private String pass;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setName(String name){
        this.name = name;
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

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getPass(){
        return pass;
    }
}
