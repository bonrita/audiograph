package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.bonrita.audiograph.adapter.artist.ArtistAdapter;

public class ArtistsFragment extends AbsLibraryPagerRecyclerViewCustomGridSizeFragment<ArtistAdapter, GridLayoutManager> {

    @Override
    protected GridLayoutManager createLayoutManager() {
        return null;
    }

    @NonNull
    @Override
    protected ArtistAdapter createAdapter() {
        return null;
    }

    @Override
    protected int loadGridSize() {
        return 0;
    }

    @Override
    protected int loadGridSizeLand() {
        return 0;
    }

    @Override
    protected boolean loadUsePalette() {
        return false;
    }
}
