package com.example.stonesoup.solarsystem.Fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stonesoup.solarsystem.Models.Moons;
import com.example.stonesoup.solarsystem.R;

/**
 * Created by vfour_000 on 10/10/2015.
 */
public class FragmentDescriptionMoon extends Fragment {

    private View view;
    private Moons object01;
    private ImageView image;
    private TextView description;
    private byte[] mImagebytes;
    private Bitmap bitmap;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        savedInstanceState = getArguments();

        if(savedInstanceState != null) {
            object01 =  savedInstanceState.getParcelable("item");
        }
        view = inflater.inflate(R.layout.fragment_description, container , false);
        image = (ImageView)view.findViewById(R.id.discription_image);
        description = (TextView)view.findViewById(R.id.description);
        //decoding the Image
        mImagebytes = object01.getData();
        bitmap = BitmapFactory.decodeByteArray(mImagebytes, 0, mImagebytes.length);
        image.setImageBitmap(bitmap);
        description.setText(object01.getDescription());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //the button search is setting to visible
        menu.findItem(android.R.id.home).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar actions click
        switch (item.getItemId()) {
            case android.R.id.home:
                //go to the previous fragment
                getFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
