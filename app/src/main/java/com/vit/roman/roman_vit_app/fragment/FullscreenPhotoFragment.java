package com.vit.roman.roman_vit_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoFragment extends Fragment {

    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    public static Fragment getStartFragment(String catImageUrl) {
        FullscreenPhotoFragment fragment = new FullscreenPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, catImageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_fullscreen, container, false);
        ButterKnife.bind(this, view);
        String imageUrl = getArguments().getString(IMAGE_URL);
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(this).load(imageUrl).apply(glideOptions.centerCrop()).into(mPhotoView);
        return view;
    }
}
