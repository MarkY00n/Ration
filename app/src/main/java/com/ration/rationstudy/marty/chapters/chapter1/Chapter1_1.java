package com.ration.rationstudy.marty.chapters.chapter1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.martyBaseActivity;

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
        btn.setOnClickListener(v -> Toast.makeText(mCon, "이부분을 람다로", Toast.LENGTH_SHORT).show());
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mCon, "이부분을 람다로", Toast.LENGTH_SHORT).show();
//            }
//        });
        // Question 2.
        btn.setOnFocusChangeListener((v, b) -> Toast.makeText(mCon, "이 부분을 람다로", Toast.LENGTH_SHORT).show());
//        btn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                Toast.makeText(mCon, "이 부분을 람다로", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Question 3.
        onTouchListener = (v, motionEvent) -> false;
        btn.setOnTouchListener(onTouchListener);
//        onTouchListener = new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(mCon, "이 부분을 람다로", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        };


    }
}




