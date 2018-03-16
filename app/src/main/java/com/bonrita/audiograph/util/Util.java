package com.bonrita.audiograph.util;


import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Util {
    public static void setStatusBarTranslucent(Window window) {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public static void setAllowDrawUnderStatusBar(@NonNull Window window) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
