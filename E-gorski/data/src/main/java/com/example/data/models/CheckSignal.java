package com.example.data.models;

public class CheckSignal {
    private String registeredNumber;
    private String cookieText;

    public CheckSignal(){

    }

    public CheckSignal(String registeredNumber, String cookieText){
        this.registeredNumber = registeredNumber;
        this.cookieText = cookieText;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getCookieText() {
        return cookieText;
    }

    public void setCookieText(String cookieText) {
        this.cookieText = cookieText;
    }
}
