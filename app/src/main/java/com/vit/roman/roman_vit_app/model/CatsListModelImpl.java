package com.vit.roman.roman_vit_app.model;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatsListModelImpl implements CatsListModel {

    @Override
    public void getCatsArrayList(final OnFinishedListener onFinishedListener, final boolean isChange) {
        Call<List<CatEntity>> call = App.getApi().imagesOfCats();
        call.enqueue(new Callback<List<CatEntity>>() {
            @Override
            public void onResponse(Call<List<CatEntity>> call,
                                   Response<List<CatEntity>> response) {
                List<CatEntity> resultCats = response.body();
                onFinishedListener.onFinished(resultCats, isChange);
            }

            @Override
            public void onFailure(Call<List<CatEntity>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}
