package com.example.vasilis.myapplication;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vasilis on 22/6/2015.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ListItemViewHolder> {

    private List<Item> items;
    /*
    Constructor
     */
    public Adapter(List<Item> pList) {

        this.items = pList;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder (ViewGroup pViewGroup, int viewType) {
        View lViewItem = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.items,pViewGroup, false);

        return new ListItemViewHolder(lViewItem);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder viewHolder , int position ) {

        //getting the child from the list with fixed position
        Item lItem = items.get(position);

        //reference to the views
        viewHolder.mTextView.setText(lItem.getName());
        viewHolder.mImageView.setImageResource(lItem.getIcon());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /*
    inner class : View Holder , holds the views
     */
    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        //constructor of the inner class
        public ListItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_item);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
