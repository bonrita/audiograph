package com.bonrita.audiograph.ui.activities;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.App;
import com.bonrita.audiograph.R;
import com.bonrita.audiograph.ui.activities.base.AbsSlidingMusicPanelActivity;
import com.bonrita.audiograph.ui.fragments.mainactivity.library.LibraryFragment;
import com.bonrita.audiograph.util.PreferenceUtil;
import com.bonrita.audiograph.util.Util;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.appthemehelper.util.ATHUtil;
import com.kabouzeid.appthemehelper.util.NavigationViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AbsSlidingMusicPanelActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int LIBRARY = 0;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Util.setStatusBarTranslucent(getWindow());
            drawerLayout.setFitsSystemWindows(false);
            navigationView.setFitsSystemWindows(false);
            //noinspection ConstantConditions
            findViewById(R.id.drawer_content_container).setFitsSystemWindows(false);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawerLayout.setOnApplyWindowInsetsListener((view, windowInsets) -> {
                navigationView.dispatchApplyWindowInsets(windowInsets);
                return windowInsets.replaceSystemWindowInsets(0, 0, 0, 0);
            });
        }

        setUpDrawerLayout();
    }

    private void setUpDrawerLayout() {
        setUpNavigationView();
    }

    private void setUpNavigationView() {
        int accentColor = ThemeStore.accentColor(this);
        NavigationViewUtil.setItemIconColors(navigationView, ATHUtil.resolveColor(this, R.attr.iconColor, ThemeStore.textColorSecondary(this)), accentColor);
        NavigationViewUtil.setItemTextColors(navigationView, ThemeStore.textColorPrimary(this), accentColor);

//        checkSetUpPro();

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();

            switch (item.getItemId()) {
                case R.id.nav_library:
                    new Handler().postDelayed(() -> {
                        setMusicChooser(LIBRARY);
                    }, 200);
                    break;

                case R.id.nav_settings:
                    new Handler().postDelayed(() -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)), 200);
                    break;
            }

            return true;
        });

    }

    private void setMusicChooser(int key) {
        PreferenceUtil.getInstance(this).setLastMusicChooser(key);
        switch (key) {
            case LIBRARY:
                navigationView.setCheckedItem(R.id.nav_library);
                setCurrentFragment(LibraryFragment.newInstance());
                break;
        }
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void checkSetUpPro() {
        if (App.isProVersion()) {
            setUpPro();
        }
    }

    private void setUpPro() {
        navigationView.getMenu().removeGroup(R.id.navigation_drawer_menu_category_buy_pro);
    }

    @Override
    protected View createContentView() {
        View contentView = getLayoutInflater().inflate(R.layout.activity_main_drawer_layout, null);
        ViewGroup drawerContent = contentView.findViewById(R.id.drawer_content_container);
        drawerContent.addView(wrapSlidingMusicPanel(R.layout.activity_main_content));
        return contentView;
    }

}
