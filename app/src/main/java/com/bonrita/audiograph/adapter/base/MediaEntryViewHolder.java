package com.bonrita.audiograph.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonrita.audiograph.R;

import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaEntryViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.image)
    public ImageView image;

    @Nullable
    @BindView(R.id.image_text)
    public TextView imageText;

    @Nullable
    @BindView(R.id.title)
    public TextView title;

    @Nullable
    @BindView(R.id.text)
    public TextView text;

    @Nullable
    @BindView(R.id.menu)
    public View menu;

    @Nullable
    @BindView(R.id.separator)
    public View separator;

    @Nullable
    @BindView(R.id.short_separator)
    public  View shortSeparator;

    @Nullable
    @BindView(R.id.drag_view)
    public View dragView;

    @Nullable
    @BindView(R.id.palette_color_container)
    public View paletteColorContainer;

    public MediaEntryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
