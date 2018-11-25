package com.vit.roman.roman_vit_app.catslist;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public class CatsListPresenterImpl implements CatsListPresenter {

    private CatsListView mView;
    private CatsListModel mModel;

    CatsListPresenterImpl(CatsListView view, CatsListModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreate() {
        requestDataFromServer();
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    private void requestDataFromServer() {
        mModel.getCatsArrayList(false, new CatsListModel.OnFinishedListener() {
            @Override
            public void onFinished(List<CatEntity> catsArrayList) {
                if (mView != null)
                    mView.setDataToRecyclerView(catsArrayList);

            }

            @Override
            public void onFailure(Throwable t) {
                mView.onResponseFailure(t);
            }
        });
    }

    private void refreshData() {
        mModel.getCatsArrayList(true, new CatsListModel.OnFinishedListener() {
            @Override
            public void onFinished(List<CatEntity> catsArrayList) {
                mView.refreshDataInRecyclerView(catsArrayList);
            }

            @Override
            public void onFailure(Throwable t) {
                mView.onResponseFailure(t);
            }
        });
    }
}
