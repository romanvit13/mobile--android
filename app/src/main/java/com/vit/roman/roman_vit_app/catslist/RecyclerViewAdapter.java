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
import com.vit.roman.roman_vit_app.App;
import com.vit.roman.roman_vit_app.MainActivity;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.entity.CatEntity;
import com.vit.roman.roman_vit_app.expanded.ExpandedFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<CatEntity> mCats;
    private Context mContext;

    RecyclerViewAdapter(Context context, List<CatEntity> cats) {
        mCats = cats;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        RequestOptions glideOptions = new RequestOptions();
        Glide.with(mContext)
                .asBitmap()
                .load(mCats.get(viewHolder.getAdapterPosition()).getUrl())
                .apply(glideOptions.centerCrop())
                .into(viewHolder.mImageView);
        viewHolder.mTextView.setText(mCats.get(viewHolder.getAdapterPosition()).getId());
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.setCatEntity(mCats.get(viewHolder.getAdapterPosition()));
                ExpandedFragment fragment = new ExpandedFragment();
                ((MainActivity) view.getContext()).setFragment(fragment);
            }
        });
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

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
