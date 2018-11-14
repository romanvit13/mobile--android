package com.vit.roman.roman_vit_app.model;

import android.os.Bundle;
import android.util.Log;

import com.vit.roman.roman_vit_app.repository.FullScreenPhotoRepository;

import static com.vit.roman.roman_vit_app.ui.MainActivity.PHOTO_URL;

public class FullScreenPhotoModelImpl implements FullScreenPhotoModel {

    FullScreenPhotoRepository mFullScreenPhotoRepository;
    FullScreenPhotoModel.OnFinishedListener mOnFinishedListener;

    public FullScreenPhotoModelImpl(FullScreenPhotoRepository fullScreenPhotoRepository,
                             OnFinishedListener onFinishedListener) {
        mFullScreenPhotoRepository = fullScreenPhotoRepository;
        mOnFinishedListener = onFinishedListener;
    }

    @Override
    public void getCatUrl() {
        Bundle bundle = mFullScreenPhotoRepository.getFragment().getArguments();
        if (bundle != null) {
            String catUrl = bundle.getString(PHOTO_URL);
            mOnFinishedListener.setCatUrl(catUrl);
        } else {
            Log.i("TAG", "Model, bundle is null.");
        }
    }

    @Override
    public void setBundleToFragment() {

    }


}
