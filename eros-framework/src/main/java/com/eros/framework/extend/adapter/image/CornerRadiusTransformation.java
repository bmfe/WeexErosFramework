package com.eros.framework.extend.adapter.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.eros.framework.utils.ImageCornerUtil;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class CornerRadiusTransformation extends BitmapTransformation {
    private float[] mRadius;
    private CornerRect mCornerRect;

    public CornerRadiusTransformation(float[] radius) {
        this.mRadius = radius;
        this.mCornerRect = new CornerRect(mRadius);
    }


    public void updateCornerRadius(float[] change) {
        mRadius = change;
        mCornerRect.setRadius(mRadius);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int
            outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }


    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        return ImageCornerUtil.toRoundCorner(source, mCornerRect.getRadiusPx(), mCornerRect
                .getCornerEdge());
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }


    public static class CornerRect {
        private float[] radius;
        private int edge;
        private int radiusPx;

        public CornerRect(float[] radius) {
            this.radius = radius;
            initRadius();
        }

        private void initRadius() {
            int edge = ImageCornerUtil.CORNER_ALL;
            int radiusPx = 0;
            if (radius[0] <= 0 && radius[1] <= 0) {
                edge ^= ImageCornerUtil.CORNER_TOP_LEFT;
            } else {
                radiusPx = Math.round(radius[0]);
            }

            if (radius[2] <= 0 && radius[3] <= 0) {
                edge ^= ImageCornerUtil.CORNER_TOP_RIGHT;
            } else {
                radiusPx = Math.round(radius[2]);
            }

            if (radius[4] <= 0 && radius[5] <= 0) {
                edge ^= ImageCornerUtil.CORNER_BOTTOM_RIGHT;
            } else {
                radiusPx = Math.round(radius[4]);
            }

            if (radius[6] <= 0 && radius[7] <= 0) {
                edge ^= ImageCornerUtil.CORNER_BOTTOM_LEFT;
            } else {
                radiusPx = Math.round(radius[6]);
            }

            this.edge = edge;
            this.radiusPx = radiusPx;
        }


        public int getRadiusPx() {

            return this.radiusPx;
        }

        public int getCornerEdge() {
            return this.edge;
        }

        public void setRadius(float[] chage) {
            this.radius = chage;
            //重新计算弧度
            initRadius();
        }
    }
}
