package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.bonrita.audiograph.adapter.GenreAdapter;

public class GenresFragment extends AbsLibraryPagerRecyclerViewFragment<GenreAdapter, LinearLayoutManager> {
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return null;
    }

    @NonNull
    @Override
    protected GenreAdapter createAdapter() {
        return null;
    }
}
