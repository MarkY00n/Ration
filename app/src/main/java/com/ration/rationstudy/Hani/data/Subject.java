package com.ration.rationstudy.Hani.data;

import com.ration.rationstudy.Hani.chapters.Chapter01;

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
            case Chapter01: classCode = Chapter01.class;
            break;
        }
        return classCode;
    }
}
