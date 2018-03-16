package com.bonrita.audiograph.ui.fragments.mainactivity.library;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bonrita.audiograph.R;
import com.bonrita.audiograph.adapter.MusicLibraryPagerAdapter;
import com.bonrita.audiograph.interfaces.CabHolder;
import com.bonrita.audiograph.ui.fragments.AbsMainActivityFragment;
import com.bonrita.audiograph.util.PreferenceUtil;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.appthemehelper.util.ToolbarContentTintHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LibraryFragment extends AbsMainActivityFragment implements CabHolder, SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String TAG = LibraryFragment.class.getSimpleName();

    private Unbinder unbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.pager)
    ViewPager pager;

    private MusicLibraryPagerAdapter mPagerAdapter;

    public static LibraryFragment newInstance() {
        return new LibraryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        PreferenceUtil.getInstance(getActivity()).registerOnSharedPreferenceChangedListener(this);
        getMainActivity().setTaskDescriptionColorAuto();
        setUpToolbar();
        setUpViewPager();
    }

    private void setUpViewPager() {
        mPagerAdapter = new MusicLibraryPagerAdapter(getChildFragmentManager(), getActivity());
        pager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(pager);

        // Color tabs.
        int primaryColor = ThemeStore.primaryColor(getActivity());
        int normalColor = ToolbarContentTintHelper.toolbarSubtitleColor(getActivity(), primaryColor);
        int selectedColor = ToolbarContentTintHelper.toolbarTitleColor(getActivity(), primaryColor);
        tabs.setTabTextColors(normalColor, selectedColor);
        tabs.setSelectedTabIndicatorColor(ThemeStore.accentColor(getActivity()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (pager == null) return;
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Activity activity = getActivity();
        if (activity == null) return;
        ToolbarContentTintHelper.handleOnPrepareOptionsMenu(activity, toolbar);
    }

    private void setUpToolbar() {
        int primaryColor = ThemeStore.primaryColor(getActivity());
        appbar.setBackgroundColor(primaryColor);
        toolbar.setBackgroundColor(primaryColor);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        getActivity().setTitle(R.string.app_name);
        getMainActivity().setSupportActionBar(toolbar);
    }

    @Override
    public void onDestroyView() {
        PreferenceUtil.getInstance(getActivity()).unregisterOnSharedPreferenceChangedListener(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (PreferenceUtil.LIBRARY_CATEGORIES.equals(key)) {
            Fragment current = getCurrentFragment();
            mPagerAdapter.setCategoryInfo(PreferenceUtil.getInstance(getActivity()).getLibraryCategoryInfos());


        }
    }

    private Fragment getCurrentFragment() {
        return mPagerAdapter.getFragment(pager.getCurrentItem());
    }
}
