package com.vit.roman.roman_vit_app.startscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.timer.TimerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartScreenFragment extends Fragment implements StartScreenView {

    private StartScreenPresenter mPresenter;
    @BindView(R.id.button_get_data)
    Button mButtonGetData;

    public static StartScreenFragment newInstance() {
        return new StartScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_start_screen, container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mPresenter.onCreate();
        return view;
    }

    @OnClick({R.id.button_get_data})
    public void onClick(View v) {
        mPresenter.onButtonPressed(v);
    }

    @Override
    public void startTimerFragment() {
        if (getActivity() == null) return;
        ((MainActivity) getActivity()).setFragment(TimerFragment.newInstance());
    }

    private void createPresenter() {
        mPresenter = new StartScreenPresenterImpl(this);
    }
}
