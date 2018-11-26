package com.vit.roman.roman_vit_app.catslist;

import android.content.Context;
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
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<CatEntity> mCats;
    private Context mContext;
    private final OnItemClickListener mListener;

    RecyclerViewAdapter(Context context, List<CatEntity> cats, OnItemClickListener listener) {
        mCats = cats;
        mContext = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.bind(mListener, viewHolder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mCats.size();
    }

    void clear() {
        mCats.clear();
        notifyDataSetChanged();
    }

    void addAll(List<CatEntity> cats) {
        mCats.addAll(cats);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.imageHeader)
        TextView mTextView;

        ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(final OnItemClickListener listener, final int position) {
            RequestOptions glideOptions = new RequestOptions();
            Glide.with(mContext)
                    .asBitmap()
                    .load(mCats.get(position).getUrl())
                    .apply(glideOptions.centerCrop())
                    .into(mImageView);
            mTextView.setText(mCats.get(position).getId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view, mCats.get(position));
                }
            });
        }
    }
}
