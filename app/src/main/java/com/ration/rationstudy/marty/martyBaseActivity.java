package com.ration.rationstudy.marty;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

 /**
 * User: Marty
 * Date: 2018-11-02
 * Time: 오후 2:26
 * Dscription:
 */
public class martyBaseActivity extends AppCompatActivity {

    protected Context mCon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCon = this;
    }
}
