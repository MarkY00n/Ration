package com.ration.rationstudy.mark.chapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.mark.common.BaseActivity;
import com.ration.rationstudy.mark.common.MarkAdapter;
import com.ration.rationstudy.mark.common.MarkViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter02 extends BaseActivity {

    private RecyclerView rv_data;
    private EditText et_selectNum;
    private EditText et_firstNum;
    private EditText et_lastNum;
    private Button btn_select;
    private Button btn_range;

    private StreamDataAdapter streamDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity_chapter02);

        init();
    }

    @Override
    public void findViews() {
        rv_data = (RecyclerView) findViewById(R.id.rv_data);
        et_selectNum = (EditText) findViewById(R.id.et_selectNum);
        et_firstNum = (EditText) findViewById(R.id.et_firstNum);
        et_lastNum = (EditText) findViewById(R.id.et_lastNum);
        btn_select = (Button) findViewById(R.id.btn_selectNum);
        btn_range = (Button) findViewById(R.id.btn_range);
    }

    @Override
    public void init() {
        findViews();
        btn_select.setOnClickListener(onClickListener);
        btn_range.setOnClickListener(onClickListener);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        streamDataAdapter = new StreamDataAdapter(this, R.layout.mark_list_data);
        streamDataAdapter.setItemList(getDataArray());
        rv_data.setAdapter(streamDataAdapter);
    }

    View.OnClickListener onClickListener = v -> {
      if (v.getId() == R.id.btn_range) {
          if (et_firstNum.length() > 0 && et_lastNum.length() > 0) {
              int firstNum = Integer.parseInt(et_firstNum.getText().toString());
              int lastNum = Integer.parseInt(et_lastNum.getText().toString());

              if (firstNum > 0 && lastNum > firstNum && firstNum < 101  && lastNum < 101) {
//                  List<Integer> rangeNumbers = getDataArray();
//                  streamDataAdapter.setItemList((ArrayList<Integer>) rangeNumbers.stream().filter(n -> firstNum <= n && lastNum >= n).collect(Collectors.toList()));
                  streamDataAdapter.setItemList((ArrayList<Integer>) IntStream.range(firstNum, lastNum + 1).boxed().collect(Collectors.toList()));
              }
          }
      } else if (v.getId() == R.id.btn_selectNum) {
          if (et_selectNum.length() > 0) {
              List<Integer> selectNumbers = getDataArray();
              streamDataAdapter.setItemList((ArrayList<Integer>) selectNumbers.stream().filter(n -> n == Integer.parseInt(et_selectNum.getText().toString())).collect(Collectors.toList()));
          }
      }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mark_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_asc : asc();
                return true;
            case R.id.menu_desc : desc();
                return true;
            case R.id.menu_even : even();
                return true;
            case R.id.menu_odd : odd();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void asc() {
        List<Integer> numbers = getDataArray();
        List<Integer> collectList = numbers.stream().sorted().collect(Collectors.toList());
        streamDataAdapter.setItemList((ArrayList<Integer>) collectList);
    }

    void desc() {
        List<Integer> numbers = getDataArray();
        List<Integer> reCollectList = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        streamDataAdapter.setItemList((ArrayList<Integer>) reCollectList);
    }

    void even() {
        List<Integer> numbers = getDataArray();
        List<Integer> evenList = numbers.stream().sorted().filter(n -> n % 2 == 0).collect(Collectors.toList());
        streamDataAdapter.setItemList((ArrayList<Integer>) evenList);
    }

    void odd() {
        List<Integer> numbers = getDataArray();
        List<Integer> oddList = numbers.stream().sorted().filter(n -> n % 2 != 0).collect(Collectors.toList());
        streamDataAdapter.setItemList((ArrayList<Integer>) oddList);
    }


    private ArrayList<Integer> getDataArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            arrayList.add(i);
        }

        return arrayList;
    }

    class StreamDataAdapter extends MarkAdapter<Integer, StreamDataViewHolder> {

        public StreamDataAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public StreamDataViewHolder viewHolder(View view) {
            return new StreamDataViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StreamDataViewHolder streamDataViewHolder, int i) {
            streamDataViewHolder.bindTo(getContext(), getArrayList().get(i));
        }
    }

    class StreamDataViewHolder extends MarkViewHolder<Integer> {

        TextView tvData;

        public StreamDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tvData);
        }

        @Override
        public void bindTo(Context context, Integer item) {
            tvData.setText(String.valueOf(item));
        }
    }

}
