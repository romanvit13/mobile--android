package com.vit.roman.roman_vit_app.expanded;

import android.content.Context;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.Preferences;
import com.vit.roman.roman_vit_app.entity.CatEntity;

public class ExpandedModelImpl implements ExpandedModel {

    private final Preferences mPreferences;

    ExpandedModelImpl(Context context) {
        this.mPreferences = new Preferences(context);
    }

    @Override
    public CatEntity getCat() {
        return App.getCatEntity();
    }

    @Override
    public void actionFavourite(CatEntity catEntity, Result result) {
        if (!mPreferences.getSharedPrefs().contains(catEntity.getId())) {
            addFavourite(catEntity);
            result.onAdd();
        } else {
            rmFavourite(catEntity);
            result.onRemove();
        }
    }

    @Override
    public void addFavourite(CatEntity catEntity) {
        mPreferences.putIntoSharedPrefs(catEntity);
    }

    @Override
    public void rmFavourite(CatEntity catEntity) {
        mPreferences.rmFromSharedPrefs(catEntity);
    }

}
