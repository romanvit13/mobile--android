package com.vit.roman.roman_vit_app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import uk.co.senab.photoview.PhotoView;

public class FullscreenPhotoActivity extends AppCompatActivity {
    private static final String IMAGE_URL = "IMAGE_URL";
    PhotoView photoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fullscreen);
        photoView = findViewById(R.id.fullscreen_content);
        //ImageView imageView = findViewById(R.id.ok);
        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        RequestOptions glideOptions = new RequestOptions();
        glideOptions.centerCrop();
        Glide.with(this).load(imageUrl).apply(glideOptions).into(photoView);
    }
}
