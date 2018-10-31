package com.vit.roman.roman_vit_app.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedActivity extends AppCompatActivity {

    private static final String TAG = "ExpandedActivity";
    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;
    @BindView(R.id.favourite_action_button)
    Button mFavuriteActionButton;
    private String catImageUrl = "";
    private String[] externalStoragePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_expanded);
        ButterKnife.bind(this);
        getIncomingIntent();
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                startFullScreenPhotoIntent();
                break;
            case R.id.favourite_action_button:
                Toast.makeText(ExpandedActivity.this, "Clicked", Toast.LENGTH_SHORT)
                        .show();
                if (!isExternalStoragePermission())
                    askForExternalStoragePermission();
                else {
                    if (isExternalStorageWritable()) {
                        File path = getPublicAlbumStorageDir("cat_folder");

                    }
                }
                break;
        }

    }

    private void startFullScreenPhotoIntent() {
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

    private boolean isExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(ExpandedActivity.this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED;

    }

    private void askForExternalStoragePermission() {
        ActivityCompat.requestPermissions(ExpandedActivity.this, externalStoragePermission, 0);
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    private void saveImage(Context context, Bitmap bitmap, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
}

