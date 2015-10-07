package com.example.stonesoup.solarsystem;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stonesoup.solarsystem.Activities.LogIn;
import com.example.stonesoup.solarsystem.Activities.youtube_activity;
import com.example.stonesoup.solarsystem.Fragment.FragmentCards;
import com.example.stonesoup.solarsystem.Fragment.FragmentMoons;
import com.example.stonesoup.solarsystem.Models.Planets;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/*
******************************
*  8         8    888888888  *
*   8       8     8          *
*    8     8      888888888  *
*     8   8       8          *
*      8 8        8          *
*       8     .   8         .*
******************************
* vassilis Fouroulis
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar, mBottomBar; //The action Bar
    private Fragment mFragment;
    private FragmentManager mFragmentManager;
    private static TextView mTitles;
    private ImageButton PlanetCards, MoonCareds , youTube, signOut;
    private ArrayList<Planets> mListPlanets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListPlanets = new ArrayList<>(); // initialize Planet List
        /**
         * vf : Toolbar customization
         */
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //disable toolbar title - override from text box
        mTitles = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mTitles.setText("Home");
        getSupportActionBar().setDisplayShowTitleEnabled(false);//disable toolbar title
        //fetching data
        new fetchData().execute();
        /**
         * vf: Bottom bar customization
         * Toolbar
         */
        mBottomBar = (Toolbar)findViewById(R.id.bottom_bar);

        /**
         * Widgets customization
         */
        PlanetCards = (ImageButton)findViewById(R.id.planet_cards);
        MoonCareds = (ImageButton)findViewById(R.id.moon_cards);
        youTube = (ImageButton)findViewById(R.id.youtube);
        signOut = (ImageButton)findViewById(R.id.sign_out);

        PlanetCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * To planet Cards - learn about planets - transfering the List of planets or the List of moons
                 */
                mFragmentManager = getFragmentManager();
                mFragment = mFragmentManager.findFragmentById(R.id.frame_container);
                mFragment = new FragmentCards();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("list_planets",mListPlanets);
                mFragment.setArguments(bundle);
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
                Intent intent = new Intent(MainActivity.this, youtube_activity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * Sign out from the Current user
                 */
                ParseUser.logOut();
                Toast toast = Toast.makeText(getApplicationContext(),"GoodBuy :( see you next time" , Toast.LENGTH_LONG);
                Intent intent = new Intent(MainActivity.this , LogIn.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    /**
     * setting the TextView Title of the Tool Bar
     * */
    public static void setmTitles(String mTitles) {
        MainActivity.mTitles.setText(mTitles);
    }

    private class fetchData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            //Taking the object - Table Patients
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Planets");
            //fetching the records
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> planetlist, ParseException e) {

                    //if there are records
                    if (e == null) {
                        for (ParseObject planetParse : planetlist) {

                            //a new Patient
                            Planets planet = new Planets(planetParse.getString("PlanetName"), planetParse.getString("Description"),
                                    planetParse.getString("Mass"), planetParse.getDouble("gravity"), planetParse.getDouble("radius"),
                                    planetParse.getLong("DistanceFromSun"), planetParse.getString("LengthOfDate"),
                                    planetParse.getString("typeOfPlanet"), planetParse.getString("Age"),planetParse.getParseFile("Image"));
                            //Log.d("billy", patient.getFullName());
                            mListPlanets.add(planet);
                        }
                    } else {

                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }
}
