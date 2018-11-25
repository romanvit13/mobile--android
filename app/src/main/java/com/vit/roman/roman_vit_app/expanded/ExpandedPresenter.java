package com.vit.roman.roman_vit_app.expanded;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedPresenter {
    void getCat();

    void actionFavourite(CatEntity catEntity);
}
