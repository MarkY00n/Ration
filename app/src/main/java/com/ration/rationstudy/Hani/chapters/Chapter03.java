package com.ration.rationstudy.Hani.chapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ration.rationstudy.Hani.common.BaseActivity;
import com.ration.rationstudy.Hani.common.HaniAdapter;
import com.ration.rationstudy.Hani.common.HaniViewHolder;
import com.ration.rationstudy.R;
import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.http.ApiService;
import com.ration.rationstudy.http.RationCallback;
import com.ration.rationstudy.http.response.StudentRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Chapter03 extends BaseActivity {
    RecyclerView recyclerView;
    StudentAdapter adapter;
    Spinner spinner;
    EditText search_edt;
    Button search_btn;
    ArrayList<StudentRepo> repos;
    Stream<StudentRepo> stream;
    Comparator<StudentRepo> compare;
    int s_num = 0;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hani_activity_chapter03);
        reqData();
        findViews();
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(this, R.layout.hani_item01);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hani_menu02, menu);
        return true;
    }

    private void reqData() {
        ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);
        api.listStudentRepo().enqueue(new RationCallback<>(this, (call, response) -> {
            if (response.body() != null) {
                repos = response.body().getResult();
                stream = repos.stream();
                compare = Comparator.comparing(StudentRepo::getNumber);
                adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
            }
        }));
    }

    private void accData() {

        Stream<StudentRepo> acc_stream = adapter.arrayList.stream();
        compare = Comparator.comparing(StudentRepo::getNumber);
        adapter.setItemList((ArrayList<StudentRepo>) acc_stream.sorted(compare).collect(Collectors.toList()));
    }

    private void descData() {
        Stream<StudentRepo> des_stream = adapter.arrayList.stream();
        compare = Comparator.comparing(StudentRepo::getNumber).reversed();
        adapter.setItemList((ArrayList<StudentRepo>) des_stream.sorted(compare).collect(Collectors.toList()));
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_num = position;
                switch (position) {
                    case 0: {
                        stream = repos.stream();
                        compare = Comparator.comparing(StudentRepo::getNumber);
                        adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
                        hideKeyboard();
                    }
                    break;
                    case 1: {
                        search_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                        imm.showSoftInput(search_edt, 0);
                    }
                    break;
                    case 2: {
                        search_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                        imm.showSoftInput(search_edt, 0);
                    }
                    break;
                    case 3: {
                        search_edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                        imm.showSoftInput(search_edt, 0);
                    }
                    break;
                    case 4: {
                        search_edt.setInputType(InputType.TYPE_CLASS_TEXT);
                        imm.showSoftInput(search_edt, 0);
                    }
                    break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void hideKeyboard() {
        imm.hideSoftInputFromWindow(search_edt.getWindowToken(), 0);
    }

    @Override
    public void init() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        search_btn.setOnClickListener(v -> {
            try {
                switch (s_num) {
                    case 0: {
                        Toast.makeText(this, "전체는 검색 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case 1: {
                        if (search_edt.getText().toString().length() <= 0) {
                            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!Pattern.matches("^[0-9]+$", search_edt.getText().toString())) {
                            Toast.makeText(this, "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        stream = repos.stream().filter((StudentRepo b) -> b.getNumber().startsWith(search_edt.getText().toString()));
                        compare = Comparator.comparing(StudentRepo::getNumber);
                        adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
                    }
                    break;
                    case 2: {
                        if (search_edt.getText().toString().length() <= 0) {
                            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!Pattern.matches("^[0-9]+$", search_edt.getText().toString())) {
                            Toast.makeText(this, "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int edit_num = Integer.parseInt(search_edt.getText().toString());
                        stream = repos.stream().filter((StudentRepo b) -> b.getYear() == edit_num);
                        compare = Comparator.comparing(StudentRepo::getNumber);
                        adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
                    }
                    break;
                    case 3: {
                        if (search_edt.getText().toString().length() <= 0) {
                            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (!Pattern.matches("^[0-9]+$", search_edt.getText().toString())) {
                            Toast.makeText(this, "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int edit_num = Integer.parseInt(search_edt.getText().toString());
                        stream = repos.stream().filter((StudentRepo b) -> b.getGrade() == edit_num);
                        compare = Comparator.comparing(StudentRepo::getNumber);
                        adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
                    }
                    break;
                    case 4: {
                        if (search_edt.getText().toString().length() <= 0) {
                            Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        stream = repos.stream().filter((StudentRepo b) -> b.getPart().startsWith(search_edt.getText().toString()));
                        compare = Comparator.comparing(StudentRepo::getNumber);
                        adapter.setItemList((ArrayList<StudentRepo>) stream.sorted(compare).collect(Collectors.toList()));
                    }
                    break;

                }
                hideKeyboard();
            } catch (Exception e) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.asc_menu: {
                accData();
            }
            break;
            case R.id.desc_menu: {
                descData();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
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
