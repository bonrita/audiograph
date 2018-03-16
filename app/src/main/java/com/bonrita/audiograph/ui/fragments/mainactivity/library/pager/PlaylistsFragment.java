package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.bonrita.audiograph.adapter.PlaylistAdapter;

public class PlaylistsFragment extends AbsLibraryPagerRecyclerViewFragment<PlaylistAdapter, LinearLayoutManager> {
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return null;
    }

    @NonNull
    @Override
    protected PlaylistAdapter createAdapter() {
        return null;
    }
}
