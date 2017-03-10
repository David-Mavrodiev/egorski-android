package com.example.data;

import com.example.data.base.IData;
import com.example.data.models.Signal;
import com.example.data.models.SuccessSignalCreation;
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

public class SignalsData implements IData {
    private final String url;
    private final OkHttpClient client;

    public SignalsData(String url){
        this.url = url;
        this.client = new OkHttpClient();
    }

    public Observable<Signal> Check(final Object obj) {
        return Observable.create(new ObservableOnSubscribe<Signal>() {
            @Override
            public void subscribe(ObservableEmitter<Signal> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(obj);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                Request req = new Request.Builder().url(SignalsData.this.url + "/check").post(body).build();
                Response res = SignalsData.this.client.newCall(req).execute();

                Signal result = SignalsData.this.parseToSignal(res.body().string());
                e.onNext(result);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Signal parseToSignal(String text) {
        Gson gson = new Gson();
        return gson.fromJson(text, Signal.class);
    }

    private SuccessSignalCreation parseToSuccessSignalCreation(String text) {
        Gson gson = new Gson();
        return gson.fromJson(text, SuccessSignalCreation.class);
    }

    public Observable<SuccessSignalCreation> Create(final Signal obj) {
        return Observable.create(new ObservableOnSubscribe<SuccessSignalCreation>() {
            @Override
            public void subscribe(ObservableEmitter<SuccessSignalCreation> e) throws Exception {
                Gson gson = new Gson();
                String json = gson.toJson(obj);
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                Request req = new Request.Builder().url(SignalsData.this.url + "/create").post(body).build();
                Response res = SignalsData.this.client.newCall(req).execute();

                SuccessSignalCreation result = SignalsData.this.parseToSuccessSignalCreation(res.body().string());
                e.onNext(result);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
