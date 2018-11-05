package com.ration.rationstudy.http;

import com.ration.rationstudy.http.response.StudentRepo;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dev.Dec on 2018-11-04
 */
public class ServerRequest {

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public static Retrofit getService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();


        return retrofit;
    }
}
