package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface FullScreenPhotoModel {
    interface OnFinishedListener {
        void onFinish(CatEntity catEntity);
    }
    void requestCat();
}
