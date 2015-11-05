package com.intership.vasilisfouroulis.saxparserretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.intership.vasilisfouroulis.saxparserretrofit.Adapter.ItemAdapter;
import com.intership.vasilisfouroulis.saxparserretrofit.Model.Channel;


import com.intership.vasilisfouroulis.saxparserretrofit.Model.Item;
import com.intership.vasilisfouroulis.saxparserretrofit.Model.Rss;
import com.intership.vasilisfouroulis.saxparserretrofit.Retrofit.RestClient;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArrayList<Item> mList;
    private ItemAdapter mAdapter;
    private Button pagerButton;
    private Item mItem;
    private LinearLayoutManager mLayoutManager;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecycler = (RecyclerView)findViewById(R.id.recyclerView);
        pagerButton = (Button)findViewById(R.id.pager);
        pagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestClient restClient = new RestClient();
                Callback<Rss> callback = new Callback<Rss>() {
                    @Override
                    public void success(Rss gadgets, Response response) {
                        mList = (ArrayList<Item>)gadgets.getmChannel().getItemList();

                        if(mList != null) {
                            for(int i = 0; i < mList.size();i++) {
                                //mList = mItem.getGadgets();
                                Log.d("billy", "Item : " + String.valueOf(i));
                                Log.d("billy", "Title: " + mList.get(i).getTitle());
                                Log.d("billy", "PubDate: " + mList.get(i).getPubDate());
                                Log.d("billy", "ImageURL: " + mList.get(i).getLink());
                                Log.d("billy","-------------------------------------------------------------------- ");
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("billy",error.getLocalizedMessage());
                    }
                };
                restClient.getApiService().getGadgets(page,callback);
                        page++;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
