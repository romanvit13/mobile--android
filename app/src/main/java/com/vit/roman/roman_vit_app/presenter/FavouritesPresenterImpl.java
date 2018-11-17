package com.vit.roman.roman_vit_app.presenter;

import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.model.FavouritesModel;
import com.vit.roman.roman_vit_app.model.FavouritesModelImpl;
import com.vit.roman.roman_vit_app.view.FavouritesView;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter, FavouritesModel.OnLoadListener {

    FavouritesModel model;
    FavouritesView view;

    public FavouritesPresenterImpl(FavouritesView view) {
        this.view = view;
        this.model = new FavouritesModelImpl(this);
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
