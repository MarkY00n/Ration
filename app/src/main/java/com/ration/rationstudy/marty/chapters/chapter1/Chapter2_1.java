package com.ration.rationstudy.marty.chapters.chapter1;

import android.annotation.TargetApi;
import android.os.Build;

import com.ration.rationstudy.marty.MartyMainActivity;
import com.ration.rationstudy.marty.common.MDEBUG;

import java.util.ArrayList;

/**
 * Created by Charny on 2018-11-04.
 */

public class Chapter2_1 extends MartyMainActivity{






    @TargetApi(Build.VERSION_CODES.O)
    public void main(){

        ArrayList<Integer> arrayList = getdata();


        arrayList.stream().map(Integer::doubleValue).forEach(MDEBUG::debug);


    }


    ArrayList<Integer> getdata(){

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 100 ; i++)
            arrayList.add(i);

        return arrayList;
    }
}
