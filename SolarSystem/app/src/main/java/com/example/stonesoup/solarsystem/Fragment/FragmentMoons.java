package com.example.stonesoup.solarsystem.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.stonesoup.solarsystem.Adapter.cardsMoonAdapter;
import com.example.stonesoup.solarsystem.R;

public class FragmentMoons  extends baseFragment {

    private cardsMoonAdapter mAdapter;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cards, container , false);

        /**
         * initialize Recycler View
         */
        mRecycler = (RecyclerView)v.findViewById(R.id.planet_list);
        mRecycler.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mAdapter = new cardsMoonAdapter();
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(mLayoutManger);


        return v;
    }
}

