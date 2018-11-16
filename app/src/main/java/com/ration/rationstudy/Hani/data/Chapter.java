package com.ration.rationstudy.Hani.data;

public enum Chapter {
    Chapter01("Lambda"), Chapter02("Stream"),Chapter03("StudyList"),Chapter04("Gallery");

    private String title;

    Chapter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
