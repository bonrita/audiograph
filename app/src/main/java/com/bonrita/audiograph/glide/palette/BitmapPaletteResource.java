package com.bonrita.audiograph.glide.palette;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;


public class BitmapPaletteResource implements Resource<BitmapPaletteWrapper> {

    private final BitmapPaletteWrapper mBitmapPaletteWrapper;
    private final BitmapPool mBitmapPool;

    public BitmapPaletteResource(BitmapPaletteWrapper bitmapPaletteWrapper, BitmapPool bitmapPool) {
        mBitmapPaletteWrapper = bitmapPaletteWrapper;
        mBitmapPool = bitmapPool;
    }

    @Override
    public BitmapPaletteWrapper get() {
        return mBitmapPaletteWrapper;
    }

    @Override
    public int getSize() {
        return Util.getBitmapByteSize(mBitmapPaletteWrapper.getBitmap());
    }

    @Override
    public void recycle() {
        if (!mBitmapPool.put(mBitmapPaletteWrapper.getBitmap())) {
            mBitmapPaletteWrapper.getBitmap().recycle();
        }

    }
}
