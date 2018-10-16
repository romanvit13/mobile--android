package com.vit.roman.roman_vit_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String TAG = "Adapter";

    private ArrayList<String> mTexts;
    private ArrayList<String> mImages;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> texts, ArrayList<String> images) {
        Log.i(TAG, "Constructor");
        mTexts = texts;
        mImages = images;
        mContext = context;
        Log.i(TAG, "ImageNames size: " + mImages.size());
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.i(TAG, "onBindViewHolder");

        RequestOptions glideOptions = new RequestOptions();
        glideOptions.centerCrop();

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .apply(glideOptions)
                .into(viewHolder.mImageView);

        viewHolder.mTextView.setText(mTexts.get(position));
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "OnClick: "  + mTexts.get(position));
                Toast.makeText(mContext, mTexts.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void clear() {
        mImages.clear();
        mTexts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<String> images, List<String> texts){
        mImages.addAll(images);
        mTexts.addAll(texts);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;
        RelativeLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.imageHeader);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
