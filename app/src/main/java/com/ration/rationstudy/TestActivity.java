package com.ration.rationstudy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ration.rationstudy.common.RationAPP;
import com.ration.rationstudy.http.ApiService;
import com.ration.rationstudy.http.RationCallback;
import com.ration.rationstudy.http.ResponseBody;
import com.ration.rationstudy.http.ServerRequest;
import com.ration.rationstudy.http.response.StudentRepo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private TextView tv_jsonData;

    ApiService api = RationAPP.getRAPP().getRetrofit().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_jsonData = (TextView) findViewById(R.id.tv_jsonData);


        api.listStudentRepo().enqueue(new RationCallback<ResponseBody<StudentRepo>>(this,(call,res)->{
            StudentRepo repos = res.body().getResult().get(0);
            StringBuilder builder = new StringBuilder();
            builder.append(repos.getOid()).append(repos.getName()).append(repos.getNumber()).append(repos.getGrade()).append(repos.getYear()).append(repos.getPart());
            tv_jsonData.setText(builder.toString());
        }));


    }


}
