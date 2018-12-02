package com.vit.roman.roman_vit_app.timer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.catslist.CatsListFragment;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class TimerFragment extends Fragment implements TimerView {
    private TimerPresenter mTimerPresenter;
    private int timerCurrent = 0;
    @BindView(R.id.timer)
    CountdownView mTimerView;
    @BindView(R.id.button_timer_action)
    Button mButtonTimerAction;
    @BindView(R.id.custom_view)
    CustomView mTimerRoman;

    public static TimerFragment newInstance() {
        return new TimerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_timer, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mTimerPresenter.onCreate();
        setTimerEndListener();
        return view;
    }

    @OnClick({R.id.button_timer_action})
    public void onClick(View v) {
        mTimerPresenter.onViewPressed(v);
    }

    private void createPresenter() {
        TimerModel timerModel = new TimerModelImpl();
        mTimerPresenter = new TimerPresenterImpl(this, timerModel);
    }

    private void setTimerEndListener() {
        mTimerView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                mTimerPresenter.onTimerEnd(cv);
            }
        });
    }

    @Override
    public void displayTimer() {
        int timerDuration = 10000;
        mTimerView.start(timerDuration);
        mTimerRoman.start(timerDuration / 1000);
    }

    @Override
    public void setData(List<CatEntity> catEntities) {

    }

    @Override
    public void showResponseError(Throwable throwable) {

    }

    @Override
    public void startCatFragment(CountdownView cv) {
        if (getContext() == null) return;
        ((MainActivity) getContext()).setFragment(CatsListFragment.newInstance());
    }

    @Override
    public void stopTimer() {
        mTimerView.stop();
        mTimerRoman.stop();
        timerCurrent = mTimerView.getSecond();
        mButtonTimerAction.setText(getString(R.string.button_timer_start));
    }

    @Override
    public void startTimer() {
        mTimerView.start(timerCurrent * 1000);
        mTimerRoman.start(timerCurrent);
        mButtonTimerAction.setText(getString(R.string.button_timer_stop));
    }

    @Override
    public void onButtonTimerActionClicked() {
        if (mButtonTimerAction.getText()==getString(R.string.button_timer_start))
            mTimerPresenter.onButtonTimerActionPressed(true);
        else
            mTimerPresenter.onButtonTimerActionPressed(false);
    }
}
