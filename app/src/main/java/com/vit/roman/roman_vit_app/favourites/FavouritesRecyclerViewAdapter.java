package com.vit.roman.roman_vit_app.favourites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.listener.OnItemClickListener;
import com.vit.roman.roman_vit_app.entity.CatEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.ViewHolder> {

    private List<CatEntity> mCatEntities;
    private OnItemClickListener mListener;

    FavouritesRecyclerViewAdapter(List<CatEntity> catEntities, OnItemClickListener listener) {
        this.mCatEntities = catEntities;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.bind(mListener, viewHolder.getAdapterPosition());
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

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(final OnItemClickListener listener, final int position) {
            mTextView.setText(mCatEntities.get(position).getId());
            RequestOptions glideOptions = new RequestOptions();
            Glide.with(mImageView.getContext())
                    .asBitmap()
                    .load(mCatEntities.get(position).getUrl())
                    .apply(glideOptions.centerCrop())
                    .into(mImageView);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, mCatEntities.get(position));
                }
            });
        }
    }

}
