package com.bonrita.audiograph.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.bonrita.audiograph.model.CategoryInfo;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.pager.AlbumsFragment;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.pager.ArtistsFragment;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.pager.GenresFragment;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.pager.PlaylistsFragment;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.pager.SongsFragment;
import com.bonrita.audiograph.util.PreferenceUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MusicLibraryPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = MusicLibraryPagerAdapter.class.getSimpleName();

    private final SparseArray<WeakReference<Fragment>> mFragmentArray = new SparseArray<>();

    @NonNull
    private final Context mContext;

    private final ArrayList<Holder> mHolderList = new ArrayList<>();

    public MusicLibraryPagerAdapter(FragmentManager fm, @NonNull Context context) {
        super(fm);
        mContext = context;
        setCategoryInfo(PreferenceUtil.getInstance(context).getLibraryCategoryInfos());
    }

    public void setCategoryInfo(ArrayList<CategoryInfo> categoryInfos) {
        mHolderList.clear();

        for (CategoryInfo categoryInfo : categoryInfos) {
            if (categoryInfo.mVisible) {
                Log.i(TAG, "setCategoryInfo: toString: " + categoryInfo.mCategory.toString());
                Log.i(TAG, "setCategoryInfo: mStringRes: " + categoryInfo.mCategory.mStringRes);
                Log.i(TAG, "setCategoryInfo: Ordinal: " + categoryInfo.mCategory.ordinal());
                Log.i(TAG, "setCategoryInfo: name: " + categoryInfo.mCategory.name());
                Log.i(TAG, "setCategoryInfo: ValueOf: " + CategoryInfo.Category.valueOf(categoryInfo.mCategory.toString()));
                int bon = CategoryInfo.Category.valueOf(categoryInfo.mCategory.toString()).mStringRes;
                Log.i(TAG, "setCategoryInfo: Bon: " + bon);
                // --------------------------------------


                MusicFragments fragment = MusicFragments.valueOf(categoryInfo.mCategory.toString());
                Holder holder = new Holder();
                holder.mClassName = fragment.getFragmentClass().getName();
                holder.mTitle = mContext.getResources().getString(categoryInfo.mCategory.mStringRes)
                        .toUpperCase(Locale.getDefault());

                mHolderList.add(holder);

            }
        }

        alignCache();
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        final Holder currentHolder = mHolderList.get(position);
        return Fragment.instantiate(mContext, currentHolder.mClassName, currentHolder.mParams);
    }

    @Override
    public int getCount() {
        return mHolderList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mHolderList.get(position).mTitle;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);

        final WeakReference<Fragment> weakFragment = mFragmentArray.get(position);

        if (weakFragment != null) {
            weakFragment.clear();
        }

        mFragmentArray.put(position, new WeakReference<>(fragment));

        return fragment;
    }

    @Override
    public int getItemPosition(@NonNull Object fragment) {
        for (int i = 0, size = mHolderList.size(); i < size; i++) {
            Holder holder = mHolderList.get(i);
            if (holder.mClassName.equals(fragment.getClass().getName())) {
                return i;
            }
        }
        return POSITION_NONE;
    }

    @Override
    public long getItemId(int position) {
        // as fragment position is not fixed, we can't use position as id
        return MusicFragments.of(getFragment(position).getClass()).ordinal();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        final WeakReference<Fragment> weakFrangment = mFragmentArray.get(position);
        if (weakFrangment != null) {
            weakFrangment.clear();
        }
    }

    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> weakFragment = mFragmentArray.get(position);
        if (weakFragment != null && weakFragment.get() != null) {
            return weakFragment.get();
        }
        return getItem(position);
    }

    /**
     * Aligns the fragment cache with the current category layout.
     */
    private void alignCache() {
        if (mFragmentArray.size() == 0) return;

        HashMap<String, WeakReference<Fragment>> mappings = new HashMap<>(mFragmentArray.size());

        for (int i = 0, size = mFragmentArray.size(); i < size; i++) {
            WeakReference<Fragment> ref = mFragmentArray.valueAt(i);
            Fragment fragment = ref.get();
            if (fragment != null) {
                mappings.put(fragment.getClass().getName(), ref);
            }
        }

        for (int i = 0, size = mHolderList.size(); i < size; i++) {
            WeakReference<Fragment> ref = mappings.get(mHolderList.get(i).mClassName);
            if (ref != null) {
                mFragmentArray.put(i, ref);
            } else {
                mFragmentArray.remove(i);
            }
        }
    }

    public enum MusicFragments {
        SONGS(SongsFragment.class),
        ALBUMS(AlbumsFragment.class),
        ARTISTS(ArtistsFragment.class),
        GENRES(GenresFragment.class),
        PLAYLISTS(PlaylistsFragment.class);


        private final Class<? extends Fragment> mFragmentClass;

        MusicFragments(final Class<? extends Fragment> fragmentClass) {
            mFragmentClass = fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return mFragmentClass;
        }

        public static MusicFragments of(Class<?> cl) {
            MusicFragments[] fragments = All.FRAGMENTS;
            for (int i = 0; i < fragments.length; i++) {
                if (cl.equals(fragments[i].mFragmentClass)) {
                    return fragments[i];
                }
            }

            throw new IllegalArgumentException("Unknown music fragment " + cl);
        }

        private static class All {
            public static final MusicFragments[] FRAGMENTS = values();
        }
    }

    private final static class Holder {
        String mClassName;
        String mTitle;
        Bundle mParams;
    }
}
