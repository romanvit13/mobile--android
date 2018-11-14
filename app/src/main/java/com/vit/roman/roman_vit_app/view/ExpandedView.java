package com.vit.roman.roman_vit_app.view;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedView {
    void addToFavourite();
    void removeFromFavourite();
    void setCat(CatEntity catEntity);
    void onPhotoClick();
}
