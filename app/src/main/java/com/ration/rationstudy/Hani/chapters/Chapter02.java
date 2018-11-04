package com.ration.rationstudy.Hani.chapters;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ration.rationstudy.Hani.common.BaseActivity;
import com.ration.rationstudy.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter02 extends BaseActivity {
    ListView listview;
    ArrayAdapter adapter;
    List<Integer> num;
    Stream<Integer> intStream;
    Button btn_01, btn_02;
    EditText edit_01, edit_02, edit_03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hani_activity_chapter02);
        findViews();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hani_menu01, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        adapter.clear();
        int id = item.getItemId();
        switch (id) {
            case R.id.asc_menu: {
                intStream = num.stream().sorted();
            }
            break;
            case R.id.desc_menu: {
                intStream = num.stream().sorted(Comparator.reverseOrder());
            }
            break;
            case R.id.even_menu: {
                intStream = num.stream().filter(number -> number % 2 == 0);
            }
            break;
            case R.id.odd_menu: {
                intStream = num.stream().filter(number -> number % 2 == 1);
            }
            break;

        }
        adapter.addAll(intStream.toArray());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void findViews() {
        listview = (ListView) findViewById(R.id.listview);
        btn_01 = (Button) findViewById(R.id.btn_01);
        btn_02 = (Button) findViewById(R.id.btn_02);
        edit_01 = (EditText) findViewById(R.id.edit_01);
        edit_02 = (EditText) findViewById(R.id.edit_02);
        edit_03 = (EditText) findViewById(R.id.edit_03);
    }

    @Override
    public void init() {

        num = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            num.add(i);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);

        btn_01.setOnClickListener(v -> {
            if (edit_01.getText().toString().length() < 0) {
                Toast.makeText(this, "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            int edit01_num = Integer.parseInt(edit_01.getText().toString());

            if (edit01_num > 100) {
                Toast.makeText(this, "100이하를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter.clear();
            intStream = num.stream().filter(i -> i == edit01_num);

            adapter.addAll(intStream.toArray());
        });

        btn_02.setOnClickListener(v -> {
            if (edit_02.getText().toString().length() < 0 || edit_03.getText().toString().length() < 0) {
                Toast.makeText(this, "두 입력창에 모두 숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                return;
            }

            int edit02_num = Integer.parseInt(edit_02.getText().toString());
            int edit03_num = Integer.parseInt(edit_03.getText().toString());

            if (edit02_num < 1 || edit02_num > 100 || edit03_num < 1 || edit03_num > 100) {
                Toast.makeText(this, "범위에 맡게 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edit02_num > edit03_num) {
                Toast.makeText(this, "범위에 맡게 숫자를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter.clear();
//            filter로
//            intStream = num.stream().filter(i -> edit02_num <= i && i <= edit03_num);
//            adapter.addAll(intStream.toArray());
//            range로
            adapter.addAll(IntStream.rangeClosed(edit02_num, edit03_num).boxed().toArray());
        });
    }

}
