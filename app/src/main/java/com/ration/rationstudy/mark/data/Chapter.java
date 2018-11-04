package com.ration.rationstudy.mark.data;

public enum Chapter {
    Chapter01("lambda"),
    Chapter02("Stream");

    private String title;

    Chapter(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
