package com.bonrita.audiograph.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.ui.activities.MainActivity;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.appthemehelper.util.ColorUtil;


public abstract class AbsMainActivityFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public void setStatusbarColor(View view, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final View statusBar = view.findViewById(R.id.status_bar);
            if (statusBar != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    statusBar.setBackgroundColor(ColorUtil.darkenColor(color));
                } else {
                    statusBar.setBackgroundColor(color);
                }
            }
        }
    }

    public void setStatusbarColorAuto(View view) {
        setStatusbarColor(view, ThemeStore.primaryColor(getContext()));
    }

}
