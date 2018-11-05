package com.ration.rationstudy.http;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.common.MDEBUG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * User: Marty
 * Date: 2018-11-05
 * Time: 오전 9:25
 * Description:
 */
public class RationCallback<T> implements Callback<T> {

    private final int NO_VALUE = -1;
    public interface RationCall<T> {
        void onResponse(Call<T> call, Response<T> response);
    }

    public interface RationCallWithFailure<T> {
        void onResponse(Call<T> call, Response<T> response);
        void onFailure(@NonNull Call<T> call, @NonNull Throwable t);
    }

    private RationCall<T> rationCall;
    private RationCallWithFailure<T> rationCallWithFailure;
    private AppCompatActivity baseView;
    private int failAlertText;

    public RationCallback(AppCompatActivity baseView, RationCall<T> rationCall) {
        this.rationCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public RationCallback(AppCompatActivity baseView, RationCall<T> rationCall, int failAlertText) {
        this.rationCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }

    public RationCallback(AppCompatActivity baseView, RationCallWithFailure<T> rationCall) {
        this.rationCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public RationCallback(AppCompatActivity baseView, RationCallWithFailure<T> rationCall, int failAlertText) {
        this.rationCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (rationCall != null)
                rationCall.onResponse(call, response);
            else
                rationCallWithFailure.onResponse(call, response);
        }else{
            onFailure(call,new Throwable());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (rationCallWithFailure != null)
            rationCallWithFailure.onFailure(call, t);
        Snackbar.make(baseView.findViewById(android.R.id.content),failAlertText == NO_VALUE ? baseView.getResources().getString(R.string.retry): baseView.getResources().getString(failAlertText),Snackbar.LENGTH_SHORT).show();
    }
}
