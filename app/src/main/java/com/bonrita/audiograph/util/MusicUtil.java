package com.bonrita.audiograph.util;

import android.content.ContentUris;
import android.net.Uri;

public class MusicUtil {

    public static final String TAG = MusicUtil.class.getSimpleName();

    public static Uri getMediaStoreAlbumCoverUri(int albumId) {
        final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

        return ContentUris.withAppendedId(sArtworkUri, albumId);
    }

}
