package com.vit.roman.roman_vit_app.favourites;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter {

    private FavouritesModel mModel;
    private FavouritesView mView;

    FavouritesPresenterImpl(FavouritesView view, FavouritesModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreate() {
        getCats();
    }

    public void getCats() {
        mModel.getListFromSharedPrefs(new FavouritesModel.Result() {
            @Override
            public void onResult(List<CatEntity> catEntities) {
                mView.setDataToRecyclerView(catEntities);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.onResponseFailure(throwable);
            }
        });
    }


}
