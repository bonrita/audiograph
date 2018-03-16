package com.bonrita.audiograph.adapter.song;


import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.adapter.base.AbsMultiSelectAdapter;
import com.bonrita.audiograph.adapter.base.MediaEntryViewHolder;
import com.bonrita.audiograph.glide.PhonographColoredTarget;
import com.bonrita.audiograph.glide.SongGlideRequest;
import com.bonrita.audiograph.interfaces.CabHolder;
import com.bonrita.audiograph.model.Song;
import com.bumptech.glide.Glide;
import com.kabouzeid.appthemehelper.util.ColorUtil;
import com.kabouzeid.appthemehelper.util.MaterialValueHelper;

import java.util.ArrayList;

public class SongAdapter extends AbsMultiSelectAdapter<SongAdapter.ViewHolder, Song> {

    public static final String TAG = SongAdapter.class.getSimpleName();

    protected final AppCompatActivity mActivity;
    protected boolean mShowSectionName = true;
    protected boolean mUsePalette = false;
    protected int mItemLayoutRes;

    protected ArrayList<Song> mDataSet;

    public SongAdapter(AppCompatActivity activity, ArrayList<Song> dataSet, @LayoutRes int itemLayoutRes, boolean usePalette, CabHolder cabHolder) {
        this(activity, dataSet, itemLayoutRes, usePalette, cabHolder, true);
    }

    public SongAdapter(AppCompatActivity activity, ArrayList<Song> dataset, @LayoutRes int itemLayoutRes, boolean usePalette, CabHolder cabHolder, boolean showSectionName) {
        super();
        mActivity = activity;
        mDataSet = dataset;
        mItemLayoutRes = itemLayoutRes;
        mUsePalette = usePalette;
        mShowSectionName = showSectionName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(mItemLayoutRes, parent, false);
        return createViewHolder(view);
    }

    private ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Song song = mDataSet.get(position);

        if (holder.title != null) {
            Log.i(TAG, "onBindViewHolder: Title available " + song.mTitle);
            holder.title.setText(song.mTitle);
        }

        if (holder.text != null) {
            holder.text.setText(song.mArtistName);
        }

        loadAlbumCover(song, holder);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return mDataSet.get(position).mId;
    }

    public ArrayList<Song> getDataSet() {
        return mDataSet;
    }

    public void swapDataset(ArrayList<Song> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
    }

    protected void loadAlbumCover(Song song, final ViewHolder holder) {
        if (holder.image == null) return;

        SongGlideRequest.Builder.from(Glide.with(mActivity), song)
                .checkIgnoreMediaStore(mActivity)
                .generatePalette(mActivity).build()
                .into(new PhonographColoredTarget(holder.image) {
                    @Override
                    public void onColorReady(int color) {
                        if (mUsePalette) {
                            setColors(color, holder);
                        } else {
                            setColors(getDefaultFooterColor(), holder);
                        }
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                        setColors(getDefaultFooterColor(), holder);
                    }
                });
    }

    private void setColors(int color, ViewHolder holder) {
        if (holder.paletteColorContainer != null) {
            holder.paletteColorContainer.setBackgroundColor(color);
            if (holder.title != null) {
                holder.title.setTextColor(MaterialValueHelper.getPrimaryTextColor(mActivity, ColorUtil.isColorLight(color)));
            }
            if (holder.text != null) {
                holder.text.setTextColor(MaterialValueHelper.getSecondaryTextColor(mActivity, ColorUtil.isColorLight(color)));
            }
        }
    }

    public class ViewHolder extends MediaEntryViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
