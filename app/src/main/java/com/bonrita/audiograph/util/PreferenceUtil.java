package com.bonrita.audiograph.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.bonrita.audiograph.helper.SortOrder;
import com.bonrita.audiograph.model.CategoryInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public final class PreferenceUtil {

    private static final String LAST_MUSIC_CHOOSER = "last_music_chooser";
    public static final String LIBRARY_CATEGORIES = "library_categories";

    public static final String ARTIST_SORT_ORDER = "artist_sort_order";
    public static final String ARTIST_SONG_SORT_ORDER = "artist_song_sort_order";
    public static final String ARTIST_ALBUM_SORT_ORDER = "artist_album_sort_order";
    public static final String ALBUM_SORT_ORDER = "album_sort_order";
    public static final String ALBUM_SONG_SORT_ORDER = "album_song_sort_order";
    public static final String SONG_SORT_ORDER = "song_sort_order";
    public static final String GENRE_SORT_ORDER = "genre_sort_order";

    public static final String IGNORE_MEDIA_STORE_ARTWORK = "ignore_media_store_artwork";


    private static PreferenceUtil sInstance;
    private final SharedPreferences mPreferences;

    private PreferenceUtil(@NonNull final Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferenceUtil getInstance(@NonNull final Context context) {
        if (sInstance == null) {
            sInstance = new PreferenceUtil(context.getApplicationContext());
        }
        return sInstance;
    }

    public void setLastMusicChooser(final int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(LAST_MUSIC_CHOOSER, value);
        editor.apply();
    }

    public void setLibraryCategoryInfos(ArrayList<CategoryInfo> categories) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<CategoryInfo>>() {
        }.getType();

        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(LIBRARY_CATEGORIES, gson.toJson(categories, collectionType));
        editor.apply();
    }

    public ArrayList<CategoryInfo> getLibraryCategoryInfos() {
        String data = mPreferences.getString(LIBRARY_CATEGORIES, null);
        if (data != null) {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<CategoryInfo>>() {
            }.getType();

            try {
                return gson.fromJson(data, collectionType);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }

        return getDefaultLibraryCategoryInfos();
    }

    public ArrayList<CategoryInfo> getDefaultLibraryCategoryInfos() {
        ArrayList<CategoryInfo> defaultCategoryInfos = new ArrayList<>(5);
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.SONGS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.ALBUMS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.ARTISTS, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.GENRES, true));
        defaultCategoryInfos.add(new CategoryInfo(CategoryInfo.Category.PLAYLISTS, true));
        return defaultCategoryInfos;
    }

    public void registerOnSharedPreferenceChangedListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
        mPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangedListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
        mPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }

    public final String getSongSortOrder() {
        return mPreferences.getString(SONG_SORT_ORDER, SortOrder.SongSortOrder.SONG_A_Z);
    }

    public final Boolean ignoreMediaStoreArtwork() {
        return mPreferences.getBoolean(IGNORE_MEDIA_STORE_ARTWORK, false);
    }

    public final int getLastMusicChooser() {
        return mPreferences.getInt(LAST_MUSIC_CHOOSER, 0);
    }
}
