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
    private String grade;
    @SerializedName("year")
    private String year;
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

    public String getGrade() {
        return grade;
    }

    public String getYear() {
        return year;
    }

    public String getPart() {
        return part;
    }
}
