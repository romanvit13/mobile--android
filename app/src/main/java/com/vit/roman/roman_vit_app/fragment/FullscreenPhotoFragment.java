package com.vit.roman.roman_vit_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoFragment extends Fragment {

    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CatEntity catEntity = App.getmCatEntity();
        View view = inflater.inflate(R.layout.activity_photo_fullscreen, container, false);
        ButterKnife.bind(this, view);
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(this).load(catEntity.getUrl()).apply(glideOptions.centerCrop()).into(mPhotoView);
        return view;
    }
}
