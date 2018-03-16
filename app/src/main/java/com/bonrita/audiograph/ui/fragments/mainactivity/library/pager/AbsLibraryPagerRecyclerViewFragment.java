package com.bonrita.audiograph.ui.fragments.mainactivity.library.pager;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bonrita.audiograph.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbsLibraryPagerRecyclerViewFragment<A extends RecyclerView.Adapter, LM extends RecyclerView.LayoutManager> extends AbsLibraryPagerFragment {

    public static final String TAG = AbsLibraryPagerRecyclerViewFragment.class.getSimpleName();

    private Unbinder mUnbinder;

    @BindView(R.id.container)
    View container;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.empty)
    TextView empty;

    private A mAdapter;
    private LM mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity_recycler_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLayoutManager();
        initAdapter();
        setUpRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = createAdapter();
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkIsEmpty();
            }
        });
    }

    private void checkIsEmpty() {
        if (empty != null) {
            empty.setText(getEmptyMessage());
            int cc = mAdapter.getItemCount();
            empty.setVisibility(mAdapter == null || mAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
        }
    }

    protected A getAdapter() {
        return mAdapter;
    }

    @StringRes
    protected int getEmptyMessage() {
        return R.string.empty;
    }

    private void initLayoutManager() {
        mLayoutManager = createLayoutManager();
    }

    protected abstract LM createLayoutManager();

    @NonNull
    protected abstract A createAdapter();

}
