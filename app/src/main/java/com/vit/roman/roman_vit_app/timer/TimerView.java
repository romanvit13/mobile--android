package com.vit.roman.roman_vit_app.timer;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

public interface TimerView {
    void displayTimer();
    void setData(List<CatEntity> catEntities);
    void showResponseError(Throwable throwable);
    void stopTimer();
    void startTimer();
    void onButtonTimerActionClicked();
}
