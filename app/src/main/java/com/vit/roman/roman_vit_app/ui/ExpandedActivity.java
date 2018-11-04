package com.vit.roman.roman_vit_app.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
    private static final String ID_LIST = "ID_LIST";
    private boolean favourite = false;
    private ArrayList<String> catIds;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_expanded);
        ButterKnife.bind(this);
        initArrayList();
        getIncomingIntent();
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                startFullScreenPhotoIntent();
                break;
            case R.id.favourite_action_button:

                if (favourite == false) {
                    saveData();
                    mFavouriteActionButton.setText(R.string.favourite_button_action_rm);
                    favourite = true;
                } else {
                    loadData();
                    mFavouriteActionButton.setText(R.string.favourite_button_action_add);
                    favourite = false;
                }
                break;
        }
    }

    private void initArrayList() {
        catIds = getArrayList();
    }

    private ArrayList<String> getArrayList() {
        ArrayList<String> catIds = new ArrayList<>();
        SharedPreferences sharedPreferences = ExpandedActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(ID_LIST)) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(ID_LIST, null);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            catIds = gson.fromJson(json, type);
        }
        return catIds;
    }


    private void saveListToPreffs(ArrayList<String> arrayList) {
        SharedPreferences sharedPreferences = ExpandedActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(ID_LIST, json);
        editor.apply();
    }

    private void saveData(){
        saveImageToExternalStorage();
        catIds.add(catId);
        saveListToPreffs(catIds);
        Toast.makeText(ExpandedActivity.this, R.string.saved, Toast.LENGTH_SHORT)
                .show();
    }

    private void loadData() {
        loadIdFromSharedPrefs();
        Bitmap bitmap = loadImageFromExternalStorage();
        Toast.makeText(ExpandedActivity.this, R.string.loaded + bitmap.toString(), Toast.LENGTH_SHORT)
                .show();
    }

    private void saveIdToSharedPrefs(String id) {
        SharedPreferences sharedPreferences = ExpandedActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CAT_ID, id);
        editor.apply();
    }

    private String loadIdFromSharedPrefs() {
        String id = "";
        SharedPreferences sharedPreferences = ExpandedActivity.this.getApplicationContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(CAT_ID)) {
            id = sharedPreferences.getString(CAT_ID, null);
        }
        return id;
    }

    private Bitmap loadImageFromExternalStorage() {
        String photoPath = Environment.getExternalStorageDirectory()
                + "/" + FOLDER_NAME + "/" + "image-"+catId+".jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(photoPath, options);
    }

    private void saveImageToExternalStorage() {
        if (!isExternalStorageWritePermission()) {
            askForExternalStorageWritePermission();
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

    private boolean isExternalStorageWritePermission() {
        return ContextCompat.checkSelfPermission(ExpandedActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

    }

    private boolean isExternalStorageReadPermission() {
        return ContextCompat.checkSelfPermission(ExpandedActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

    }

    private void askForExternalStorageReadPermission() {
        ActivityCompat.requestPermissions(ExpandedActivity.this, externalStoragePermission, 1);
    }

    private void askForExternalStorageWritePermission() {
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

