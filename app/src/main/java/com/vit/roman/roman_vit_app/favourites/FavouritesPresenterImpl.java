package com.vit.roman.roman_vit_app.favourites;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter {

    private FavouritesModel model;
    private FavouritesView view;

    FavouritesPresenterImpl(FavouritesView view, FavouritesModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getCats() {
        model.getListFromSharedPrefs(new FavouritesModel.Result() {
            @Override
            public void onResult(List<CatEntity> catEntities) {
                view.setDataToRecyclerView(catEntities);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onResponseFailure(throwable);
            }
        });
    }
}
