package com.vit.roman.roman_vit_app;

import android.app.Application;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CatEntity mCatEntity;
    private static CatInterface catInterface;

    public static CatInterface getApi() {
        return catInterface;
    }

    public static CatEntity getCatEntity() {
        return mCatEntity;
    }

    public static void setCatEntity(CatEntity mCatEntity) {
        App.mCatEntity = mCatEntity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        catInterface = retrofit.create(CatInterface.class);
    }

}
