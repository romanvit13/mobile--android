package com.vit.roman.roman_vit_app.presenter;

import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.fragment.FullscreenPhotoFragment;
import com.vit.roman.roman_vit_app.model.ExpandedModel;
import com.vit.roman.roman_vit_app.model.ExpandedModelImpl;
import com.vit.roman.roman_vit_app.repository.ExpandedRepository;
import com.vit.roman.roman_vit_app.view.ExpandedView;

public class ExpandedPresenterImpl implements ExpandedPresenter, ExpandedModel.OnFinishedListener {

    private ExpandedView view;
    private ExpandedModel model;
    ExpandedRepository repository;

    public ExpandedPresenterImpl(ExpandedView view,
                                ExpandedRepository repository) {
        this.repository = repository;
        this.view = view;
        this.model = new ExpandedModelImpl(repository, this);
    }

    public void onAdd() {
        view.addToFavourite();
    }

    public void onRemove() {
        view.removeFromFavourite();
    }

    @Override
    public void setCat(CatEntity catEntity) {
        view.setCat(catEntity);
    }

    @Override
    public void getCat() {
        model.getCat();
    }

    @Override
    public void addFavourite(CatEntity catEntity) {
        model.addToFavourite(catEntity);
    }

    @Override
    public void startFullScreenPhotoFragment(FullscreenPhotoFragment fullscreenPhotoFragment) {

    }
}
