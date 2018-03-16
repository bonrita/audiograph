package com.bonrita.audiograph.helper;

import android.provider.MediaStore;

/**
 * Holds all of the sort orders for each list type.
 */
public class SortOrder {

    /**
     * Song sort order entries.
     */
    public interface SongSortOrder {
        /* Song sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Song sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* Song sort order artist */
        String SONG_ARTIST = MediaStore.Audio.Media.ARTIST;

        /* Song sort order album */
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Song sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Song sort order duration */
        String SONG_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Song sort order date */
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";
    }

}
