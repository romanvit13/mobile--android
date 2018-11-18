package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.App;

public class FullScreenPhotoModelImpl implements FullScreenPhotoModel {

    FullScreenPhotoModel.OnFinishedListener mOnFinishedListener;

    public FullScreenPhotoModelImpl(OnFinishedListener onFinishedListener) {
        mOnFinishedListener = onFinishedListener;
    }

    @Override
    public void getCat() {
        mOnFinishedListener.setCat(App.getCatEntity());
    }
}
