package com.example.jsontogridview.model;

public class Movie {
    String title;
    String year;
    String image;

    public Movie() {
    }

    public Movie(String title, String year, String image) {
        this.title = title;
        this.year = year;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
