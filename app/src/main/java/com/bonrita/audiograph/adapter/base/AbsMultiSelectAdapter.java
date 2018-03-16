package com.bonrita.audiograph.adapter.base;


import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public abstract class AbsMultiSelectAdapter<VH extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<VH> {

    private ArrayList<I> mChecked;

}
