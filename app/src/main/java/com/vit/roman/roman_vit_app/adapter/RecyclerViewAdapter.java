package com.vit.roman.roman_vit_app.adapter;

import android.content.Context;
import android.os.Bundle;
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
import com.vit.roman.roman_vit_app.entity.Cat;
import com.vit.roman.roman_vit_app.fragment.ExpandedFragment;
import com.vit.roman.roman_vit_app.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Cat> mCats;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Cat> cats) {
        Log.i(TAG, "Constructor");
        mCats = cats;
        mContext = context;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.i(TAG, "onBindViewHolder");

        RequestOptions glideOptions = new RequestOptions();
        Glide.with(mContext)
                .asBitmap()
                .load(mCats.get(position).getImage())
                .apply(glideOptions.centerCrop())
                .into(viewHolder.mImageView);

        viewHolder.mTextView.setText(mCats.get(position).getId());
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "OnClick: " + mCats.get(position).getId());
                Toast.makeText(mContext, mCats.get(position).getId(), Toast.LENGTH_SHORT).show();
                startExpandedFragment(view, mCats.get(position).getId(), mCats.get(position).getImage());
            }
        });
    }

    private void startExpandedFragment(View v, String catId, String catImageUrl) {
        ExpandedFragment fragment = new ExpandedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cat_id", catId);
        bundle.putString("cat_image_url", catImageUrl);
        fragment.setArguments(bundle);
        ((MainActivity) v.getContext()).setFragment(fragment);
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
