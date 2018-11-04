package com.ration.rationstudy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_jsonData = (TextView) findViewById(R.id.tv_jsonData);

        ServerRequest.getService().listStudentRepo().enqueue(new Callback<ResponseBody<StudentRepo>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody<StudentRepo>> call, @NonNull Response<ResponseBody<StudentRepo>> response) {
                if (response.isSuccessful()) {
                    ArrayList<StudentRepo> repos = response.body().getResult();
                    String oid = repos.get(0).getOid();
                    String name = repos.get(0).getName();
                    String number =repos.get(0).getNumber();
                    String grade =repos.get(0).getGrade();
                    String year =repos.get(0).getYear();
                    String part =repos.get(0).getPart();

                    StringBuilder builder = new StringBuilder();
                    builder.append(oid).append(name).append(number).append(grade).append(year).append(part);
                    tv_jsonData.setText(builder.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody<StudentRepo>> call, @NonNull Throwable t) {

            }
        });
    }
}
