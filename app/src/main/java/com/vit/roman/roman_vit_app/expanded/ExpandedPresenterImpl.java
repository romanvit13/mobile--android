package com.vit.roman.roman_vit_app.expanded;

import com.vit.roman.roman_vit_app.entity.CatEntity;

public class ExpandedPresenterImpl implements ExpandedPresenter {

    private ExpandedView view;
    private ExpandedModel model;

    ExpandedPresenterImpl(ExpandedView view, ExpandedModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getCat() {
        view.displayCat(model.getCat());
    }

    @Override
    public void actionFavourite(CatEntity catEntity) {
        model.actionFavourite(catEntity, new ExpandedModel.Result() {
                    @Override
                    public void onAdd() {
                        view.displayAddToFavourite();
                    }

                    @Override
                    public void onRemove() {
                        view.displayRemoveFromFavourite();
                    }
                }
        );
    }
}
