package com.ration.rationstudy.marty.chapters.chapter1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;

import com.ration.rationstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Chapter4_marty extends AppCompatActivity {

    @BindView(R.id.gallery_img)
    ImageView galleryImg;
    @BindView(R.id.prev_btn)
    Button prevBtn;
    @BindView(R.id.next_btn)
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4_marty);
        ButterKnife.bind(this);


    }

}
