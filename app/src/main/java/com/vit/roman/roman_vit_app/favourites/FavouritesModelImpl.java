package com.vit.roman.roman_vit_app.favourites;

import android.content.Context;

import com.google.gson.Gson;
import com.vit.roman.roman_vit_app.Preferences;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavouritesModelImpl implements FavouritesModel {

    Preferences mPrefs;

    public FavouritesModelImpl(Context context) {
        this.mPrefs = new Preferences(context);
    }

    @Override
    public void getListFromSharedPrefs(Result result) {
        List<CatEntity> catEntities = new ArrayList<>();
        Map<String, ?> map = mPrefs.getSharedPrefs().getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            CatEntity cat = new Gson().fromJson(entry.getValue().toString(), CatEntity.class);
            catEntities.add(cat);
        }
        result.onResult(catEntities);
    }
}
