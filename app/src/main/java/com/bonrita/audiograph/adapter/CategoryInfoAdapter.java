package com.bonrita.audiograph.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.model.CategoryInfo;
import com.bonrita.audiograph.util.SwipeAndDragHelper;

import java.util.ArrayList;

public class CategoryInfoAdapter extends RecyclerView.Adapter<CategoryInfoAdapter.ViewHolder> implements SwipeAndDragHelper.ActionCompletionContract {

    public static final String TAG = CategoryInfoAdapter.class.getSimpleName();

    private ItemTouchHelper mTouchHelper;
    private ArrayList<CategoryInfo> mCategoryInfos;

    public CategoryInfoAdapter(ArrayList<CategoryInfo> categoryInfos) {
        mCategoryInfos = categoryInfos;
        SwipeAndDragHelper swipeAndDragHelper = new SwipeAndDragHelper(this);
        mTouchHelper = new ItemTouchHelper(swipeAndDragHelper);
    }

    @Override
    public CategoryInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preference_dialog_library_categories_listitem, parent, false);
        return new CategoryInfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryInfoAdapter.ViewHolder holder, int position) {
        CategoryInfo categoryInfo = mCategoryInfos.get(position);
        Log.i(TAG, "onBindViewHolder: enum toString: " + categoryInfo.mCategory.toString());
        Log.i(TAG, "onBindViewHolder: enum name: " + categoryInfo.mCategory.name());
        Log.i(TAG, "onBindViewHolder: ordinal name: " + categoryInfo.mCategory.ordinal());
        Log.i(TAG, "onBindViewHolder: valueOf name: " + categoryInfo.mCategory.valueOf(categoryInfo.mCategory.toString()));


        holder.checkbox.setChecked(categoryInfo.mVisible);
        holder.title.setText(holder.title.getResources().getString(categoryInfo.mCategory.mStringRes));

        // On click.
        holder.itemView.setOnClickListener(v -> {
            if (categoryInfo.mVisible) {
                categoryInfo.mVisible = false;
                holder.checkbox.setChecked(categoryInfo.mVisible);
            } else {
                categoryInfo.mVisible = true;
                holder.checkbox.setChecked(categoryInfo.mVisible);
            }

            if (!checkAtleastOneCategoryChecked()) {
                Toast.makeText(holder.itemView.getContext(), R.string.you_have_to_select_at_least_one_category, Toast.LENGTH_SHORT).show();
            }
        });

        // On drag.
        holder.dragView.setOnTouchListener((v, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                mTouchHelper.startDrag(holder);
            }
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return mCategoryInfos.size();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        CategoryInfo categoryInfo = mCategoryInfos.remove(oldPosition);
        mCategoryInfos.add(newPosition, categoryInfo);
        notifyItemMoved(oldPosition, newPosition);
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        mTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void setCategoryInfos(ArrayList<CategoryInfo> categoryInfos) {
        mCategoryInfos = categoryInfos;
        notifyDataSetChanged();
    }

    public ArrayList<CategoryInfo> getCategoryInfos() {
        return mCategoryInfos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public View dragView;
        public TextView title;
        public CheckBox checkbox;

        public ViewHolder(View view) {
            super(view);
            checkbox = view.findViewById(R.id.checkbox);
            title = view.findViewById(R.id.title);
            dragView = view.findViewById(R.id.drag_view);
        }
    }

    protected boolean checkAtleastOneCategoryChecked() {
        for (CategoryInfo categoryInfo : mCategoryInfos) {
            if (categoryInfo.mVisible) {
                return true;
            }
        }
        return false;
    }
}
