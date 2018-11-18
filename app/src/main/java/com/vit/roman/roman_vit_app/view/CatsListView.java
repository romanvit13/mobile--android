package com.vit.roman.roman_vit_app.view;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface CatsListView {
    void setDataToRecyclerView(List<CatEntity> catsArrayList);

    void refreshData(List<CatEntity> catsArrayList);

    void onResponseFailure(Throwable throwable);
}
