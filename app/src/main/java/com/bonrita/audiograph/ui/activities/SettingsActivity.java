package com.bonrita.audiograph.ui.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.preferences.LibraryPreference;
import com.bonrita.audiograph.preferences.LibraryPreferenceDialog;
import com.bonrita.audiograph.ui.activities.base.AbsBaseActivity;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.appthemehelper.common.prefs.supportv7.ATEPreferenceFragmentCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AbsBaseActivity {
    public static final String TAG = SettingsActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setDrawUnderStatusbar();
        ButterKnife.bind(this);

        setStatusbarColorAuto();
        setTaskDescriptionColorAuto();

        toolbar.setBackgroundColor(ThemeStore.primaryColor(this));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
        }
// else {
//            SettingsFragment frag = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
//            if (frag != null) frag.invalidateSettings();
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class SettingsFragment extends ATEPreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.pref_library);
            addPreferencesFromResource(R.xml.pref_images);
        }

        @Nullable
        @Override
        public DialogFragment onCreatePreferenceDialog(Preference preference) {
            if (preference instanceof LibraryPreference) {
                return LibraryPreferenceDialog.newInstance();
            }
            return super.onCreatePreferenceDialog(preference);
        }
    }
}
