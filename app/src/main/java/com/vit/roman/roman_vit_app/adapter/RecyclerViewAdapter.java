package com.vit.roman.roman_vit_app.adapter;

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
import com.vit.roman.roman_vit_app.model.Cat;
import com.vit.roman.roman_vit_app.ui.ExpandedActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Cat> mCats;

    public RecyclerViewAdapter(ArrayList<Cat> cats) {
        Log.i(TAG, "Constructor");
        mCats = cats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        Log.i(TAG, "onBindViewHolder");
        final Context context = viewHolder.mImageView.getContext();
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(context)
                .asBitmap()
                .load(mCats.get(viewHolder.getAdapterPosition()).getImage())
                .apply(glideOptions.centerCrop())
                .into(viewHolder.mImageView);
        viewHolder.mTextView.setText(mCats.get(viewHolder.getAdapterPosition()).getId());
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "OnClick: " + mCats.get(viewHolder.getAdapterPosition()).getId());
                Toast.makeText(context, mCats.get(viewHolder.getAdapterPosition()).getId(),
                        Toast.LENGTH_SHORT).show();
                context.startActivity(ExpandedActivity.getStartIntent(context,
                        mCats.get(viewHolder.getAdapterPosition()).getId(),
                        mCats.get(viewHolder.getAdapterPosition()).getImage()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCats.size();
    }

    public void clear() {
        mCats.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Cat> cats) {
        mCats.addAll(cats);
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
}
