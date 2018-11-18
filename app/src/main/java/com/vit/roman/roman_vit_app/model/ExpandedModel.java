package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public interface ExpandedModel {
    interface OnFinishedListener {
        void onAdd();
        void onRemove();
        void setCat(CatEntity catEntity);
    }

    void addToFavourite(CatEntity catEntity);
    void getCat();
}
