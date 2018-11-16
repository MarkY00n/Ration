package com.ration.rationstudy.Hani.data;

import com.ration.rationstudy.Hani.chapters.Chapter01;
import com.ration.rationstudy.Hani.chapters.Chapter02;
import com.ration.rationstudy.Hani.chapters.Chapter03;
import com.ration.rationstudy.Hani.chapters.Chapter04;

public class Subject {

    private Chapter chapter;
    private Class<?> classCode;

    public Subject(Chapter chapter) {
        this.chapter = chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public Class<?> getClassCode() {
        switch (chapter) {
            case Chapter01:
                classCode = Chapter01.class;
                break;

            case Chapter02:
                classCode = Chapter02.class;
                break;

            case Chapter03:
                classCode = Chapter03.class;
                break;
            case Chapter04:
                classCode = Chapter04.class;
                break;
        }
        return classCode;
    }
}
