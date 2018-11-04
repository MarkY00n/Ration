package com.ration.rationstudy.marty.chapters.chapter1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.marty.common.MDEBUG;
import com.ration.rationstudy.marty.common.MartyAdapter;
import com.ration.rationstudy.marty.common.MartyVH;
import com.ration.rationstudy.marty.common.martyBaseActivity;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Charny on 2018-11-04.
 */

public class Chapter2_1 extends martyBaseActivity {

    StreamListAdapter adapter;

    @BindView(R.id.main_list)
    RecyclerView mainList;
    @BindView(R.id.single_search_edt)
    EditText singleSearchEdt;
    @BindView(R.id.single_search_btn)
    Button singleSearchBtn;
    @BindView(R.id.range_search_edt1)
    EditText rangeSearchEdt1;
    @BindView(R.id.range_search_edt2)
    EditText rangeSearchEdt2;
    @BindView(R.id.range_search_btn)
    Button rangeSearchBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marty_chap2);
        ButterKnife.bind(this);
        adapter = new StreamListAdapter(this, R.layout.marty_listitem);
        mainList.setAdapter(adapter);

        adapter.setList(getdata());

        MDEBUG.debug(adapter.arrayList.size() + " SIZE ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.marty_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_asc:
                adapter.setList(getdata().stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toCollection(ArrayList<Integer>::new)));
                break;
            case R.id.menu_desc:
                adapter.setList(getdata().stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toCollection(ArrayList<Integer>::new)));
                break;
            case R.id.menu_even:
                adapter.setList(getdata().stream().filter((i) -> i % 2 == 0).collect(Collectors.toCollection(ArrayList<Integer>::new)));
                break;
            case R.id.menu_odd:
                adapter.setList(getdata().stream().filter((i) -> i % 2 != 0).collect(Collectors.toCollection(ArrayList<Integer>::new)));

                break;
        }


        return true;
    }


    ArrayList<Integer> getdata() {

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 1; i <= 100; i++)
            arrayList.add(i);

        return arrayList;
    }


    @OnClick({R.id.single_search_btn, R.id.range_search_btn})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.single_search_btn:
                    Integer result =getdata().stream()
                            .filter(x -> x == Integer.parseInt(singleSearchEdt.getText().toString()))
                            .findAny()
                            .orElse(-1);
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(result);

                    adapter.setList(arrayList);
                    break;
                case R.id.range_search_btn:
                    int num1 = Integer.parseInt(rangeSearchEdt1.getText().toString());
                    int num2 = Integer.parseInt(rangeSearchEdt2.getText().toString());

                    if (num1 == num2) {
                        adapter.setList(new ArrayList<>(num1));
                        return;
                    }
                    int start, end;
                    start = num1 < num2 ? num1 : num2;
                    end = num1 < num2 ? num2 : num1;
                    if (start <= 0 || start > 100 || end <= 0 || end > 100) {
                        Snackbar.make(findViewById(android.R.id.content), "제대로 입력해라", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    adapter.setList(IntStream.rangeClosed(start, end).boxed().collect(Collectors.toCollection(ArrayList<Integer>::new)));
                    break;
            }
        }catch (Exception e){
            Snackbar.make(findViewById(android.R.id.content), "입력해라", Snackbar.LENGTH_SHORT).show();

        }
    }
}


class StreamListAdapter extends MartyAdapter<Integer, StreamVH> {
    public StreamListAdapter(Context mCon, Integer mLayout) {
        super(mCon, mLayout);
    }

    @Override
    public StreamVH getViewHolder(View view) {
        return new StreamVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamVH streamVH, int i) {
        streamVH.bind(arrayList.get(i), getmCon());

    }
}


class StreamVH extends MartyVH<Integer> {
    TextView tv;

    public StreamVH(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    @Override
    public void bind(Integer integer, Context mCon) {
        tv.setText(integer + "");

    }
}