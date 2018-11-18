package com.vit.roman.roman_vit_app.favourites;

import android.content.Context;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter, FavouritesModel.OnLoadListener {

    FavouritesModel model;
    FavouritesView view;

    public FavouritesPresenterImpl(FavouritesView view, Context context) {
        this.view = view;
        this.model = new FavouritesModelImpl(this, context);
    }

    @Override
    public void onSuccess(List<CatEntity> catEntityList) {
        view.setDataToRecyclerView(catEntityList);
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);
    }

    @Override
    public void getCats() {
        model.getList();
    }
}
