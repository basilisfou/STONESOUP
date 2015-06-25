package com.example.vasilis.myapplication;

/**
 * Created by Vasilis on 22/6/2015.
 */
public class Item {
    private String name;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int pIcon) {
        icon = pIcon;
    }

    public String getName() {

        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public Item(String pName, int pIcon) {
        name = pName;
        icon = pIcon;
    }
}
