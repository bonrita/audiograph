package com.bonrita.audiograph.preferences;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bonrita.audiograph.R;
import com.bonrita.audiograph.adapter.CategoryInfoAdapter;
import com.bonrita.audiograph.model.CategoryInfo;
import com.bonrita.audiograph.util.PreferenceUtil;

import java.util.ArrayList;

public class LibraryPreferenceDialog extends DialogFragment {

    private CategoryInfoAdapter mAdapter;

    public static LibraryPreferenceDialog newInstance() {
        return new LibraryPreferenceDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.preference_dialog_library_categories, null);

        ArrayList<CategoryInfo> categoryInfos;
        if (savedInstanceState != null) {
            categoryInfos = savedInstanceState.getParcelableArrayList(PreferenceUtil.LIBRARY_CATEGORIES);
        } else {
            categoryInfos = PreferenceUtil.getInstance(getContext()).getLibraryCategoryInfos();
        }

        mAdapter = new CategoryInfoAdapter(categoryInfos);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        mAdapter.attachToRecyclerView(recyclerView);

        return new MaterialDialog.Builder(getContext())
                .title(R.string.library_categories)
                .customView(view, false)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .neutralText(R.string.reset_action)
                .autoDismiss(false)
                .onNeutral((dialog, action) -> mAdapter.setCategoryInfos(PreferenceUtil.getInstance(getContext()).getDefaultLibraryCategoryInfos()))
                .onNegative((dialog, action) -> dismiss())
                .onPositive((dialog, action) -> {
                    updateCategories(mAdapter.getCategoryInfos());
                    dismiss();
                }).build();
    }

    private void updateCategories(ArrayList<CategoryInfo> categories) {
        if (getSelected(categories) == 0) return;
        PreferenceUtil.getInstance(getContext()).setLibraryCategoryInfos(categories);
    }

    private int getSelected(ArrayList<CategoryInfo> categories) {
        int selected = 0;
        for (CategoryInfo categoryInfo : categories) {
            if (categoryInfo.mVisible) {
                selected++;
            }
        }
        return selected;
    }

}
