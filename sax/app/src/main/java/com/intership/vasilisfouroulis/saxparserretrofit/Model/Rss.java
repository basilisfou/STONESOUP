package com.intership.vasilisfouroulis.saxparserretrofit.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by vfour_000 on 4/11/2015.
 */
@Root(name="rss", strict = false)
public class Rss {

    @Element(name="channel")
    private Channel channel;

    public Channel getmChannel() {
        return channel;
    }
}
