package com.ration.rationstudy.Hani.chapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.jakewharton.rxbinding3.view.RxView;
import com.ration.rationstudy.R;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;

public class Chapter04 extends AppCompatActivity {
    ImageView img;
    int img_list[] = {R.drawable.chapter04_01, R.drawable.chapter04_02, R.drawable.chapter04_03, R.drawable.chapter04_04, R.drawable.chapter04_05, R.drawable.chapter04_06,
            R.drawable.chapter04_07};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hani_activity_chapter04);
        img = (ImageView) findViewById(R.id.img);

        RxView.clicks(findViewById(R.id.pre_btn))
                .subscribe(aVoid -> {
                    try {
                        i--;
                        if (i < 0) {
                            i = img_list.length - 1;
                        }
                        img.setImageResource(img_list[i]);
                    } catch (Exception e) {
                        Log.e("error = ", e.toString());
                    }

                });

        RxView.clicks(findViewById(R.id.next_btn))
                .subscribe(aVoid -> {
                    try {
                        i++;
                        if (i > img_list.length - 1) {
                            i = 0;
                        }
                        img.setImageResource(img_list[i]);
                    } catch (Exception e) {
                        Log.e("error = ", e.toString());
                    }
                });
    }
}
