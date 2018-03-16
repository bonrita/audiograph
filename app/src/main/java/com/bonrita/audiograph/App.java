package com.bonrita.audiograph;

import android.app.Application;

import com.kabouzeid.appthemehelper.ThemeStore;


public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        // Default theme.
        // Style the app with default colors.
        if (!ThemeStore.isConfigured(this, 1)) {
            ThemeStore.editTheme(this)
                    .activityTheme(R.style.Theme_Phonograph_Light)
                    .primaryColorRes(R.color.md_indigo_500)
                    .accentColorRes(R.color.md_pink_A400)
                    .commit();
        }

    }

    public static boolean isProVersion() {
        return BuildConfig.DEBUG;
    }

    public static App getInstance() {
        return app;
    }

}
