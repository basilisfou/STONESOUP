package com.example.stonesoup.solarsystem.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stonesoup.solarsystem.Adapter.cardsAdapter;
import com.example.stonesoup.solarsystem.Adapter.cardsMoonAdapter;
import com.example.stonesoup.solarsystem.MainActivity;
import com.example.stonesoup.solarsystem.Models.Moons;
import com.example.stonesoup.solarsystem.Models.Planets;
import com.example.stonesoup.solarsystem.R;

import java.util.ArrayList;

public class FragmentMoons  extends baseFragment {

    private cardsMoonAdapter mAdapter;
    private ArrayList<Moons> mMoons;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cards, container , false);
        savedInstanceState = getArguments();
        if(savedInstanceState != null)
            mMoons = savedInstanceState.getParcelableArrayList("list_moon");
        /**
         * initialize Recycler View
         */
        mRecycler = (RecyclerView)v.findViewById(R.id.planet_list);
        mRecycler.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mAdapter = new cardsMoonAdapter(mMoons);
        mAdapter.setOnItemClickListener(new cardsMoonAdapter.OnItemClickListener() {

            @Override
            public void itemClick( int position, Moons moon) {
                onListViewItemClick(position,moon);
            }
        });
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(mLayoutManger);


        return v;
    }

    public void onListViewItemClick(int position,Moons moon){
        mFragmentManager = getFragmentManager();
        mFragment = mFragmentManager.findFragmentById(R.id.frame_container);
        mFragment = new FragmentDescriptionMoon();
        MainActivity.setmTitles(moon.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable("item",moon);
        mFragment.setArguments(bundle);
        //To the fragment
        mFragmentManager.beginTransaction().replace(R.id.frame_container ,mFragment ).commit();
    }
}

