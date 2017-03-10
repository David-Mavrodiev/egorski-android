package com.example.dsm2001.e_gorski.common.utils;

public class ConfigApplicationData {
    private static String url = "https://egorski-webapi.herokuapp.com";

    public ConfigApplicationData(){

    }

    public static String getUsersRequestUrl(){
        return url + "/users";
    }

    public static String getUsersLoginRequestUrl(){
        return "/authenticate";
    }

    public static String getUsersRegisterRequestUrl(){
        return "/register";
    }

    public static String getSignalsCreateRequestUrl(){
        return "/create";
    }

    public static String getSignalsCheckRequestUrl(){
        return "/check";
    }

    public static String getSignalsRequestUrl() {return url + "/signals";}
}
