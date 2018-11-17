package com.vit.roman.roman_vit_app.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavouritesModelImpl implements FavouritesModel {

    FavouritesModel.OnLoadListener mOnLoadListener;
    SharedPreferences mPrefs;

    public FavouritesModelImpl(FavouritesModel.OnLoadListener onLoadListener) {
        this.mOnLoadListener = onLoadListener;
        this.mPrefs = App.getmSharedPrefs();
    }

    @Override
    public void getList() {
        SharedPreferences mPrefs = App.getmSharedPrefs();
        List<CatEntity> catEntities = new ArrayList<>();
        Map<String, ?> map = mPrefs.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            CatEntity cat = new Gson().fromJson(entry.getValue().toString(), CatEntity.class);
            catEntities.add(cat);
        }
        mOnLoadListener.onSuccess(catEntities);
    }
}
