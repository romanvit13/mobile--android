package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface FullScreenPhotoModel {
    interface OnFinishedListener {
        void setCat(CatEntity catEntity);
    }
    void getCat();
}
