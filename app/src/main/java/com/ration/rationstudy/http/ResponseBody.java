package com.ration.rationstudy.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dev.Dec on 2018-11-04
 */
public class ResponseBody<T> {

    @SerializedName("error")
    private boolean error;
    @SerializedName("msg")
    private String msg;
    @SerializedName("result")
    private ArrayList<T> result;

    public boolean isError() {
        return error;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<T> getResult() {
        return result;
    }

}
