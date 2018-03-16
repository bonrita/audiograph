package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.adapter.song.SongAdapter;
import com.bonrita.audiograph.interfaces.LoaderIds;
import com.bonrita.audiograph.loader.SongLoader;
import com.bonrita.audiograph.misc.WrappedAsyncTaskLoader;
import com.bonrita.audiograph.model.Song;

import java.util.ArrayList;

public class SongsFragment extends AbsLibraryPagerRecyclerViewCustomGridSizeFragment<SongAdapter, GridLayoutManager> implements LoaderManager.LoaderCallbacks<ArrayList<Song>> {

    public static final String TAG = SongsFragment.class.getSimpleName();

    private static final int LOADER_ID = LoaderIds.SONGS_FRAGMENT;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<ArrayList<Song>> onCreateLoader(int id, Bundle args) {
        return new AsyncSongLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Song>> loader, ArrayList<Song> data) {
        getAdapter().swapDataset(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Song>> loader) {
        getAdapter().swapDataset(new ArrayList<>());
    }

    @Override
    protected GridLayoutManager createLayoutManager() {
        return new GridLayoutManager(getActivity(), getGridSize());
    }

    @NonNull
    @Override
    protected SongAdapter createAdapter() {
        int itemLayoutRes = getItemLayoutRes();
        notifyLayoutResChanged(itemLayoutRes);
        boolean usePalette = loadUsePalette();

//        ArrayList<Song> dataSet = getAdapter() == null ? new ArrayList<>() : getAdapter().getDataSet();
        ArrayList<Song> dataSet = new ArrayList<>();
        return new SongAdapter(
                getLibraryFragment().getMainActivity(),
                dataSet,
                itemLayoutRes,
                usePalette,
                getLibraryFragment());
    }

    @Override
    protected int loadGridSize() {
        return 1;
    }

    @Override
    protected int loadGridSizeLand() {
        return 1;
    }

    @Override
    protected boolean loadUsePalette() {
        return false;
    }

    @Override
    protected int getEmptyMessage() {
        return R.string.no_songs;
    }

    private static class AsyncSongLoader extends WrappedAsyncTaskLoader<ArrayList<Song>> {

        public AsyncSongLoader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public ArrayList<Song> loadInBackground() {
            // @todo probably this is the place to place logic of either getting the songs from online or offline.
            // or from the phone's internal storage. So also the logic to ccheck if the app
            // is a paid version whould be checked here to allow offline playing.
            return SongLoader.getAllSongs(getContext());
        }

    }

}
