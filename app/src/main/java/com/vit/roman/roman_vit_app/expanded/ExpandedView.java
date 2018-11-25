package com.vit.roman.roman_vit_app.expanded;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedView {
    void displayAddToFavourite();

    void displayRemoveFromFavourite();

    void displayCat(CatEntity catEntity);
}
