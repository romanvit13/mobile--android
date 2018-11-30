package com.vit.roman.roman_vit_app.startscreen;

import android.view.View;

import com.vit.roman.roman_vit_app.R;

public class StartScreenPresenterImpl implements StartScreenPresenter {

    private StartScreenView mView;

    StartScreenPresenterImpl(StartScreenView view) {
        mView = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onButtonPressed(View view) {
        switch (view.getId()) {
            case R.id.button_get_data:
                startFragment();
                break;
        }
    }

    private void startFragment() {
        mView.startTimerFragment();
    }
}
