package com.vit.roman.roman_vit_app.timer;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface TimerModel {
    interface Result {
        void onSuccess(List<CatEntity> catEntities);
        void onFailure(Throwable throwable);
    }
    void makeCallToServer(Result result);
}
