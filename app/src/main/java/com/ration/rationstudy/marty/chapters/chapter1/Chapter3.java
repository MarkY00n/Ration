package com.ration.rationstudy.marty.chapters.chapter1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.http.ApiService;
import com.ration.rationstudy.http.ResponseBody;
import com.ration.rationstudy.http.response.StudentRepo;
import com.ration.rationstudy.marty.common.BsDialog;
import com.ration.rationstudy.marty.common.MartyAdapter;
import com.ration.rationstudy.marty.common.MartyCallback;
import com.ration.rationstudy.marty.common.MartyVH;

import java.util.ArrayList;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Chapter3 extends AppCompatActivity implements TextView.OnEditorActionListener {

    @BindView(R.id.main_search_edt)
    EditText mainSearchEdt;
    @BindView(R.id.main_search_btn)
    TextView mainSearchBtn;
    @BindView(R.id.main_search_rl)
    RelativeLayout mainSearchRl;
    @BindView(R.id.main_list)
    RecyclerView mainList;

    ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);

    ArrayList<StudentRepo> items;

    Stream2ListAdapter adapter;
    BsDialog dlg;

    BsDialog.BsListener callback = (p)->{
        String text = "";
        switch (p) {
            case 0: // 1
                text = "전체";
            break;
            case 1:
                text = "학번";
                break;
            case 2:
                text = "학년";
                break;
            case 3:
                text = "성적";
                break;
            case 4:  // 5
                text = "전공";
                break;
        }
        mainSearchBtn.setText(text);
        mainSearchBtn.setTag(p);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3);
        ButterKnife.bind(this);
        adapter = new Stream2ListAdapter(this, R.layout.marty_chap3_listitem);
        mainList.setAdapter(adapter);
        mainSearchEdt.setOnEditorActionListener(this::onEditorAction);
        api.listStudentRepo().enqueue(new MartyCallback<ResponseBody<StudentRepo>>(this, (call, response) -> {
            items = response.body().getResult();
            items.stream().sorted((a, b) -> a.compareTo(b.getNumber())).collect(Collectors.toList());
            adapter.setList(items);
        }));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            return true;
        }
        return false;
    }

    @OnClick(R.id.main_search_btn)
    public void onViewClicked() {
        BsDialog bottomSheetDialog = BsDialog.getInstance(callback);
        bottomSheetDialog.show(getSupportFragmentManager(),"dialog");


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
                adapter.setList(items.stream().sorted((a, b) -> a.compareTo(b.getNumber())).collect(Collectors.toCollection(ArrayList<StudentRepo>::new)));
                break;
            case R.id.menu_desc:
                adapter.setList(items.stream().sorted((a, b) -> b.compareTo(a.getNumber())).collect(Collectors.toCollection(ArrayList<StudentRepo>::new)));
                break;
        }


        return true;
    }
}


class Stream2ListAdapter extends MartyAdapter<StudentRepo, Stream2VH> {
    public Stream2ListAdapter(Context mCon, Integer mLayout) {
        super(mCon, mLayout);
    }

    @Override
    public Stream2VH getViewHolder(View view) {
        return new Stream2VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Stream2VH streamVH, int i) {
        streamVH.bind(arrayList.get(i), getmCon());

    }
}


class Stream2VH extends MartyVH<StudentRepo> {
    TextView tv1, tv2, tv3, tv4, tv5;

    public Stream2VH(@NonNull View itemView) {
        super(itemView);
        tv1 = itemView.findViewById(R.id.tv_number);
        tv2 = itemView.findViewById(R.id.tv_year);
        tv3 = itemView.findViewById(R.id.tv_name);
        tv4 = itemView.findViewById(R.id.tv_part);
        tv5 = itemView.findViewById(R.id.tv_grade);
    }

    @Override
    public void bind(StudentRepo item, Context mCon) {
        tv1.setText(mCon.getString(R.string.tv1, item.getNumber()));
        tv2.setText(mCon.getString(R.string.tv2, item.getYear() + ""));
        tv3.setText(mCon.getString(R.string.tv3, item.getName()));
        tv4.setText(mCon.getString(R.string.tv4, item.getPart()));
        tv5.setText(mCon.getString(R.string.tv5, item.getGrade() + ""));
    }
}
