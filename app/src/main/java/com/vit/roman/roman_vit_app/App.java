package com.vit.roman.roman_vit_app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CatEntity mCatEntity;
    private static CatInterface catInterface;
    private static Context mContext;

    public static Application getApplication() {
        return mApplication;
    }

    private static Application mApplication;
    private static SharedPreferences mSharedPrefs;
    public static CatInterface getApi() {
        return catInterface;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
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

    public static Context getAppContext() {
        return App.mContext;
    }

    public static CatEntity getmCatEntity() {
        return mCatEntity;
    }

    public static void setmCatEntity(CatEntity mCatEntity) {
        App.mCatEntity = mCatEntity;
    }

    public static SharedPreferences getmSharedPrefs() {
        return getAppContext().getSharedPreferences("favourites", Context.MODE_PRIVATE);
    }
}
