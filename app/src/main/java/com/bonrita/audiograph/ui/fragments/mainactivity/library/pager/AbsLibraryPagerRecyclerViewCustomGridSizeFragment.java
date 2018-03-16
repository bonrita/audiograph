package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.bonrita.audiograph.R;

public abstract class AbsLibraryPagerRecyclerViewCustomGridSizeFragment<A extends RecyclerView.Adapter, LM extends RecyclerView.LayoutManager> extends AbsLibraryPagerRecyclerViewFragment<A, LM> {

    private int mCurrentLayoutRes;

    /**
     * Override to customize which item layout currentLayoutRes should be used. You might also want to override {@link #canUsePalette()} then.
     *
     * @see #getGridSize()
     */
    @LayoutRes
    protected int getItemLayoutRes() {
        if (getGridSize() > getMaxGridSizeForList()) {
            return R.layout.item_grid;
        }
        return R.layout.item_list;
    }

    public final int getGridSize() {
        return loadGridSize();
    }

    protected int getMaxGridSizeForList() {
        if (isLandscape()) {
            return getActivity().getResources().getInteger(R.integer.default_list_columns_land);
        }
        return getActivity().getResources().getInteger(R.integer.default_list_columns);
    }

    protected final void notifyLayoutResChanged(@LayoutRes int res) {
        mCurrentLayoutRes = res;
        if (recyclerView != null) {
            applyRecyclerViewPaddingForLayoutRes();
        }
    }

    protected void applyRecyclerViewPaddingForLayoutRes() {
        int padding;
        if (mCurrentLayoutRes == R.layout.item_grid) {
            padding = (int) (getResources().getDisplayMetrics().density * 2);
        } else {
            padding = 0;
        }
        recyclerView.setPadding(padding, padding, padding, padding);
    }

    protected final boolean isLandscape() {
        return false;
    }

    protected abstract int loadGridSize();

    protected abstract int loadGridSizeLand();

    protected abstract boolean loadUsePalette();

}
