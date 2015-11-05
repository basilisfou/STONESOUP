package com.intership.vasilisfouroulis.saxparserretrofit.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by vassilis on 3/11/2015.
 */

@Root(name="channel", strict = false)
public class Channel  {

    @ElementList(inline = true)
    private List<Item> itemList;

    public List<Item> getItemList(){
        return itemList;
    }

}
