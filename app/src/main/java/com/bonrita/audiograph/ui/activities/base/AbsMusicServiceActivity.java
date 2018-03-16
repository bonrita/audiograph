package com.bonrita.audiograph.ui.activities.base;


import android.Manifest;
import android.support.annotation.Nullable;

public abstract class AbsMusicServiceActivity extends AbsBaseActivity {

    @Nullable
    @Override
    protected String[] getPermissionsToRequest() {
        return new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
    }
}
