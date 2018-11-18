package com.vit.roman.roman_vit_app.presenter;

import android.content.Context;

import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.model.ExpandedModel;
import com.vit.roman.roman_vit_app.model.ExpandedModelImpl;
import com.vit.roman.roman_vit_app.view.ExpandedView;

public class ExpandedPresenterImpl implements ExpandedPresenter, ExpandedModel.OnFinishedListener {

    private ExpandedView view;
    private ExpandedModel model;


    public ExpandedPresenterImpl(ExpandedView view, Context context) {
        this.view = view;
        this.model = new ExpandedModelImpl(this, context);
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
}
