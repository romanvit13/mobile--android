package com.vit.roman.roman_vit_app.model;

public interface FullScreenPhotoModel {

    interface OnFinishedListener {
        void setCatUrl(String catUrl);
    }

    void getCatUrl();
    void setBundleToFragment();
}
