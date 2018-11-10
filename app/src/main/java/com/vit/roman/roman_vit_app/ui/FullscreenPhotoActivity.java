package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoActivity extends AppCompatActivity {
    private static final String IMAGE_URL = "IMAGE_URL";
    PhotoView mPhotoView;

    public static Intent getStartIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, FullscreenPhotoActivity.class);
        intent.putExtra(IMAGE_URL, imageUrl);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fullscreen);
        mPhotoView = findViewById(R.id.photo_view);
        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(this).load(imageUrl).apply(glideOptions.centerCrop()).into(mPhotoView);
    }
}
