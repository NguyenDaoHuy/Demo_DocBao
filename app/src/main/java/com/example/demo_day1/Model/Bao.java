package com.example.demo_day1.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Bao implements Serializable {
    private final String title;
    private final String description;

    public Bao(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bao{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
