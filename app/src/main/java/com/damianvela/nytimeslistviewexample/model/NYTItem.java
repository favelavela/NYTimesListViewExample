package com.damianvela.nytimeslistviewexample.model;

/**
 * Created by Android1 on 6/3/2015.
 */
public class NYTItem {
    private String title, thumbnailUrl;

    public NYTItem() {
    }

    public NYTItem(String name, String thumbnailUrl) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
