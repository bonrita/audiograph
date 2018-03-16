package com.bonrita.audiograph.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.ui.activities.MainActivity;
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


}
