package com.bonrita.audiograph.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.bonrita.audiograph.R;

public class CategoryInfo implements Parcelable {

    public Category mCategory;
    public boolean mVisible;

    public CategoryInfo(Category category, boolean visible) {
        mCategory = category;
        mVisible = visible;
    }

    public CategoryInfo(Parcel source) {
        mCategory = (Category) source.readSerializable();
        mVisible = source.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(mCategory);
        dest.writeInt(mVisible ? 1 : 0);
    }

    public static final Parcelable.Creator<CategoryInfo> CREATOR = new Parcelable.Creator<CategoryInfo>() {
        @Override
        public CategoryInfo createFromParcel(Parcel source) {
            return new CategoryInfo(source);
        }

        @Override
        public CategoryInfo[] newArray(int size) {
            return new CategoryInfo[0];
        }
    };

    public enum Category {
        SONGS(R.string.songs),
        ALBUMS(R.string.albums),
        ARTISTS(R.string.artists),
        GENRES(R.string.genres),
        PLAYLISTS(R.string.playlists);

        public final int mStringRes;

        Category(int stringRes) {
            mStringRes = stringRes;
        }
    }

}
