package com.bonrita.audiograph.glide.palette;

import android.content.Context;
import android.graphics.Bitmap;

import com.bonrita.audiograph.util.PhonographColorUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;


public class BitmapPaletteTranscoder implements ResourceTranscoder<Bitmap, BitmapPaletteWrapper> {

    private final BitmapPool mBitmapPool;

    public BitmapPaletteTranscoder(Context context) {
        this(Glide.get(context).getBitmapPool());
    }

    public BitmapPaletteTranscoder(BitmapPool bitmapPool) {
        mBitmapPool = bitmapPool;
    }

    @Override
    public Resource<BitmapPaletteWrapper> transcode(Resource<Bitmap> bitmapResource) {
        Bitmap bitmap = bitmapResource.get();
        BitmapPaletteWrapper bitmapPaletteWrapper = new BitmapPaletteWrapper(bitmap, PhonographColorUtil.generatePalette(bitmap));
        return new BitmapPaletteResource(bitmapPaletteWrapper, mBitmapPool);
    }

    @Override
    public String getId() {
        return "BitmapPaletteTranscoder.com.bonrita.audiograph.glide.palette";
    }
}
