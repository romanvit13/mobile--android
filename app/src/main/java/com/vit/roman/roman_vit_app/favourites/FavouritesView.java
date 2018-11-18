package com.vit.roman.roman_vit_app.favourites;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface FavouritesView {
    void setDataToRecyclerView(List<CatEntity> catsArrayList);
    void onResponseFailure(Throwable throwable);
}
