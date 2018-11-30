package com.vit.roman.roman_vit_app.timer;

import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimerModelImpl implements TimerModel {
    @Override
    public void makeCallToServer(final Result result) {
        Call<List<CatEntity>> call = App.getApi().imagesOfCats();
        call.enqueue(new Callback<List<CatEntity>>() {
            @Override
            public void onResponse(Call<List<CatEntity>> call,
                                   Response<List<CatEntity>> response) {
                List<CatEntity> resultCats = response.body();
                result.onSuccess(resultCats);
            }

            @Override
            public void onFailure(Call<List<CatEntity>> call, Throwable t) {
                result.onFailure(t);
            }
        });
    }
}
