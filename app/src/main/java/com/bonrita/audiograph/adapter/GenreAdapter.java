package com.bonrita.audiograph.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.adapter.base.MediaEntryViewHolder;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{

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
