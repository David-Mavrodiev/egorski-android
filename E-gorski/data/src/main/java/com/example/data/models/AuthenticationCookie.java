package com.example.data.models;

import com.orm.SugarRecord;

import java.io.Serializable;

public class AuthenticationCookie extends SugarRecord implements Serializable{
    private String username;
    private String cookieText;
    private String error;

    public AuthenticationCookie(){

    }

    public AuthenticationCookie(String error, String username, String cookieText){
        this.error = error;
        this.username = username;
        this.cookieText = cookieText;
    }

    public AuthenticationCookie(String username, String cookieText){
        this.username = username;
        this.cookieText = cookieText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCookieText() {
        return cookieText;
    }

    public void setCookieText(String cookieText) {
        this.cookieText = cookieText;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
