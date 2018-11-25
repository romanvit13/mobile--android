package com.vit.roman.roman_vit_app.catslist;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class CatsListPresenterImpl implements CatsListPresenter {

    private CatsListView view;
    private CatsListModel model;

    CatsListPresenterImpl(CatsListView view) {
        this.view = view;
        this.model = new CatsListModelImpl();
    }

    @Override
    public void requestDataFromServer() {
        model.getCatsArrayList(false, new CatsListModel.OnFinishedListener() {
            @Override
            public void onFinished(List<CatEntity> catsArrayList) {
                if (view != null)
                    view.setDataToRecyclerView(catsArrayList);

            }

            @Override
            public void onFailure(Throwable t) {
                view.onResponseFailure(t);
            }
        });
    }

    @Override
    public void refreshData() {
        model.getCatsArrayList(true, new CatsListModel.OnFinishedListener() {
            @Override
            public void onFinished(List<CatEntity> catsArrayList) {
                view.refreshDataInRecyclerView(catsArrayList);
            }

            @Override
            public void onFailure(Throwable t) {
                view.onResponseFailure(t);
            }
        });
    }
}
