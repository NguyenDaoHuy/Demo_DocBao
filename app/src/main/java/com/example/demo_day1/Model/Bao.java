package com.example.demo_day1.Model;

import java.io.Serializable;

public class Bao implements Serializable {
    private String title;
    private String description;

    public Bao(){}

    public Bao(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bao{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
