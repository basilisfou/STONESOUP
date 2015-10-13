package com.example.stonesoup.solarsystem.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.stonesoup.solarsystem.Adapter.cardsAdapter;
import com.example.stonesoup.solarsystem.MainActivity;
import com.example.stonesoup.solarsystem.Models.Planet;
import com.example.stonesoup.solarsystem.Models.Planets;
import com.example.stonesoup.solarsystem.R;
import com.parse.ParseUser;

import java.util.ArrayList;

public class FragmentCards extends baseFragment {

    private cardsAdapter mAdapter;
    private ArrayList<Planets> mPlanets;
    private Context mContext;


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        savedInstanceState = getArguments();
        if(savedInstanceState != null)
            mPlanets = savedInstanceState.getParcelableArrayList("list_planets");
        mContext = getActivity();
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cards, container , false);
        /**
         * initialize Recycler View
         */
        mRecycler = (RecyclerView)v.findViewById(R.id.planet_list);
        mRecycler.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mContext = getActivity();
        mAdapter = new cardsAdapter(mPlanets,mContext);
        mAdapter.setOnItemClickListener(new cardsAdapter.OnItemClickListener() {

            @Override
            public void onItemClick( int position, Planets planet) {
                onListViewItemClick(position,planet);
            }
        });
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(mLayoutManger);


        return v;
    }

    public void onListViewItemClick(int position, Planets planet){
        mFragmentManager = getFragmentManager();
        mFragment = mFragmentManager.findFragmentById(R.id.frame_container);
        mFragment = new FragmentDescription();
        MainActivity.setmTitles(planet.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", planet);
        mFragment.setArguments(bundle);
        //To the fragment
        mFragmentManager.beginTransaction().replace(R.id.frame_container ,mFragment ).addToBackStack(null).commit();
    }


}
