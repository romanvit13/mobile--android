package com.vit.roman.roman_vit_app.fullscreen;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public class FullScreenPhotoPresenterImpl implements FullScreenPhotoPresenter,
        FullScreenPhotoModel.OnFinishedListener {

    FullScreenPhotoModel mModel;
    FullScreenPhotoView mView;

    public FullScreenPhotoPresenterImpl(FullScreenPhotoView view) {
        mView = view;
        mModel = new FullScreenPhotoModelImpl(this);
    }

    @Override
    public void getCat() {
        mModel.requestCat();
    }

    @Override
    public void onFinish(CatEntity catEntity) {
        mView.displayCat(catEntity);
    }
}
