package com.bonrita.audiograph.adapter.artist;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.adapter.base.AbsMultiSelectAdapter;
import com.bonrita.audiograph.adapter.base.MediaEntryViewHolder;
import com.bonrita.audiograph.adapter.song.SongAdapter;
import com.bonrita.audiograph.model.Artist;

public class ArtistAdapter extends AbsMultiSelectAdapter<ArtistAdapter.ViewHolder, Artist>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends MediaEntryViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
