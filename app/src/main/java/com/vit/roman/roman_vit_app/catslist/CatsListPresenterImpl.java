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
    public void onFinished(List<CatEntity> resultCatsArrayList, boolean isChange) {
        if (view != null) {
            if (!isChange) {
                view.setDataToRecyclerView(resultCatsArrayList);
            } else {
                view.refreshData(resultCatsArrayList);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view!=null)
            view.onResponseFailure(t);
    }

    @Override
    public void requestDataFromServer(boolean isChange) {
        model.getCatsArrayList(this, isChange);
    }
}
