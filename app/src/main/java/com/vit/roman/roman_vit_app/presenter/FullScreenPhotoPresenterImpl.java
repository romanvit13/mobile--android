package com.vit.roman.roman_vit_app.presenter;

import com.vit.roman.roman_vit_app.model.FullScreenPhotoModel;
import com.vit.roman.roman_vit_app.model.FullScreenPhotoModelImpl;
import com.vit.roman.roman_vit_app.repository.FullScreenPhotoRepository;
import com.vit.roman.roman_vit_app.view.FullScreenPhotoView;

public class FullScreenPhotoPresenterImpl implements FullScreenPhotoPresenter,
        FullScreenPhotoModel.OnFinishedListener {

    FullScreenPhotoModel mModel;
    FullScreenPhotoView mView;
    FullScreenPhotoRepository mRepository;

    FullScreenPhotoPresenterImpl(FullScreenPhotoView view,
                                 FullScreenPhotoRepository repository) {
        mView = view;
        mRepository = repository;
        mModel = new FullScreenPhotoModelImpl(repository, this);
    }

    @Override
    public void getCatUrl() {
        mModel.getCatUrl();
    }

    @Override
    public void setCatUrl(String catUrl) {
        mView.setCatUrl(catUrl);
    }
}