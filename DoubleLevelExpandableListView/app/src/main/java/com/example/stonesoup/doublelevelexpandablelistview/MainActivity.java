package com.example.stonesoup.doublelevelexpandablelistview;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static DrawerLayout mDrawerLayout;
    private static ExpandableListView mExpandableListView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ParentLevel mAdapter;
    private ArrayList<Items> firstLevel;
    private ArrayList<Items> SecondLevel;
    private Map<Items, List<Items>> mapList;
    private String[] NavigationDrawItemsFirstLevel;
    private String[] NavigationDrawItemsSecondLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Closed");// changing the title of the action bar with the name of the item
        //navigation Drawer
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //Expandable List View
        mExpandableListView = (ExpandableListView)findViewById(R.id.list_slideMenu);
        groupList();
        ChildList();
        /**
         * changing the groupIndicator from left to right
         */
        mExpandableListView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mExpandableListView.removeOnLayoutChangeListener(this);
                //getting the width of the Expandable List view
                int width = mExpandableListView.getWidth();

                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    mExpandableListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                            - getDipsFromPixel(5));
                } else {
                    mExpandableListView.setIndicatorBoundsRelative(width - getDipsFromPixel(35), width
                            - getDipsFromPixel(5));
                }
            }
        });

        groupList();
        ChildList();
    /*
        for (Map.Entry<Items, List<Items>> entry : mapList.entrySet()) {
            Log.d("bill","Key = " + entry.getKey().getTitle() + ", Value = " + entry.getValue());
        }*/
        // A new adapter
        mAdapter = new ParentLevel(this, mapList, firstLevel);

        //setting the adapter
        mExpandableListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        /**
         * toggling the sliding menu
         */
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.Opened,   R.string.Closed) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // calling onPrepareOptionsMenu() to hide action bar icons
                getSupportActionBar().setTitle("Closed");
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //when openig the navigation drawer hide the keyboard
                getSupportActionBar().setTitle("Opened");
                invalidateOptionsMenu();
            }
        };
        mActionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // toggle nav drawer on selecting action bar app icon/title
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case android.R.id.home:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Convert pixel to dip , help method for changing the menu indicator from left to right
     */
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    /**
     * populate group list
     */
    public void groupList() {
        NavigationDrawItemsFirstLevel = getResources().getStringArray(R.array.first_level);
        firstLevel = new ArrayList<>();

        for(int i = 0 ; i < NavigationDrawItemsFirstLevel.length; i ++) {
            firstLevel.add(i, new Items(NavigationDrawItemsFirstLevel[i]));
        }
    }
    /**
     * populate Child list
     */
    public void ChildList() {
        mapList = new LinkedHashMap<Items, List<Items>>();

        for(Items item : firstLevel){
            if(item.getTitle().equals("3")){
                SecondLevel = new ArrayList<>();
                String NavigationDrawItems1_3 [] = getResources().getStringArray(R.array.Second_level_third_item);

                for(int i = 0 ; i < NavigationDrawItems1_3.length ; i ++) {
                    SecondLevel.add(i, new Items(NavigationDrawItems1_3[i]));


                }

            } else if(item.getTitle().equals("5")) {
                SecondLevel = new ArrayList<>();
                String NavigationDrawItems1_5 [] = getResources().getStringArray(R.array.Second_level_fifth_item);

                for(int i = 0 ; i < NavigationDrawItems1_5.length ; i ++) {
                    SecondLevel.add(i, new Items(NavigationDrawItems1_5[i]));
                }
            } else {
                SecondLevel = new ArrayList<>();
            }

            if(SecondLevel != null)
                mapList.put(item,SecondLevel);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mExpandableListView);
        //hiding the menu items of the action bar if the navigation drawer is open
        if(mDrawerLayout!=null && drawerOpen)
            menu.clear();

        return super.onPrepareOptionsMenu(menu);
    }
}
