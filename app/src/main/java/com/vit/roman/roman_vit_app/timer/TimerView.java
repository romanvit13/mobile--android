package com.vit.roman.roman_vit_app.timer;

import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public interface TimerView {
    void displayTimer();
    void setData(List<CatEntity> catEntities);
    void showResponseError(Throwable throwable);
    void startCatFragment(CountdownView cv);
    void stopTimer();
    void startTimer();
    void onButtonTimerActionClicked();
}
