package com.ration.rationstudy.http;

import com.ration.rationstudy.http.response.StudentRepo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dev.Dec on 2018-11-04
 */
public interface ApiService {

    String URL = "http://kimec0905.cafe24.com/";

    @GET("study/studylist.php")
    Call<ResponseBody<StudentRepo>> listStudentRepo();

}
