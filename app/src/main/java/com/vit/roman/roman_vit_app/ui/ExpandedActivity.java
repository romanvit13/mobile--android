package com.vit.roman.roman_vit_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedActivity extends AppCompatActivity {

    private static final String TAG = "ExpandedActivity";
    private String catImageUrl = "";

    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_expanded);
        ButterKnife.bind(this);
        getIncomingIntent();

    }

    @OnClick(R.id.image_view_expanded)
    public void click(View v) {
        Intent intent = new Intent(ExpandedActivity.this, FullscreenPhotoActivity.class);
        intent.putExtra("IMAGE_URL", catImageUrl);
        startActivity(intent);
    }


    private void getIncomingIntent() {
        if (getIntent().hasExtra("cat_id") && getIntent().hasExtra("cat_image_url")) {
            String catId = getIntent().getStringExtra("cat_id");
            catImageUrl = getIntent().getStringExtra("cat_image_url");
            setContent(catId, catImageUrl);
        }
    }

    private void setContent(String catId, String imageUrl) {
        mTextView.setText(catId);
        RequestOptions glideOptions = new RequestOptions();
        glideOptions.centerCrop();
        Glide.with(this).load(imageUrl).apply(glideOptions).into(mImageView);
    }
}
