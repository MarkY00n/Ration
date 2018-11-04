package com.ration.rationstudy.Hani.data;

public enum Chapter {
    Chapter01("lambda");

    private String title;

    Chapter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
