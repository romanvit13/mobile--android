package com.vit.roman.roman_vit_app.expanded;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedModel {

    void actionFavourite(CatEntity catEntity, Result result);

    void addFavourite(CatEntity catEntity);

    void rmFavourite(CatEntity catEntity);

    CatEntity getCat();

    interface Result {
        void onAdd();

        void onRemove();
    }
}
