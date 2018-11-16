package com.ration.rationstudy.marty.chapters.chapter1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;

import com.jakewharton.rxbinding3.view.RxView;
import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.common.MDEBUG;
import com.ration.rationstudy.marty.common.martyBaseActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class Chapter4_marty extends martyBaseActivity {

    @BindView(R.id.gallery_img)
    ImageView galleryImg;
    @BindView(R.id.prev_btn)
    Button prevBtn;
    @BindView(R.id.next_btn)
    Button nextBtn;

    Integer[] imgs = {R.drawable.chapter04_01,R.drawable.chapter04_02,R.drawable.chapter04_03,R.drawable.chapter04_04,R.drawable.chapter04_05
    ,R.drawable.chapter04_06,R.drawable.chapter04_07};

    int currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4_marty);
        ButterKnife.bind(this);

        currentPos = imgs.length/2;
        galleryImg.setImageResource(imgs[currentPos]);
        
        Observable.merge(RxView.clicks(prevBtn).map(i->-1),RxView.clicks(nextBtn).map(i->1))
                .subscribe(isNext->onMoveImages(isNext),onError-> MDEBUG.debug("RxBasic  Error : " + onError));

    }


    void onMoveImages(int isNext){
        currentPos += isNext;
        if (currentPos >= imgs.length) currentPos=0;
        else if (currentPos < 0 ) currentPos = imgs.length-1;
        galleryImg.setImageResource(imgs[currentPos]);
    }

}
