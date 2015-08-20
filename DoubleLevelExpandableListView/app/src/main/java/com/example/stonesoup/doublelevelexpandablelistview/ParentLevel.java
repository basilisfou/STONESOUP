package com.example.stonesoup.doublelevelexpandablelistview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vfour_000 on 17/8/2015.
 */
public class ParentLevel extends BaseExpandableListAdapter {
    private ArrayList<Items> firstLevel;
    private Map<Items, List<Items>> mMapList;
    private Activity mContext;
    private List<Items> getChilds;

    public ParentLevel(Activity pContext,Map<Items,List<Items>> pMapList, ArrayList<Items> firstLevel) {
        this.mContext = pContext;
        this.mMapList = pMapList;
        this.firstLevel = firstLevel;
        getChilds = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return firstLevel.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mMapList.get(firstLevel.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mMapList.get(firstLevel.get(groupPosition)).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater lLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = lLayoutInflater.inflate(R.layout.drawer_group_item, null);
        }

        TextView lTextView = (TextView)convertView.findViewById(R.id.title);
        lTextView.setText(firstLevel.get(groupPosition).getTitle());

        return convertView;
    }

    @Override
    public View getChildView( int groupPosition,  int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        final CustExpListView SecondLevelExpandableListView = new CustExpListView(mContext);
        final SecondLevelAdapter mAdapter = new SecondLevelAdapter(mContext,(Items)getChild(groupPosition,childPosition));
        SecondLevelExpandableListView.setAdapter(mAdapter);
        return SecondLevelExpandableListView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    /**
     * custom Expandable List View
     */
    public class CustExpListView extends ExpandableListView {
        int intGroupPosition, intChildPosition, intGroupid;

        public CustExpListView(Context context){
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
