package com.ration.rationstudy.marty.chapters.chapter1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.martyBaseActivity;

import java.util.ArrayList;

/**
 * User: Marty
 * Date: 2018-11-02
 * Time: 오후 2:26
 * Dscription:
 */

public class Chapter1_1 extends martyBaseActivity {

    Button btn;

    View.OnTouchListener onTouchListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chater1_1);
        btn = findViewById(R.id.btn);

        // Question 1.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCon, "이부분을 람다로", Toast.LENGTH_SHORT).show();
            }
        });

        // Question 2.
        btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(mCon, "이 부분을 람다로", Toast.LENGTH_SHORT).show();
            }
        });

        // Question 3.

        onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // 이부분을 람다로
                return false;
            }
        };
        btn.setOnTouchListener(onTouchListener);

    }
}




