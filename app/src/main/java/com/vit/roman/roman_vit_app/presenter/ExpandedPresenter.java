package com.vit.roman.roman_vit_app.presenter;

import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.fragment.FullscreenPhotoFragment;

public interface ExpandedPresenter {
    void getCat();
    void addFavourite(CatEntity catEntity);
    void startFullScreenPhotoFragment(FullscreenPhotoFragment fullscreenPhotoFragment);
}
