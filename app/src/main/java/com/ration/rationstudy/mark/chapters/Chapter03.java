package com.ration.rationstudy.mark.chapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.http.ApiService;
import com.ration.rationstudy.http.RationCallback;
import com.ration.rationstudy.http.response.StudentRepo;
import com.ration.rationstudy.mark.common.BaseActivity;
import com.ration.rationstudy.mark.common.MarkAdapter;
import com.ration.rationstudy.mark.common.MarkViewHolder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter03 extends BaseActivity {

    private RecyclerView rv_data;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity_chapter03);

        init();
    }

    @Override
    public void findViews() {
        rv_data = (RecyclerView) findViewById(R.id.rv_data);
    }

    @Override
    public void init() {
        findViews();
        reqData();
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(this, R.layout.mark_list_student);
        rv_data.setAdapter(adapter);
    }

    private void reqData() {
        ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);
        api.listStudentRepo().enqueue(new RationCallback<>(this, (call, response) -> {
            if (response.body() != null) {
                ArrayList<StudentRepo> repos = response.body().getResult();
                Iterator<StudentRepo> iterator = repos.iterator();
                adapter.setItemList(repos);
            }
        }));
    }

    class StudentAdapter extends MarkAdapter<StudentRepo, StudentViewHolder> {

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

    class StudentViewHolder extends MarkViewHolder<StudentRepo> {

        private TextView tv_number;
        private TextView tv_year;
        private TextView tv_name;
        private TextView tv_part;
        private TextView tv_grade;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_year = (TextView) itemView.findViewById(R.id.tv_year);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_part = (TextView) itemView.findViewById(R.id.tv_part);
            tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
        }

        @Override
        public void bindTo(Context context, StudentRepo item) {
            String format = String.format("학번 : %s", item.getNumber());
            tv_number.setText(format);
            format = String.format("학년 : %d", item.getYear());
            tv_year.setText(format);
            format = String.format("이름 : %s", item.getName());
            tv_name.setText(format);
            format = String.format("전공 : %s", item.getPart());
            tv_part.setText(format);
            format = String.format("성적 : %d", item.getGrade());
            tv_grade.setText(format);
        }
    }

}





