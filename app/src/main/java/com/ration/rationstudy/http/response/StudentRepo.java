package com.ration.rationstudy.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dev.Dec on 2018-11-05
 */
public class StudentRepo {

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
}
