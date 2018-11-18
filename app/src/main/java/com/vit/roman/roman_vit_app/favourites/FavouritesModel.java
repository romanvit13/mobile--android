package com.vit.roman.roman_vit_app.favourites;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface FavouritesModel {
    interface OnLoadListener {
        void onSuccess(List<CatEntity> catEntityList);
        void onFailure(Throwable e);
    }
    void getList();
}
