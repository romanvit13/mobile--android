package com.vit.roman.roman_vit_app.expanded;

import android.content.Context;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.prefs.Preferences;

public class ExpandedModelImpl implements ExpandedModel {

    private final Preferences mPreferences;

    ExpandedModelImpl(Context context) {
        this.mPreferences = new Preferences(context);
    }

    @Override
    public CatEntity getCat() {
        return MainActivity.getCatEntity();
    }

    @Override
    public void doActionFavourite(CatEntity catEntity, Result result) {
        if (mPreferences.getSharedPrefs().contains(catEntity.getId())) {
            rmFavourite(catEntity);
            result.onRemove();
        } else {
            addFavourite(catEntity);
            result.onAdd();
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
