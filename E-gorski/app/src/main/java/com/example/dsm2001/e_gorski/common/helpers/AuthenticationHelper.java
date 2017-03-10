package com.example.dsm2001.e_gorski.common.helpers;


import com.example.data.UsersData;
import com.example.data.models.AuthenticationCookie;
import com.example.data.models.User;
import com.example.dsm2001.e_gorski.common.utils.ConfigApplicationData;
import com.orm.SugarRecord;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class AuthenticationHelper {
    private static UsersData usersData = new UsersData(ConfigApplicationData.getUsersRequestUrl());

    public AuthenticationHelper(){

    }

    public static void saveCookie(AuthenticationCookie cookie){
        SugarRecord.save(cookie);
    }

    public static AuthenticationCookie getCookie(){
        List<AuthenticationCookie> loginResults = SugarRecord.listAll(AuthenticationCookie.class);
        if (loginResults.size() == 0) {
            return null;
        } else {
            return loginResults.get(0);
        }
    }

    public static Observable<AuthenticationCookie> login(User user){
        return usersData.Login(user);
    }

    public static Observable<AuthenticationCookie> register(User user){
        return usersData.Register(user);
    }

    public static void logout(){
        SugarRecord.deleteAll(AuthenticationCookie.class);
    }
}
