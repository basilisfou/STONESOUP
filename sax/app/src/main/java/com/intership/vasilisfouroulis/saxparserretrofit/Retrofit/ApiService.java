package com.intership.vasilisfouroulis.saxparserretrofit.Retrofit;
import com.intership.vasilisfouroulis.saxparserretrofit.Model.Rss;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by vfour_000 on 31/10/2015.
 */
public interface ApiService {

    @GET("/?feed=single_gallery_feed")
    void getGadgets(@Query("paged")int page, Callback<Rss> gadgetsCallback);
}
