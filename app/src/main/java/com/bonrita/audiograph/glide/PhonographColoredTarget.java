package com.bonrita.audiograph.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bonrita.audiograph.R;
import com.bonrita.audiograph.glide.palette.BitmapPaletteTarget;
import com.bonrita.audiograph.glide.palette.BitmapPaletteWrapper;
import com.bonrita.audiograph.util.PhonographColorUtil;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.kabouzeid.appthemehelper.util.ATHUtil;


public abstract class PhonographColoredTarget extends BitmapPaletteTarget {

    public PhonographColoredTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onColorReady(getDefaultFooterColor());
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);
        onColorReady(PhonographColorUtil.getColor(resource.getPalette(), getDefaultFooterColor()));
    }

    protected int getDefaultFooterColor() {
        return ATHUtil.resolveColor(getView().getContext(), R.attr.defaultFooterColor);
    }

    protected int getAlbumArtistFooterColor() {
        return ATHUtil.resolveColor(getView().getContext(), R.attr.cardBackgroundColor);
    }

    public abstract void onColorReady(int color);

}
