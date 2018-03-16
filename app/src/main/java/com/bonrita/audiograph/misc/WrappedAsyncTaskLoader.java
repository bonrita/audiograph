package com.bonrita.audiograph.misc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

public abstract class WrappedAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mData;

    public WrappedAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mData == null || takeContentChanged()) {
            forceLoad();
        } else {
            deliverResult(mData);
        }
    }

    @Override
    protected void onReset() {
        mData = null;
    }
}
