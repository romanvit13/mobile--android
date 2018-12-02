package com.vit.roman.roman_vit_app.timer;

import android.view.View;

import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;

public class TimerPresenterImpl implements TimerPresenter {

    private TimerView mView;
    private TimerModel mModel;

    TimerPresenterImpl(TimerView view, TimerModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreate() {
        startTimerCount();
    }

    @Override
    public void onViewPressed(View view) {
        switch (view.getId()) {
            case R.id.button_timer_action:
                mView.onButtonTimerActionClicked();
                break;
        }
    }

    @Override
    public void onButtonTimerActionPressed(boolean isStart) {
        if (!isStart) mView.stopTimer(); else mView.startTimer();
    }

    @Override
    public void onTimerEnd(CountdownView cv) {
        mView.stopTimer();
        mView.startCatFragment(cv);
    }

    private void getImage() {
        mModel.makeCallToServer(new TimerModel.Result() {
            @Override
            public void onSuccess(List<CatEntity> catEntities) {
                mView.setData(catEntities);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.showResponseError(throwable);
            }
        });
    }

    private void startTimerCount() {
        mView.displayTimer();
    }
}
