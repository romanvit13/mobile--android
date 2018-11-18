package com.vit.roman.roman_vit_app.presenter;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedPresenter {
    void getCat();
    void addFavourite(CatEntity catEntity);
}
