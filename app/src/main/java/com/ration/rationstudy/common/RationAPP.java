package com.ration.rationstudy.common;

import android.app.Application;

import com.ration.rationstudy.http.ServerRequest;

import retrofit2.Retrofit;


/**
 * User: Marty
 * Date: 2018-11-05
 * Time: 오전 9:38
 * Description:
 */
public class RationAPP extends Application {

    private Retrofit retrofit;
    private static RationAPP RAPP;


    public static RationAPP getRAPP() {
        return RAPP;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RAPP = this;
        retrofit = ServerRequest.getService();

    }

}
