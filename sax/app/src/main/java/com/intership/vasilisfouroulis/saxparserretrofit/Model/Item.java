package com.intership.vasilisfouroulis.saxparserretrofit.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by vfour_000 on 4/11/2015.
 */

@Root(name = "item", strict = false)
public class Item {

    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name="guid")
    private String Guid;



    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }
}
