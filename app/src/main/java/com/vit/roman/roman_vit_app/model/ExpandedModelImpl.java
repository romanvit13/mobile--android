package com.vit.roman.roman_vit_app.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.repository.ExpandedRepository;

public class ExpandedModelImpl implements ExpandedModel{

    ExpandedModel.OnFinishedListener mOnFinishedListener;
    SharedPreferences mPrefs;
    Context mContext;
    ExpandedRepository mExpandedRepository;

    public ExpandedModelImpl(ExpandedRepository expandedRepository,
                             OnFinishedListener onFinishedListener) {
        this.mExpandedRepository = expandedRepository;
        this.mOnFinishedListener = onFinishedListener;
        this.mPrefs = mExpandedRepository.getContext().getSharedPreferences("favourites", Context.MODE_PRIVATE);
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
        Bundle bundle = mExpandedRepository.getFragment().getArguments();
        if (bundle != null) {
            CatEntity catEntity = new Gson().fromJson(bundle.getString("cat_entity"), CatEntity.class);
            mOnFinishedListener.setCat(catEntity);
        }
    }
}
