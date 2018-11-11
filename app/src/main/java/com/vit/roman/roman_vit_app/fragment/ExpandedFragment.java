package com.vit.roman.roman_vit_app.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.ui.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandedFragment extends Fragment {

    @BindView(R.id.image_view_expanded)
    ImageView mImageView;
    @BindView(R.id.text_view_expanded)
    TextView mTextView;
    @BindView(R.id.favourite_action_button)
    Button mButtonFavourites;

    private String mCatImageUrl = "";
    private String mCatId = "";

    private static final String TAG = "ExpandedActivity";
    private static final String FOLDER_NAME = "cat_images";
    private static final String FAVOURITES_PREF = "FAVOURITES_PREF";
    private static final String CAT_ID = "CAT_ID";
    private static final String ID_LIST = "ID_LIST";
    private static final String[] EXTERNAL_STORAGE_PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private boolean mFavourite = false;
    private ArrayList<String> mCatIds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_item_expanded, container, false);
        ButterKnife.bind(this, view);
        initArrayList();
        getIncomingBundle();
        return view;
    }

    @OnClick({R.id.image_view_expanded, R.id.favourite_action_button})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.image_view_expanded:
                startFullScreenPhotoIntent(v);
                break;
            case R.id.favourite_action_button:
                if (!mFavourite) {
                    saveData();
                    mButtonFavourites.setText(R.string.favourite_button_action_rm);
                    mFavourite = true;
                } else {
                    loadData();
                    mButtonFavourites.setText(R.string.favourite_button_action_add);
                    mFavourite = false;
                }
                break;
        }
    }

    private void initArrayList() {
        mCatIds = getArrayList();
    }

    private ArrayList<String> getArrayList() {
        ArrayList<String> catIds = new ArrayList<>();
        SharedPreferences sharedPreferences = getContext()
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


    private void saveListToSharedPrefs(ArrayList<String> arrayList) {
        SharedPreferences sharedPreferences = getContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(ID_LIST, json);
        editor.apply();
    }

    private void saveData(){
        saveImageToExternalStorage();
        mCatIds.add(mCatId);
        saveListToSharedPrefs(mCatIds);
        Toast.makeText(getActivity(), R.string.saved, Toast.LENGTH_SHORT)
                .show();
    }

    private void loadData() {
        loadIdFromSharedPrefs();
        Bitmap bitmap = loadImageFromExternalStorage();
        Toast.makeText(getActivity(), R.string.loaded + bitmap.toString(), Toast.LENGTH_SHORT)
                .show();
    }

    private String loadIdFromSharedPrefs() {
        String id = "";
        SharedPreferences sharedPreferences = getContext()
                .getSharedPreferences(FAVOURITES_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(CAT_ID)) {
            id = sharedPreferences.getString(CAT_ID, null);
        }
        return id;
    }

    private Bitmap loadImageFromExternalStorage() {
        String photoPath = Environment.getExternalStorageDirectory()
                + "/" + FOLDER_NAME + "/" + "image-"+ mCatId +".jpg";
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

    private void startFullScreenPhotoIntent(View v) {
        ((MainActivity) v.getContext()).setFragment(
                FullscreenPhotoFragment.getStartFragment(mCatImageUrl));
    }

    private void getIncomingBundle() {
        mCatId = getArguments().getString("cat_id");
        mCatImageUrl = getArguments().getString("cat_image_url");
        setContent(mCatId, mCatImageUrl);
    }

    @SuppressLint("CheckResult")
    private void setContent(String catId, String imageUrl) {
        mTextView.setText(catId);
        RequestOptions glideOptions = new RequestOptions();
        glideOptions.centerCrop();
        Glide.with(this).load(imageUrl).apply(glideOptions).into(mImageView);
    }

    private boolean isExternalStorageWritePermission() {
        return ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

    }

    private boolean isExternalStorageReadPermission() {
        return ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

    }

    private void askForExternalStorageWritePermission() {
        ActivityCompat.requestPermissions(getActivity(), EXTERNAL_STORAGE_PERMISSION, 1);
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void saveImage(Bitmap bitmap, File directory) {
        String fileName = "image-" + mCatId + ".jpg";
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
