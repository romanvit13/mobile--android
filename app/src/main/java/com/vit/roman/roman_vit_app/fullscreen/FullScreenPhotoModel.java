package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface FullScreenPhotoModel {
    interface Result {
        void onSuccess(CatEntity catEntity);
    }
    void requestCat(Result result);
}
