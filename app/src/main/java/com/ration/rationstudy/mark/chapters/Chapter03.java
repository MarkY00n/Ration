package com.ration.rationstudy.mark.chapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.common.Utils;
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
    private ArrayList<StudentRepo> repos;
    private Spinner spinner;
    private EditText et_search;
    private Button btn_search;
    private boolean isASC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_activity_chapter03);

        init();
    }

    @Override
    public void findViews() {
        rv_data = (RecyclerView) findViewById(R.id.rv_data);
        spinner = (Spinner) findViewById(R.id.spinner_req);
        et_search = (EditText) findViewById(R.id.et_search);
        btn_search = (Button) findViewById(R.id.btn_search);
    }

    @Override
    public void init() {
        findViews();
        reqData();
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(this, R.layout.mark_list_student);
        rv_data.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerSelectedListener);
        btn_search.setOnClickListener(view -> {

            if (et_search.length() > 0 && repos != null) {
                final String search = et_search.getText().toString();

                Comparator<StudentRepo> comparator = getComparator(isASC);

                if (spinner.getSelectedItemPosition() == 1) {
                    // 학번
                    adapter.setItemList((ArrayList<StudentRepo>) repos
                            .stream()
                            .filter(repo -> repo.getNumber().startsWith(search))
                            .sorted(comparator)
                            .collect(Collectors.toList()));

                } else if (spinner.getSelectedItemPosition() == 2) {
                    // 학년
                    adapter.setItemList((ArrayList<StudentRepo>) repos
                            .stream()
                            .filter(repo -> String.valueOf(repo.getYear()).startsWith(search))
                            .sorted(comparator)
                            .collect(Collectors.toList()));
                } else if (spinner.getSelectedItemPosition() == 3) {
                    // 이름
                    adapter.setItemList((ArrayList<StudentRepo>) repos
                            .stream()
                            .filter(repo -> repo.getName().startsWith(search))
                            .sorted(comparator)
                            .collect(Collectors.toList()));
                } else if (spinner.getSelectedItemPosition() == 4) {
                    // 전공
                    adapter.setItemList((ArrayList<StudentRepo>) repos
                            .stream()
                            .filter(repo -> repo.getPart().startsWith(search))
                            .sorted(comparator)
                            .collect(Collectors.toList()));
                } else if (spinner.getSelectedItemPosition() == 5) {
                    // 성적
                    adapter.setItemList((ArrayList<StudentRepo>) repos
                            .stream()
                            .filter(repo -> String.valueOf(repo.getGrade()).startsWith(search))
                            .sorted(comparator)
                            .collect(Collectors.toList()));
                }


            }
        });

    }

    private void reqData() {
        ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);
        api.listStudentRepo().enqueue(new RationCallback<>(this, (call, response) -> {
            if (response.body() != null) {
                repos = response.body().getResult();
                refresh(repos);
            }
        }));
    }

    AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                // ALL
               refresh(repos);
            } else if (position == 1 || position == 2 || position == 5) {
                // 학번, 학년, 성적
                et_search.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (position == 3 || position == 4) {
                // 이름, 전공
                et_search.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mark_menu_student, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_asc : {
                isASC = true;
                refresh(adapter.getArrayList());
                return true;
            }
            case R.id.menu_desc : {
                isASC = false;
                refresh(adapter.getArrayList());
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private Comparator<StudentRepo> getComparator(boolean isASC) {
        if (isASC) {
            /// 오름차순
            return Comparator.comparing(StudentRepo::getNumber);
        } else {
            /// 내리차순
            return Comparator.comparing(StudentRepo::getNumber).reversed();
        }
    }

    private void refresh(ArrayList<StudentRepo> repos) {
        Comparator<StudentRepo> comparator = getComparator(isASC);
        if (repos != null) {
            adapter.setItemList((ArrayList<StudentRepo>) repos
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList()));
        }
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





