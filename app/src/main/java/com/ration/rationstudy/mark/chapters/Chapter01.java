package com.ration.rationstudy.mark.chapters;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ration.rationstudy.R;
import com.ration.rationstudy.mark.common.BaseActivity;

public class Chapter01 extends BaseActivity {

    private Button btn_q1;
    private Button btn_q2;
    private Button btn_q3;
    private EditText et_num1;
    private EditText et_num2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity_chapter01);
        findViews();
        init();
    }

    @Override
    public void findViews() {
        btn_q1 = (Button) findViewById(R.id.btn_q1);
        btn_q2 = (Button) findViewById(R.id.btn_q2);
        btn_q3 = (Button) findViewById(R.id.btn_q3);
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
    }

    @Override
    public void init() {
        Calc calc = (a,b) ->  a+b;



        btn_q1.setOnClickListener(v->
        {
            int num1 = 0;
            int num2 = 0;
            if (et_num1 != null && et_num2 != null) {
                num1 = Integer.parseInt(et_num1.getText().toString());
                num2 = Integer.parseInt(et_num2.getText().toString());
            }
            int result = calc.add(num1, num2);

            Toast.makeText(Chapter01.this, "result : " + result, Toast.LENGTH_SHORT).show();
        });


        btn_q2.setOnClickListener(v->{
            new Handler().postDelayed(()->Toast.makeText(Chapter01.this, "3초 지남", Toast.LENGTH_SHORT).show(), 3000);
        });

        btn_q3.setOnClickListener(v-> Toast.makeText(Chapter01.this, "버튼 클릭했음.", Toast.LENGTH_SHORT).show());
    }

    interface Calc {
        int add(int a, int b);
    }
}
