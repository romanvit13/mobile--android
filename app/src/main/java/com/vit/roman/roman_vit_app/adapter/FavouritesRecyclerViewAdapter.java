package com.vit.roman.roman_vit_app.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vit.roman.roman_vit_app.R;

import java.util.ArrayList;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder> {

    private static final String FOLDER_NAME = "cat_images";
    private ArrayList<String> mCatIds;

    public FavouritesRecyclerViewAdapter(ArrayList<String> catIds){
        this.mCatIds = catIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listitem, viewGroup, false);
        for (String catId : mCatIds) {
            Log.i("OK", "ID: "+catId);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mCatIds.get(viewHolder.getAdapterPosition()));
        viewHolder.mImageView.setImageBitmap(loadImageFromExternalStorage(
                mCatIds.get(viewHolder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return mCatIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        RelativeLayout mRecyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.imageHeader);
            mRecyclerView = itemView.findViewById(R.id.recycler_view_cats);
        }
    }

    public void clear() {
        mCatIds.clear();
        notifyDataSetChanged();
    }

    private Bitmap loadImageFromExternalStorage(String catId) {
        String photoPath = Environment.getExternalStorageDirectory()
                + "/" + FOLDER_NAME + "/" + "image-"+catId+".jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(photoPath, options);
    }
}
