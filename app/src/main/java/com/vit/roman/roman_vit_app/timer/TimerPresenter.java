package com.vit.roman.roman_vit_app.timer;

import android.view.View;

public interface TimerPresenter {
    void onCreate();
    void onViewPressed(View view);
    void onButtonTimerActionPressed(boolean isStart);
}
