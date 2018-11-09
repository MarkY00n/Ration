package com.ration.rationstudy.Hani.data;

public enum Chapter {
    Chapter01("Lambda"), Chapter02("Stream"),Chapter03("StudyList");

    private String title;

    Chapter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
