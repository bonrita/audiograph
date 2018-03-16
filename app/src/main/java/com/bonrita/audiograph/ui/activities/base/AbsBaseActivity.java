package com.bonrita.audiograph.ui.activities.base;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.bonrita.audiograph.R;
import com.kabouzeid.appthemehelper.ThemeStore;

public abstract class AbsBaseActivity extends AbsThemeActivity {

    public static String TAG = AbsBaseActivity.class.getSimpleName();

    private static final int PERMISSION_REQUEST = 100;

    private String[] mPermissions;
    private String mPermissionDeniedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPermissions = getPermissionsToRequest();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (!hasPermissions()) {
            requestPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(AbsBaseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // The dialog is shown because a user denied access. So l show the dialog
                        // again.
                        Snackbar.make(getSnackBarContainer(), getPermissionDeniedMessage(),
                                Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.action_grant, v -> requestPermissions())
                                .setActionTextColor(ThemeStore.accentColor(this))
                                .show();
                    } else {
                        // This is shown when l request permissions within my app but such permissions
                        // were never defined in my manifest.xml file. BAD BAD. You should add them
                        // in the manifest. ALWAYS.
                        // So this bar should never be shown.
                        Snackbar.make(getSnackBarContainer(), getPermissionDeniedMessage()
                                , Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.action_settings, v -> {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", AbsBaseActivity.this.getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                })
                                .setActionTextColor(ThemeStore.accentColor(this))
                                .show();
                    }
                }
            }
        }

    }

    protected View getSnackBarContainer() {
        return getWindow().getDecorView();
    }

    @Nullable
    protected String[] getPermissionsToRequest() {
        return null;
    }

    protected void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mPermissions != null) {
            requestPermissions(mPermissions, PERMISSION_REQUEST);
        }
    }

    protected boolean hasPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mPermissions != null) {
            for (String permission : mPermissions) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getPermissionDeniedMessage() {
        return mPermissionDeniedMessage == null ? getString(R.string.permissions_denied) : mPermissionDeniedMessage;
    }

}
