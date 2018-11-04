package com.ration.rationstudy.Hani.chapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.ration.rationstudy.Hani.common.BaseActivity;
import com.ration.rationstudy.R;

public class Chapter01 extends BaseActivity {

    private Button btn_01, btn_02;
    private EditText edt_01;
    private Switch sw_btn_01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hani_activity_chapter01);
        findViews();
        init();
    }

    @Override
    public void findViews() {
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
        edt_01 = (EditText) findViewById(R.id.edt_01);
        sw_btn_01 = (Switch) findViewById(R.id.sw_btn_01);
    }

    @Override
    public void init() {

        btn_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chapter01.this, "클릭함", Toast.LENGTH_SHORT).show();
            }
        });
        btn_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_01.getText().toString().length() > 0) {
                    Toast.makeText(Chapter01.this, edt_01.getText().toString() + "", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Chapter01.this, "입력값을 넣어주세여", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sw_btn_01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean result) {
                if (result) {
                    Toast.makeText(Chapter01.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Chapter01.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
