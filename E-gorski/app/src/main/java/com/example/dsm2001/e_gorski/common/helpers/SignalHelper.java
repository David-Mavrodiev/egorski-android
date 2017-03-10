package com.example.dsm2001.e_gorski.common.helpers;



import com.example.data.SignalsData;
import com.example.data.models.CheckSignal;
import com.example.data.models.Signal;
import com.example.data.models.SuccessSignalCreation;
import com.example.dsm2001.e_gorski.common.utils.ConfigApplicationData;

import io.reactivex.Observable;

public class SignalHelper {
    private static SignalsData signalsData = new SignalsData(ConfigApplicationData.getSignalsRequestUrl());

    public SignalHelper(){

    }

    public static Observable<SuccessSignalCreation> create(Signal signal){
        return signalsData.Create(signal);
    }

    public static Observable<Signal> check(CheckSignal checkSignal){
        return  signalsData.Check(checkSignal);
    }
}
