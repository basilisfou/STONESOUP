package com.example.stonesoup.doublelevelexpandablelistview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vfour_000 on 17/8/2015.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter {

    private List<Items> ThirdLevel;
    private Context mContext;
    private List<Items> SecondLevel;
    private Items item;
    private Map<Items,List<Items>> map;

    public SecondLevelAdapter(Context pContext,Items pItem) {

        this.mContext = pContext;
        this.item = pItem;
        SecondLevel = new ArrayList<>();
        SecondLevel.add(item);
        ThirdLevel = new ArrayList<>();

        map = new HashMap<>();

        for(Items item : SecondLevel){
            if(item.getTitle().equals("3.2")){
                ThirdLevel = new ArrayList<>();
                String itemsTrirdLevel [] = mContext.getResources().getStringArray(R.array.third_level_third_item_second_item);
                for(int i = 0 ; i < itemsTrirdLevel.length ; i ++) {
                    ThirdLevel.add(i, new Items(itemsTrirdLevel[i]));
                }
            } else {
                ThirdLevel = new ArrayList<>();
            }

            if(ThirdLevel != null)
                map.put(item,ThirdLevel);
        }

    }

    @Override
    public int getGroupCount() {
        return SecondLevel.size();
        //return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(SecondLevel.get(groupPosition)).size();
        //return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(SecondLevel.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    /**
     * second level
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Items item = SecondLevel.get(groupPosition);
        if(convertView == null) {
            LayoutInflater lLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = lLayoutInflater.inflate(R.layout.child_item, null);
        }
        TextView lTextView = (TextView)convertView.findViewById(R.id.title_fl);
        lTextView.setText(item.getTitle());

        return convertView;

    }
    /**
     * third level
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Items item2 = (Items)getChild(groupPosition,childPosition);

        LayoutInflater lLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = lLayoutInflater.inflate(R.layout.second_level_child_item, null);
        }

        TextView lTextView = (TextView)convertView.findViewById(R.id.title_SL);
        lTextView.setText(item2.getTitle());
        //lTextView.setText("GrandSon--------------->");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
