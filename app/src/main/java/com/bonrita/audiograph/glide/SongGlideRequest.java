package com.bonrita.audiograph.glide;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.glide.audiocover.AudioFileCover;
import com.bonrita.audiograph.glide.palette.BitmapPaletteTranscoder;
import com.bonrita.audiograph.glide.palette.BitmapPaletteWrapper;
import com.bonrita.audiograph.model.Song;
import com.bonrita.audiograph.util.MusicUtil;
import com.bonrita.audiograph.util.PreferenceUtil;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.MediaStoreSignature;

public class SongGlideRequest {

    public static final DiskCacheStrategy DEFAULT_DISK_CACHE_STRATEGY = DiskCacheStrategy.NONE;
    public static final int DEFAULT_ERROR_IMAGE = R.drawable.default_album_art;
    private static final int DEFAULT_ANIMATION = android.R.anim.fade_in;

    public static class Builder {
        final RequestManager mRequestManager;
        final Song mSong;
        boolean mIgnoreMediaStore;

        public static Builder from(@NonNull RequestManager requestManager, Song song) {
            return new Builder(requestManager, song);
        }

        private Builder(@NonNull RequestManager requestManager, Song song) {
            mRequestManager = requestManager;
            mSong = song;
        }

        public PaletteBuilder generatePalette(Context context) {
            return new PaletteBuilder(this, context);
        }

        public Builder checkIgnoreMediaStore(Context context) {
            return ignoreMediaStore(PreferenceUtil.getInstance(context).ignoreMediaStoreArtwork());
        }

        public Builder ignoreMediaStore(boolean ignoreMediaStore) {
            mIgnoreMediaStore = ignoreMediaStore;
            return this;
        }

    }

    public static class PaletteBuilder {

        final Context mContext;
        private final Builder mBuilder;

        public PaletteBuilder(Builder builder, Context context) {
            mContext = context;
            mBuilder = builder;
        }

        public BitmapRequestBuilder<?, BitmapPaletteWrapper> build() {
            return createBaseRequest(mBuilder.mRequestManager, mBuilder.mSong, mBuilder.mIgnoreMediaStore)
                    .asBitmap()
                    .transcode(new BitmapPaletteTranscoder(mContext), BitmapPaletteWrapper.class)
                    .diskCacheStrategy(DEFAULT_DISK_CACHE_STRATEGY)
                    .error(DEFAULT_ERROR_IMAGE)
                    .animate(DEFAULT_ANIMATION)
                    .signature(createSignature(mBuilder.mSong));
        }

    }

    public static DrawableTypeRequest createBaseRequest(RequestManager requestManager, Song song, boolean ignoreMediaStore) {
        if (ignoreMediaStore) {
            return requestManager.load(new AudioFileCover(song.mData));
        } else {
            return requestManager.loadFromMediaStore(MusicUtil.getMediaStoreAlbumCoverUri(song.mAlbumId));
        }
    }

    public static Key createSignature(Song song) {
        return new MediaStoreSignature("", song.mDateModified, 0);
    }

}
