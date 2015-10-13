package com.example.stonesoup.solarsystem.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stonesoup.solarsystem.Models.Moons;
import com.example.stonesoup.solarsystem.R;

import java.util.ArrayList;


public class cardsMoonAdapter extends RecyclerView.Adapter<cardsMoonAdapter.viewholder> {

    private ArrayList<Moons> moonList;
    private byte[] mImagebytes;
    private Bitmap bitmap;
    private static OnItemClickListener Listener;
    class viewholder extends RecyclerView.ViewHolder{
        private ImageView moonImage;
        private TextView gravity , mass, radius, Name,parent;
        private Button mButton;
        viewholder(View Itemview){
            super(Itemview);
            moonImage = (ImageView)Itemview.findViewById(R.id.moon_pic);
            gravity = (TextView)Itemview.findViewById(R.id.gravity_moon);
            mass = (TextView)Itemview.findViewById(R.id.mass_moon);
            radius = (TextView)Itemview.findViewById(R.id.radius_moon);
            Name = (TextView)Itemview.findViewById(R.id.moon_name);
            parent = (TextView)Itemview.findViewById(R.id.planet_moon);
            mButton = (Button)Itemview.findViewById(R.id.learnMoreMoon);
        }
    }

    public interface OnItemClickListener{

        void itemClick(int position, Moons moon);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.Listener = listener;
    }

    public cardsMoonAdapter(ArrayList<Moons> moonList){
        this.moonList = moonList;
    }
    @Override
    public cardsMoonAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_moon, parent, false);
        viewholder commentViewHolder = new viewholder(v);

        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(cardsMoonAdapter.viewholder holder,final  int position) {

        final Moons moon = moonList.get(position);
        holder.parent.setText(moon.getMotherPlanet());
        holder.gravity.setText(String.valueOf(moon.getGravity()));
        holder.mass.setText(String.valueOf(moon.getMass()));
        holder.Name.setText(moon.getName());
        holder.radius.setText(String.valueOf(moon.getRadious()));
        mImagebytes = moon.getData();
        //decoding the Image
        bitmap = BitmapFactory.decodeByteArray(mImagebytes, 0, mImagebytes.length);
        holder.moonImage.setImageBitmap(bitmap);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Listener != null)
                    Listener.itemClick(position, moon);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moonList.size();
    }
}

