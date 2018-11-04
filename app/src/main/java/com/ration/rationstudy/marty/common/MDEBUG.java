package com.ration.rationstudy.marty.common;

import android.util.Log;

import com.ration.rationstudy.BuildConfig;

/**
 * Created by Charny on 2018-11-04.
 */

public class MDEBUG {

    public static void debug(String msg){
        if (BuildConfig.DEBUG){
            Log.d("<RATION>",msg);
        }
    }
    public static void debug(double msg){
        if (BuildConfig.DEBUG){
            Log.d("<RATION>",msg + "");
        }
    }
    public static void debug(int msg){
        if (BuildConfig.DEBUG){
            Log.d("<RATION>",msg + "");
        }
    }

}
