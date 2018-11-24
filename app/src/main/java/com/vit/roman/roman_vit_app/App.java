package com.vit.roman.roman_vit_app;

import android.app.Application;
import android.content.res.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CatInterface catInterface;
    public static CatInterface getApi() {
        return catInterface;
    }

    @Override
    public void onCreate() {
        initRetrofit();
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        catInterface = retrofit.create(CatInterface.class);
    }
}
