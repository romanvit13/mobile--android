package com.vit.roman.roman_vit_app.timer;

public class TimerPresenterImpl implements TimerPresenter {

    private TimerView mView;

    TimerPresenterImpl(TimerView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.displayTimer();
    }

    @Override
    public void onButtonTimerActionPressed(TimerFragment.TimerStatus timerStatus) {
        if (timerStatus == TimerFragment.TimerStatus.STARTED) mView.stopTimerCount();
        else mView.startTimerCount();
    }
}
