package com.ration.rationstudy.mark.chapters;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.mark.common.BaseActivity;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Chapter04 extends BaseActivity {

    private ImageView img;
    private Button btn_pre;
    private Button btn_next;
    private List<Integer> imgList;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity_chapter04);

        init();
    }

    @Override
    public void findViews() {
        img = (ImageView) findViewById(R.id.img);
        btn_pre = (Button) findViewById(R.id.btn_pre);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    @Override
    public void init() {
        findViews();
        imgList = Arrays.asList(
                R.drawable.chapter04_01,
                R.drawable.chapter04_02,
                R.drawable.chapter04_03,
                R.drawable.chapter04_04,
                R.drawable.chapter04_05,
                R.drawable.chapter04_06,
                R.drawable.chapter04_07
        );

        img.setImageResource(imgList.get(0));

        getObservable().subscribe(observer());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(observer -> {
           btn_pre.setOnClickListener(v -> {
               pos--;
               observer.onNext(getImage());
           });
           btn_next.setOnClickListener(v -> {
               pos++;
               observer.onNext(getImage());
           });
        });
    }

    private Integer getImage() {
        if (pos < 0) {
            pos = 6;
        } else if (pos >= imgList.size()) {
            pos = 0;
        }
        return imgList.get(pos);
    }

    private Observer<? super Integer> observer () {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                img.setImageResource(integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
