package com.vit.roman.roman_vit_app.catslist;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class CatsListPresenterImpl implements CatsListPresenter, CatsListModel.OnFinishedListener {

    private CatsListView view;
    private CatsListModel model;

    public CatsListPresenterImpl(CatsListView view) {
        this.view = view;
        this.model = new CatsListModelImpl();
    }

    @Override
    public void onFinished(List<CatEntity> resultCatsArrayList) {
        if (view != null) {
            view.setDataToRecyclerView(resultCatsArrayList);

        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null)
            view.onResponseFailure(t);
    }

    @Override
    public void requestDataFromServer() {
        model.getCatsArrayList(this);
    }
}
