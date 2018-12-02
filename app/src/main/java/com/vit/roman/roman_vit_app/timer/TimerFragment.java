package com.vit.roman.roman_vit_app.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.catslist.CatsListFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerFragment extends Fragment implements TimerView, View.OnClickListener{
    @BindView(R.id.progressBarCircle)
    ProgressBar mProgressBar;
    @BindView(R.id.textViewTimer)
    TextView mTextViewTime;
    @BindView(R.id.button_start_timer)
    Button mButtonStartTimer;
    CountDownTimer mCountDownTimer;
    TimerPresenter mPresenter;

    private long timeCountInMilliSeconds = 60000;
    private TimerStatus timerStatus = TimerStatus.STOPPED;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.timer_fragment, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mPresenter.onCreate();
        mButtonStartTimer.setOnClickListener(this);
        return view;
    }

    public void setProgressbarValues() {
        mProgressBar.setMax((int) timeCountInMilliSeconds / 1000);
        mProgressBar.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    public void setTimerValue(int time) {
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    @SuppressLint("DefaultLocale")
    private String hmsTimeFormatter(long milliSeconds) {

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.
                        toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.
                        toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }

    private void setCatsFragment() {
        if (getActivity() == null) return;
            ((MainActivity) getActivity()).setFragment(CatsListFragment.newInstance());
    }

    public void onClick(View view) {
        mPresenter.onButtonTimerActionPressed(timerStatus);
    }

    private void createPresenter() {
        mPresenter = new TimerPresenterImpl(this);
    }

    @Override
    public void displayTimer() {
        mCountDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTextViewTime.setText(hmsTimeFormatter(millisUntilFinished));
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mCountDownTimer.cancel();
                setCatsFragment();
            }
        };
    }

    @Override
    public void startTimerCount() {
        setTimerValue(1);
        setProgressbarValues();
        mCountDownTimer.start();
        timerStatus = TimerStatus.STARTED;
        mButtonStartTimer.setText(R.string.button_timer_stop);
    }

    @Override
    public void stopTimerCount() {
        timerStatus = TimerStatus.STOPPED;
        mCountDownTimer.cancel();
        mButtonStartTimer.setText(R.string.button_timer_start);
    }

    public enum TimerStatus {
        STARTED,
        STOPPED
    }

    public static TimerFragment newInstance() {
        return new TimerFragment();
    }
}
