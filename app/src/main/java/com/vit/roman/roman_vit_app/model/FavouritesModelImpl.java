package com.vit.roman.roman_vit_app.model;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vit.roman.roman_vit_app.Preferences;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavouritesModelImpl implements FavouritesModel {

    FavouritesModel.OnLoadListener mOnLoadListener;
    Preferences mPrefs;
    Context mContext;

    public FavouritesModelImpl(FavouritesModel.OnLoadListener onLoadListener, Context context) {
        this.mOnLoadListener = onLoadListener;
        this.mPrefs = new Preferences(context);
        mContext = context;
    }

    @Override
    public void getList() {
        List<CatEntity> catEntities = new ArrayList<>();
        Map<String, ?> map = mPrefs.getSharedPrefs().getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            CatEntity cat = new Gson().fromJson(entry.getValue().toString(), CatEntity.class);
            catEntities.add(cat);
            Toast.makeText(mContext, cat.getId(), Toast.LENGTH_SHORT).show();
        }
        mOnLoadListener.onSuccess(catEntities);
    }
}
