package com.ration.rationstudy.http.response;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dev.Dec on 2018-11-05
 */
public class StudentRepo implements Comparable<String> {

    @SerializedName("oid")
    private String oid;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String number;
    @SerializedName("grade")
    private int grade;
    @SerializedName("year")
    private int year;
    @SerializedName("part")
    private String part;

    public String getParam(int pos){
        String val = "";
        switch (pos){
            case 1 : // 학번
                val =  number;
            break;
            case 2 : // 학년
                val =  year+"";
                break;
            case 3 : // 성적
                val =  grade+"";
                break;
            case 4 : // 전공
                val =  part;
                break;


        }
        return val;
    }

    public String getOid() {
        return oid;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getGrade() {
        return grade;
    }

    public int getYear() {
        return year;
    }

    public String getPart() {
        return part;
    }

    @Override
    public int compareTo(@NonNull String o) {
        return this.getNumber().compareTo(o);
    }
}
