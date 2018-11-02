package com.ration.rationstudy.marty.Data;

import com.ration.rationstudy.marty.chapters.chapter1.Chapter1_1;

/**
 * User: Marty
 * Date: 2018-11-02
 * Time: 오후 2:41
 * Description:
 */
public class Subject {

    public final static int CHAPTER_1_1 = 1;



    public static Class<?> getActivity(int oid){
        Class<?> c = null;
        switch (oid){
            case CHAPTER_1_1 :c = Chapter1_1.class;break;
        }
        return c;
    }
    public Subject(String title, int oid) {
        this.title = title;
        this.oid = oid;
    }

    String title;
    int oid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
