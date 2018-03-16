package com.bonrita.audiograph.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    public static final Song EMPTY_SONG = new Song(-1, "", -1, -1, -1, "", -1, -1, "", -1, "");

    public final int mId;
    public final String mTitle;
    public final int mTrackNumber;
    public final int mYear;
    public final long mDuration;
    public final String mData;
    public final long mDateModified;
    public final int mAlbumId;
    public final String mAlbumName;
    public final int mArtistId;
    public final String mArtistName;

    protected Song(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mTrackNumber = in.readInt();
        mYear = in.readInt();
        mDuration = in.readLong();
        mData = in.readString();
        mDateModified = in.readLong();
        mAlbumId = in.readInt();
        mAlbumName = in.readString();
        mArtistId = in.readInt();
        mArtistName = in.readString();
    }

    public Song(int id, String title, int trackNumber, int year, long duration, String data, long dateModified, int albumId, String albumName, int artistId, String artistName) {
        mId = id;
        mTitle = title;
        mTrackNumber = trackNumber;
        mYear = year;
        mDuration = duration;
        mData = data;
        mDateModified = dateModified;
        mAlbumId = albumId;
        mAlbumName = albumName;
        mArtistId = artistId;
        mArtistName = artistName;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeInt(mTrackNumber);
        dest.writeInt(mYear);
        dest.writeLong(mDuration);
        dest.writeString(mData);
        dest.writeLong(mDateModified);
        dest.writeInt(mAlbumId);
        dest.writeString(mAlbumName);
        dest.writeInt(mArtistId);
        dest.writeString(mArtistName);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + mId +
                ", title='" + mTitle + '\'' +
                ", trackNumber=" + mTrackNumber +
                ", year=" + mYear +
                ", duration=" + mDuration +
                ", data='" + mData + '\'' +
                ", dateModified=" + mDateModified +
                ", albumId=" + mAlbumId +
                ", albumName='" + mAlbumName + '\'' +
                ", artistId=" + mArtistId +
                ", artistName='" + mArtistName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + mTrackNumber;
        result = 31 * result + mYear;
        result = 31 * result + (int) (mDuration ^ (mDuration >>> 32));
        result = 31 * result + (mData != null ? mData.hashCode() : 0);
        result = 31 * result + (int) (mDateModified ^ (mDateModified >>> 32));
        result = 31 * result + mAlbumId;
        result = 31 * result + (mAlbumName != null ? mAlbumName.hashCode() : 0);
        result = 31 * result + mArtistId;
        result = 31 * result + (mArtistName != null ? mArtistName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (mId != song.mId) return false;
        if (mTrackNumber != song.mTrackNumber) return false;
        if (mYear != song.mYear) return false;
        if (mDuration != song.mDuration) return false;
        if (mDateModified != song.mDateModified) return false;
        if (mAlbumId != song.mAlbumId) return false;
        if (mArtistId != song.mArtistId) return false;
        if (mTitle != null ? !mTitle.equals(song.mTitle) : song.mTitle != null) return false;
        if (mData != null ? !mData.equals(song.mData) : song.mData != null) return false;
        if (mAlbumName != null ? !mAlbumName.equals(song.mAlbumName) : song.mAlbumName != null)
            return false;

        return mArtistName != null ? mArtistName.equals(song.mArtistName) : song.mArtistName == null;
    }

}
