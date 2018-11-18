package com.vit.roman.roman_vit_app.favourites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder> {

    private static final String FOLDER_NAME = "cat_images";
    private List<CatEntity> mCatEntities;

    public FavouritesRecyclerViewAdapter(List<CatEntity> catEntities){
        this.mCatEntities = catEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mCatEntities.get(i).getId());
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(viewHolder.mImageView.getContext())
                .asBitmap()
                .load(mCatEntities.get(i).getUrl())
                .apply(glideOptions.centerCrop())
                .into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCatEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.imageHeader)
        TextView mTextView;

        RelativeLayout mRecyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRecyclerView = itemView.findViewById(R.id.recycler_view_favourites);
        }
    }

    public void clear() {
        mCatEntities.clear();
        notifyDataSetChanged();
    }

}
