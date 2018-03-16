package com.bonrita.audiograph.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bonrita.audiograph.R;


public abstract class AbsSlidingMusicPanelActivity extends AbsMusicServiceActivity {

    public static final String TAG = AbsSlidingMusicPanelActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createContentView());

    }

    protected abstract View createContentView();

    protected View wrapSlidingMusicPanel(@LayoutRes int resId) {
        View slidingMusicPanelLayout = getLayoutInflater().inflate(R.layout.sliding_music_panel_layout, null);
        ViewGroup contentContainer = slidingMusicPanelLayout.findViewById(R.id.content_container);
        getLayoutInflater().inflate(resId, contentContainer);
        return slidingMusicPanelLayout;
    }

    @Override
    protected View getSnackBarContainer() {
        return findViewById(R.id.content_container);
    }
}
