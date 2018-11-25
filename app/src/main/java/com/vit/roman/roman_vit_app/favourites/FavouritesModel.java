package com.vit.roman.roman_vit_app.favourites;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface FavouritesModel {

    interface Result {
        void onResult(List<CatEntity> catEntities);
        void onFailure(Throwable throwable);
    }
    void getListFromSharedPrefs(Result result);
}
