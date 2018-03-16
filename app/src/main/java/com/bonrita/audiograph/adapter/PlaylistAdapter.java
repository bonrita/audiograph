package com.bonrita.audiograph.adapter;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.adapter.base.AbsMultiSelectAdapter;
import com.bonrita.audiograph.adapter.base.MediaEntryViewHolder;
import com.bonrita.audiograph.model.Playlist;

public class PlaylistAdapter extends AbsMultiSelectAdapter<PlaylistAdapter.ViewHolder, Playlist>{

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

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
