package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.MainActivity;

public class FullScreenPhotoModelImpl implements FullScreenPhotoModel {

    @Override
    public void requestCat(Result result) {
        result.onSuccess(MainActivity.getCatEntity());
    }
}
