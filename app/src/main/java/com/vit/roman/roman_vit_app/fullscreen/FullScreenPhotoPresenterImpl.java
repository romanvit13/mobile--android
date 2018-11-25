package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public class FullScreenPhotoPresenterImpl implements FullScreenPhotoPresenter {

    private FullScreenPhotoModel mModel;
    private FullScreenPhotoView mView;

    FullScreenPhotoPresenterImpl(FullScreenPhotoView view, FullScreenPhotoModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void getCat() {
        mModel.requestCat(new FullScreenPhotoModel.Result() {
            @Override
            public void onSuccess(CatEntity catEntity) {
                mView.displayCat(catEntity);
            }
        });
    }
}
