package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface CatsListModel {
    interface OnFinishedListener {
        void onFinished(List<CatEntity> catsArrayList, boolean isChange);

        void onFailure(Throwable t);
    }

    void getHitArrayList(CatsListModel.OnFinishedListener onFinishedListener,
                         boolean isChange);
}
