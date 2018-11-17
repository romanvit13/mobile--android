package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.fragment.FullscreenPhotoFragment;

public class FullScreenPhotoModelImpl implements FullScreenPhotoModel {

    FullScreenPhotoModel.OnFinishedListener mOnFinishedListener;
    FullscreenPhotoFragment mFullscreenPhotoFragment;

    public FullScreenPhotoModelImpl(OnFinishedListener onFinishedListener) {
        mOnFinishedListener = onFinishedListener;
    }

    @Override
    public void getCatUrl() {
        mOnFinishedListener.setCatUrl(App.getmCatEntity().getUrl());
    }

    @Override
    public void setBundleToFragment() {

    }


}
