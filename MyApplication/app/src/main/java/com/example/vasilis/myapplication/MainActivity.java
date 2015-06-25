package com.example.vasilis.myapplication;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private List<Item> mList;
    private RecyclerView lRecyclerView;
    private Adapter mAdapter;
    private LinearLayoutManager llm;
    private String[] navItems;
    private TypedArray navItemsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* a new recycler */
        lRecyclerView = (RecyclerView)findViewById(R.id.cardList);
        lRecyclerView.setHasFixedSize(true);

        //a new adapter
        mAdapter = new Adapter(mList);

        // a new Linear layout
        llm = new LinearLayoutManager(this);
        //Orientation of the RecyclerView
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setOrientation( LinearLayoutManager.VERTICAL);
        //instantiate and set the LayoutManager when being inflated
        lRecyclerView.setLayoutManager(llm);

        //setting the adapter
        lRecyclerView.setAdapter(mAdapter);

        //populating the list
        populateListItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*
        populate the list with items
     */
    public void populateListItems() {
        //a new List
        mList = new ArrayList<Item>();

        //nav drawer icons
        navItems = getResources().getStringArray(R.array.Numbers);
        navItemsIcon = getResources().obtainTypedArray(R.array.images);
        for (int i = 0; i < navItems.length; i++) {
            mList.add(i, new Item(navItems[i], navItemsIcon.getResourceId(i, -1)));
        }
    }


}
