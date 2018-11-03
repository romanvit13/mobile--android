package com.vit.roman.roman_vit_app.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedActivity extends AppCompatActivity {

    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;
    @BindView(R.id.favourite_action_button)
    Button mFavouriteActionButton;
    private String catImageUrl = "";
    private String catId = "";
    private String[] externalStoragePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String FOLDER_NAME = "cat_images";
    private static final String TAG = "ExpandedActivity";
    private static final String FAVOURITES_PREF = "FAVOURITES_PREF";
    private static final String CAT_ID = "CAT_ID";

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
                saveImageToExternalStorage();
                saveIdToSharedPrefs(catId);
                Toast.makeText(ExpandedActivity.this, R.string.saved, Toast.LENGTH_SHORT)
                        .show();
                break;
        }

    }

    private void saveIdToSharedPrefs(String id) {
        SharedPreferences sharedPreferences = ExpandedActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CAT_ID, id);
        editor.apply();
    }

    private void saveImageToExternalStorage() {
        if (!isExternalStoragePermission()) {
            askForExternalStoragePermission();
        } else {
            if (isExternalStorageWritable()) {
                saveImage(getBitmapFromImageView(mImageView), getAppDirectory(FOLDER_NAME));
            }
        }
    }

    private Bitmap getBitmapFromImageView(ImageView imageView) {
        return ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    private void startFullScreenPhotoIntent() {
        Intent intent = new Intent(ExpandedActivity.this, FullscreenPhotoActivity.class);
        intent.putExtra("IMAGE_URL", catImageUrl);
        startActivity(intent);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("cat_id") && getIntent().hasExtra("cat_image_url")) {
            catId = getIntent().getStringExtra("cat_id");
            catImageUrl = getIntent().getStringExtra("cat_image_url");
            setContent(catId, catImageUrl);
        }
    }

    @SuppressLint("CheckResult")
    private void setContent(String catId, String imageUrl) {
        mTextView.setText(catId);
        RequestOptions glideOptions = new RequestOptions();
        glideOptions.centerCrop();
        Glide.with(this).load(imageUrl).apply(glideOptions).into(mImageView);
    }

    private boolean isExternalStoragePermission() {
        return ContextCompat.checkSelfPermission(ExpandedActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

    }

    private void askForExternalStoragePermission() {
        ActivityCompat.requestPermissions(ExpandedActivity.this, externalStoragePermission, 1);
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void saveImage(Bitmap bitmap, File directory) {
        String fileName = "image-" + catId + ".jpg";
        File photo = new File(directory, fileName);
        OutputStream foStream;
        try {
            foStream = new FileOutputStream(photo);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.flush();
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }

    private File getAppDirectory(String folderName) {
        String externalStoragePath = Environment.getExternalStorageDirectory().toString();
        File appDirectory = new File(externalStoragePath +  "/" + folderName);
        if (!appDirectory.exists())
            appDirectory.mkdirs();
        return appDirectory;
    }
}

