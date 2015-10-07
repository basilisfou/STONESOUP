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
        mAdapter = new cardsAdapter(mPlanets);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(mLayoutManger);


        return v;
    }

    @Override
    public void setImageButtonClicks(){
        PlanetCards = (ImageButton)v.findViewById(R.id.planet_cards);
        MoonCareds = (ImageButton)v.findViewById(R.id.moon_cards);
        youTube = (ImageButton)v.findViewById(R.id.youtube);
        signOut = (ImageButton)v.findViewById(R.id.sign_out);

        PlanetCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * To planet Cards - learn about planets
                 */
                mFragmentManager = getFragmentManager();
                mFragment = mFragmentManager.findFragmentById(R.id.frame_container);
                mFragment = new FragmentCards();
                //To the fragment
                mFragmentManager.beginTransaction().replace(R.id.frame_container ,mFragment ).commit();
            }
        });

        MoonCareds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Card moon - fragmentCard
                 */
                mFragmentManager = getFragmentManager();
                mFragment = mFragmentManager.findFragmentById(R.id.frame_container);
                mFragment = new FragmentMoons();
                //To the fragment
                mFragmentManager.beginTransaction().replace(R.id.frame_container ,mFragment ).commit();
            }
        });

        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * Sign out from the Current user
                 */
                ParseUser.logOut();
                Toast toast = Toast.makeText(getActivity(),"GoodBuy :( see you next time" , Toast.LENGTH_LONG);
            }
        });
    }
}
