package com.example.data;

import com.example.data.base.IData;
import com.example.data.models.AuthenticationCookie;
import com.example.data.models.User;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UsersData implements IData {
    private final String url;
    private final OkHttpClient client;

    public UsersData(String url){
        this.url = url;
        this.client = new OkHttpClient();
    }

    public Observable<AuthenticationCookie> Login(Object obj) {
        final String url = this.url + "/authenticate";
        final Object sendObj = obj;
        return Observable.create(new ObservableOnSubscribe<AuthenticationCookie>() {
            @Override
            public void subscribe(ObservableEmitter<AuthenticationCookie> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(sendObj);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                Request req = new Request.Builder().url(url).post(body).build();
                Response res = UsersData.this.client.newCall(req).execute();

                AuthenticationCookie result = UsersData.this.parseToCookie(res.body().string());
                e.onNext(result);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private AuthenticationCookie parseToCookie(String text) {
        Gson gson = new Gson();
        return gson.fromJson(text, AuthenticationCookie.class);
    }

    public Observable<AuthenticationCookie> Register(final User obj) {
        return Observable.create(new ObservableOnSubscribe<AuthenticationCookie>() {
            @Override
            public void subscribe(ObservableEmitter<AuthenticationCookie> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(obj);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                Request req = new Request.Builder().url(UsersData.this.url + "/register").post(body).build();
                Response res = UsersData.this.client.newCall(req).execute();

                AuthenticationCookie result = UsersData.this.parseToCookie(res.body().string());
                e.onNext(result);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
