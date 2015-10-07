package com.example.stonesoup.solarsystem.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class cardsMoonAdapter extends RecyclerView.Adapter<cardsMoonAdapter.viewholder> {

    class viewholder extends RecyclerView.ViewHolder{

        viewholder(View Itemview){
            super(Itemview);
        }
    }
    @Override
    public cardsMoonAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(cardsMoonAdapter.viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

