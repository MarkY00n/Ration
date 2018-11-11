package com.ration.rationstudy.mark.data;

import com.ration.rationstudy.mark.chapters.Chapter01;
import com.ration.rationstudy.mark.chapters.Chapter02;
import com.ration.rationstudy.mark.chapters.Chapter03;
import com.ration.rationstudy.mark.chapters.FoodMenuChapter;

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
            case Chapter02: classCode = Chapter02.class;
            break;
            case Chapter03: classCode = Chapter03.class;
            break;
            case FoodMenuChapter: classCode = FoodMenuChapter.class;
            break;
        }
        return classCode;
    }
}
