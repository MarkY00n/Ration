package com.ration.rationstudy.Hani.chapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ration.rationstudy.Hani.common.BaseActivity;
import com.ration.rationstudy.Hani.common.HaniAdapter;
import com.ration.rationstudy.Hani.common.HaniViewHolder;
import com.ration.rationstudy.R;
import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.http.ApiService;
import com.ration.rationstudy.http.RationCallback;
import com.ration.rationstudy.http.response.StudentRepo;

import java.util.ArrayList;
import java.util.stream.Stream;


public class Chapter03 extends BaseActivity {
    RecyclerView recyclerView;
    StudentAdapter adapter;
    Spinner spinner;
    EditText search_edt;
    Button search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hani_activity_chapter03);
        findViews();
        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(this, R.layout.hani_item01);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hani_menu02, menu);
        reqData();
        return true;
    }

    private void reqData() {
        ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);
        api.listStudentRepo().enqueue(new RationCallback<>(this, (call, response) -> {
            if (response.body() != null) {
                ArrayList<StudentRepo> repos = response.body().getResult();
                Stream<StudentRepo> stream;
                stream = repos.stream().sorted();
                adapter.setItemList((ArrayList<StudentRepo>) stream);
            }
        }));
    }


    @Override
    public void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        spinner = (Spinner) findViewById(R.id.spinner);
        search_edt = (EditText) findViewById(R.id.search_edt);
        search_btn = (Button) findViewById(R.id.search_btn);

        String[] item = new String[]{"전체", "학번", "학년", "성적", "전공"};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void init() {
        search_btn.setOnClickListener(v -> {
            try {

            } catch (Exception e) {

            }
        });
    }

    class StudentAdapter extends HaniAdapter<StudentRepo, StudentViewHolder> {

        public StudentAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public StudentViewHolder viewHolder(View view) {
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
            studentViewHolder.bindTo(getContext(), getArrayList().get(i));
        }
    }

    class StudentViewHolder extends HaniViewHolder<StudentRepo> {

        private TextView num_tv;
        private TextView year_tv;
        private TextView name_tv;
        private TextView part_tv;
        private TextView grade_tv;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            num_tv = (TextView) itemView.findViewById(R.id.num_tv);
            year_tv = (TextView) itemView.findViewById(R.id.year_tv);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            part_tv = (TextView) itemView.findViewById(R.id.part_tv);
            grade_tv = (TextView) itemView.findViewById(R.id.grade_tv);
        }

        @Override
        public void bindTo(Context context, StudentRepo item) {
            String format = String.format("%s", item.getNumber());
            num_tv.setText(format);
            format = String.format("%d", item.getYear());
            year_tv.setText(format);
            format = String.format("%s", item.getName());
            name_tv.setText(format);
            format = String.format("%s", item.getPart());
            part_tv.setText(format);
            format = String.format("%d", item.getGrade());
            grade_tv.setText(format);
        }
    }

}
