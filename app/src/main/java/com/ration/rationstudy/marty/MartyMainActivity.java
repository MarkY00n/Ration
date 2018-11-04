package com.ration.rationstudy.marty;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.Data.Subject;
import com.ration.rationstudy.marty.common.MartyAdapter;
import com.ration.rationstudy.marty.common.MartyVH;

import java.util.ArrayList;

/**
 * User: Marty
 * Date: 2018-11-02
 * Time: 오후 2:26
 * Dscription:
 */

public class MartyMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marty_main);
        recyclerView = findViewById(R.id.main_list);
        adapter = new SimpleAdapter(this,R.layout.marty_listitem);

        adapter.setList(getChapter());
        recyclerView.setAdapter(adapter);

    }




    private ArrayList<Subject> getChapter(){

        ArrayList<Subject> array = new ArrayList<>();
        Subject[] subjects = {new Subject("lambda_1",Subject.CHAPTER_1_1),new Subject("StreamBasic",Subject.CHAPTER_2_1)};

        for (Subject sub : subjects){
            array.add(sub);
        }


        return array;
    }
}


class SimpleVH extends MartyVH<Subject> {
    TextView tv;
    public SimpleVH(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    @Override
    public void bind(Subject subject,Context mCon) {
        tv.setText(subject.getTitle());
        tv.setTag(subject.getOid());

        tv.setOnClickListener((v)->{
            Intent inte = new Intent(mCon,Subject.getActivity((int)tv.getTag()));
            mCon.startActivity(inte);
        });
    }
}

class SimpleAdapter extends MartyAdapter<Subject,SimpleVH> {
    public SimpleAdapter(Context mCon, Integer mLayout) {
        super(mCon, mLayout);
    }


    @Override
    public SimpleVH getViewHolder(View view) {
        return new SimpleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleVH simpleVH, int i) {
        simpleVH.bind(arrayList.get(i),getmCon());
    }
}

