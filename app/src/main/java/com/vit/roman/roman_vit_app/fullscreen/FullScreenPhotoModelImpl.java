package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.App;

public class FullScreenPhotoModelImpl implements FullScreenPhotoModel {

    @Override
    public void requestCat(Result result) {
        result.onSuccess(App.getCatEntity());
    }
}
