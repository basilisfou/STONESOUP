package com.example.stonesoup.solarsystem.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stonesoup.solarsystem.Fragment.FragmentMoons;
import com.example.stonesoup.solarsystem.MainActivity;
import com.example.stonesoup.solarsystem.Models.Planets;
import com.example.stonesoup.solarsystem.R;

import java.util.ArrayList;

/**
 * the adapter of the Recycler view , class where the data are being binding
 */
public class cardsAdapter extends RecyclerView.Adapter<cardsAdapter.viewholder> {
    private ArrayList<Planets> mPlanets;
    private byte[] mImagebytes;
    private Bitmap bitmap;
    private Fragment mFragment;
    private FragmentManager mFragmentManager;
    private Context mContext;
    public static OnItemClickListener listener;// Define listener member variable
    public cardsAdapter(ArrayList<Planets> pPlanets, Context pContext){
        mPlanets = pPlanets;
        mContext = pContext;

    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    /**
     * Defines the listener interface
     */
    public interface OnItemClickListener {
        void onItemClick( int position, Planets pPlanet);
    }

    class viewholder extends RecyclerView.ViewHolder{
        private ImageView planetImage;
        private TextView planetName, planetType, sunDistance, dayLength, radius, gravity, poinetGravity;
        private Button learnMore;
        viewholder(View Itemview){
            super(Itemview);

            planetImage = (ImageView)Itemview.findViewById(R.id.planet_pic);
            planetName =(TextView)Itemview.findViewById(R.id.planet_name);
            planetType = (TextView)Itemview.findViewById(R.id.type_of_planet);
            sunDistance = (TextView)Itemview.findViewById(R.id.distance_sun);
            dayLength =(TextView)Itemview.findViewById(R.id.length_day);
            radius = (TextView)Itemview.findViewById(R.id.radius);
            gravity =(TextView)Itemview.findViewById(R.id.gravity);
            poinetGravity = (TextView)Itemview.findViewById(R.id.a_gravity);
            learnMore = (Button)Itemview.findViewById(R.id.learnMore);
        }
    }
    @Override
    public cardsAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewlayout, parent, false);
        viewholder commentViewHolder = new viewholder(v);

        return commentViewHolder;
    }

    /**
     * where the values of the data base are being connected with the views
     */
    @Override
    public void onBindViewHolder( cardsAdapter.viewholder holder, final int position) {

        final Planets planet = mPlanets.get(position);
        holder.planetName.setText(planet.getName());
        holder.planetType.setText(planet.getTypeOfPlanet());
        holder.sunDistance.setText(String.valueOf(planet.getDistanceFromSun()));
        holder.dayLength.setText(planet.getLengthOfDate());
        holder.radius.setText(String.valueOf(planet.getRadius()));
        holder.gravity.setText(String.valueOf(planet.getGravity()));
        mImagebytes = planet.getData();
        //decoding the Image
        bitmap = BitmapFactory.decodeByteArray(mImagebytes, 0, mImagebytes.length);
        holder.planetImage.setImageBitmap(bitmap);
        holder.learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(position, planet);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlanets.size();
    }
}
