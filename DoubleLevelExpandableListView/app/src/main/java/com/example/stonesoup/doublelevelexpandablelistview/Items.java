package com.example.stonesoup.doublelevelexpandablelistview;

/**
 * Created by vfour_000 on 17/8/2015.
 */
public class Items {
    public Items(String title) {
        this.title = title;
    }

    public Items(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public Items(String title, Boolean hasChild) {
        this.title = title;
        this.hasChild = hasChild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    private String title;
    private Boolean hasChild = false;


}
