package com.intership.vasilisfouroulis.saxparserretrofit.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.intership.vasilisfouroulis.saxparserretrofit.Model.Item;
import com.intership.vasilisfouroulis.saxparserretrofit.R;

import java.util.ArrayList;

/**
 * Created by vfour_000 on 31/10/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MainHolder>{
    private ArrayList<Item> mList;
    private View view;
    private Context mContext;

    public ItemAdapter(ArrayList<Item> pList, Context pContext){
        this.mContext = pContext;
        this.mList = pList;
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView imageView;
        private ProgressBar progressBar;
        public MainHolder(View itemView){
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.gadget_title);
            imageView =  (ImageView)itemView.findViewById(R.id.gadget_image);
            progressBar = (ProgressBar)itemView.findViewById(R.id.pbLoadingImage);
        }
    }

    @Override
    public ItemAdapter.MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MainHolder mainHolder = new MainHolder(view);
        return mainHolder;
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.MainHolder holder, int position) {
        Item gadget = mList.get(position);

       // holder.title.setText(gadget.getTitle());
       // Log.d("billy",gadget.getTitle());
        /*Picasso.with(mContext).load(gadget.getLinks().get(0)).into(holder.imageView, new Callback() {

            @Override
            public void onError() {
            }

            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

        });*/

        //testing :
        //for (int i = 0 ; i < gadget.getLinks().size();i++)
         //   Log.d("billy","Image ("+String.valueOf(i) +"): "+gadget.getLinks().get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
