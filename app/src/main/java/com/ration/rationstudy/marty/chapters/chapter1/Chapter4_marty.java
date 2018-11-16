package com.ration.rationstudy.marty.chapters.chapter1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;

import com.jakewharton.rxbinding3.view.RxView;
import com.ration.rationstudy.R;
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

    int[] imgs;

    int currentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4_marty);
        ButterKnife.bind(this);

        imgs = getResources().getIntArray(R.array.chapter4_imgs);
        currentPos = imgs.length/2;
        galleryImg.setImageResource(imgs[currentPos]);


        Observable<Integer> prev = RxView.clicks(prevBtn).map(isNext->-1);
        Observable<Integer> next = RxView.clicks(prevBtn).map(isNext->1);

        Observable.merge(prev,next)
                .subscribe(isNext->onMoveImages(isNext));
    }


    void onMoveImages(int isNext){
        if (currentPos >= imgs.length) currentPos=0;
        else if (currentPos < 0 ) currentPos = imgs.length-1;
        currentPos += isNext;
        galleryImg.setImageResource(imgs[currentPos]);
    }

}
