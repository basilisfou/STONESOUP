package com.intership.vasilisfouroulis.saxparserretrofit.Retrofit;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by vfour_000 on 31/10/2015.
 */
public class RestClient {

    private ApiService apiService;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://thegadgetflow.com")
                .setConverter(new SimpleXMLConverter())
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService(){
        return apiService;
    }
}