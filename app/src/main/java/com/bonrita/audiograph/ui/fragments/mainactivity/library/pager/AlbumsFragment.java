package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.bonrita.audiograph.adapter.album.AlbumAdapter;

public class AlbumsFragment extends AbsLibraryPagerRecyclerViewCustomGridSizeFragment<AlbumAdapter, GridLayoutManager> {
    @Override
    protected GridLayoutManager createLayoutManager() {
        return null;
    }

    @NonNull
    @Override
    protected AlbumAdapter createAdapter() {
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
