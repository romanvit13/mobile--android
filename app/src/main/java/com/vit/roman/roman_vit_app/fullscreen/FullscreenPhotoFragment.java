package com.vit.roman.roman_vit_app.fullscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoFragment extends Fragment implements FullScreenPhotoView {

    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    FullScreenPhotoPresenter mFullScreenPhotoPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_fullscreen,
                container, false);
        ButterKnife.bind(this, view);
        createPresenter();
        mFullScreenPhotoPresenter.getCat();
        return view;
    }

    private void setPhoto(CatEntity catEntity) {
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(this).load(catEntity.getUrl())
                .apply(glideOptions.centerCrop()).into(mPhotoView);
    }

    @Override
    public void displayCat(CatEntity catEntity) {
        setPhoto(catEntity);
    }

    private void createPresenter() {
        FullScreenPhotoModel model = new FullScreenPhotoModelImpl();
        mFullScreenPhotoPresenter = new FullScreenPhotoPresenterImpl(this, model);
    }
}
