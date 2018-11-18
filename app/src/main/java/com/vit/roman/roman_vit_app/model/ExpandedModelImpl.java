package com.vit.roman.roman_vit_app.model;

import android.content.Context;
import android.widget.Toast;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.Preferences;
import com.vit.roman.roman_vit_app.entity.CatEntity;

public class ExpandedModelImpl implements ExpandedModel{

    ExpandedModel.OnFinishedListener mOnFinishedListener;
    Preferences mPreferences;
    Context mContext;

    public ExpandedModelImpl(OnFinishedListener onFinishedListener, Context context) {
        this.mOnFinishedListener = onFinishedListener;
        mContext = context;
        this.mPreferences = new Preferences(context);
    }

    @Override
    public void addToFavourite(CatEntity catEntity) {
        if (!mPreferences.getSharedPrefs().contains(catEntity.getId())) {
            mPreferences.putIntoSharedPrefs(catEntity);
            Toast.makeText(mContext, "Successfully added!", Toast.LENGTH_SHORT).show();
            mOnFinishedListener.onAdd();
        } else {
            mPreferences.rmFromSharedPrefs(catEntity);
            Toast.makeText(mContext, "Removed!", Toast.LENGTH_SHORT).show();
            mOnFinishedListener.onRemove();
        }
    }

    @Override
    public void getCat() {
        mOnFinishedListener.setCat(App.getCatEntity());
    }

}
