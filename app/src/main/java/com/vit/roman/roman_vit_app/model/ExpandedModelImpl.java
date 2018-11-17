package com.vit.roman.roman_vit_app.model;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.entity.CatEntity;

public class ExpandedModelImpl implements ExpandedModel{

    ExpandedModel.OnFinishedListener mOnFinishedListener;
    SharedPreferences mPrefs;

    public ExpandedModelImpl(OnFinishedListener onFinishedListener) {
        this.mOnFinishedListener = onFinishedListener;
        this.mPrefs = App.getmSharedPrefs();
    }

    @Override
    public void addToFavourite(CatEntity catEntity) {
        if (!mPrefs.contains(catEntity.getId())) {
            mPrefs.edit().putString(catEntity.getId(), new Gson().toJson(catEntity)).apply();
            mOnFinishedListener.onAdd();
        } else {
            mPrefs.edit().remove(catEntity.getId()).apply();
            mOnFinishedListener.onRemove();
        }
    }

    @Override
    public void getCat() {
        mOnFinishedListener.setCat(App.getmCatEntity());
    }

}
