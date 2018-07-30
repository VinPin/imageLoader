package com.vinpin.imageloader.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.view.View;

import com.vinpin.imageloader.ImageLoader;

import java.io.File;

/**
 * 图片请求选项类
 *
 * @author vinpin
 * create at 2018/01/29 16:19
 */
@SuppressWarnings("unused")
public class ImageLoaderOptions {

    private Context context;

    private String url;
    private File file;
    private Bitmap bitmap;
    private Drawable drawable;
    private int resourceId;

    private float thumbSizeMultiplier;
    private boolean asBitmap;

    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderId;
    @Nullable
    private Drawable errorPlaceholder;
    private int errorId;
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int overrideWidth;
    private int overrideHeight;

    private boolean centerCrop;
    private boolean fitCenter;
    private boolean circleCrop;
    private boolean centerInside;
    private int blurRadius;
    private int blurSampling;
    private int roundedCornersRadius;

    private boolean skipMemoryCache;
    private int diskCacheType;

    private View targetView;

    ImageLoaderOptions(OptionBuilder builder) {
        this.context = builder.context;
        this.url = builder.url;
        this.file = builder.file;
        this.bitmap = builder.bitmap;
        this.drawable = builder.drawable;
        this.resourceId = builder.resourceId;
        this.thumbSizeMultiplier = builder.thumbSizeMultiplier;
        this.asBitmap = builder.asBitmap;

        this.placeholderDrawable = builder.placeholderDrawable;
        this.placeholderId = builder.placeholderId;
        this.errorPlaceholder = builder.errorPlaceholder;
        this.errorId = builder.errorId;
        this.fallbackDrawable = builder.fallbackDrawable;
        this.fallbackId = builder.fallbackId;
        this.overrideWidth = builder.overrideWidth;
        this.overrideHeight = builder.overrideHeight;

        this.centerCrop = builder.centerCrop;
        this.fitCenter = builder.fitCenter;
        this.circleCrop = builder.circleCrop;
        this.centerInside = builder.centerInside;
        this.blurRadius = builder.blurRadius;
        this.blurSampling = builder.blurSampling;
        this.roundedCornersRadius = builder.roundedCornersRadius;

        this.skipMemoryCache = builder.skipMemoryCache;
        this.diskCacheType = builder.diskCacheType;

        this.targetView = builder.targetView;
    }

    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public File getFile() {
        return file;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public float getThumbSizeMultiplier() {
        return thumbSizeMultiplier;
    }

    public boolean asBitmap() {
        return asBitmap;
    }

    public int getResourceId() {
        return resourceId;
    }

    @Nullable
    public Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public int getPlaceholderId() {
        return placeholderId;
    }

    @Nullable
    public Drawable getErrorPlaceholder() {
        return errorPlaceholder;
    }

    public int getErrorId() {
        return errorId;
    }

    @Nullable
    public Drawable getFallbackDrawable() {
        return fallbackDrawable;
    }

    public int getFallbackId() {
        return fallbackId;
    }

    public int getOverrideWidth() {
        return overrideWidth;
    }

    public int getOverrideHeight() {
        return overrideHeight;
    }

    public boolean isCenterCrop() {
        return centerCrop;
    }

    public boolean isFitCenter() {
        return fitCenter;
    }

    public boolean isCircleCrop() {
        return circleCrop;
    }

    public boolean isCenterInside() {
        return centerInside;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public int getBlurSampling() {
        return blurSampling;
    }

    public int getRoundedCornersRadius() {
        return roundedCornersRadius;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public int getDiskCacheType() {
        return diskCacheType;
    }

    public View getTargetView() {
        return targetView;
    }

    void request() {
        ImageLoader.getActualImageLoader().request(this);
    }

    public static class OptionBuilder {

        private Context context;
        private String url;
        private File file;
        private Bitmap bitmap;
        private Drawable drawable;
        private int resourceId;

        private float thumbSizeMultiplier;
        private boolean asBitmap = false;

        @Nullable
        private Drawable placeholderDrawable;
        private int placeholderId;
        @Nullable
        private Drawable errorPlaceholder;
        private int errorId;
        @Nullable
        private Drawable fallbackDrawable;
        private int fallbackId;
        private int overrideWidth;
        private int overrideHeight;

        private boolean centerCrop;
        private boolean fitCenter;
        private boolean circleCrop;
        private boolean centerInside;
        private int blurRadius;
        private int blurSampling;
        private int roundedCornersRadius;

        private boolean skipMemoryCache = false;
        private int diskCacheType = DiskCacheType.AUTOMATIC;

        private View targetView;

        public OptionBuilder(Context context) {
            this.context = context;
        }

        /**
         * 设置网络图片
         *
         * @param url 网络图片url
         * @return This option builder.
         */
        public OptionBuilder url(@Nullable String url) {
            this.url = url;
            return this;
        }

        /**
         * 设置加载图片文件
         *
         * @param file 图片文件
         * @return This option builder.
         */
        public OptionBuilder file(@Nullable File file) {
            this.file = file;
            return this;
        }

        /**
         * 加载bitmap图片
         *
         * @param bitmap bitmap图片
         * @return This option builder.
         */
        public OptionBuilder bitmap(@Nullable Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        /**
         * 加载drawable图片
         *
         * @param drawable drawable图片
         * @return This option builder.
         */
        public OptionBuilder drawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        /**
         * 设置加载本地资源图片
         *
         * @param resourceId 本地资源图片
         * @return This option builder.
         */
        public OptionBuilder resourceId(@RawRes @DrawableRes int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        /**
         * 设置缩略图缩放倍数
         *
         * @param sizeMultiplier sizeMultiplier must be between 0 and 1
         * @return This option builder.
         */
        public OptionBuilder thumbnail(float sizeMultiplier) {
            this.thumbSizeMultiplier = (sizeMultiplier < 0 || sizeMultiplier > 1) ? 0 : sizeMultiplier;
            return this;
        }

        /**
         * 设置作为静态图加载
         *
         * @return This option builder.
         */
        public OptionBuilder asBitmap() {
            this.asBitmap = true;
            return this;
        }

        /**
         * 设置占位符
         *
         * @param drawable The drawable to display as a placeholder.
         * @return This option builder.
         */
        public OptionBuilder placeholder(@Nullable Drawable drawable) {
            this.placeholderDrawable = drawable;
            return this;
        }

        /**
         * 设置占位符
         *
         * @param resourceId The id of the resource to use as a placeholder
         * @return This option builder.
         */
        public OptionBuilder placeholder(@DrawableRes int resourceId) {
            this.placeholderId = resourceId;
            return this;
        }

        /**
         * 设置错误符
         *
         * @param drawable The drawable to display.
         * @return This option builder.
         */
        public OptionBuilder error(@Nullable Drawable drawable) {
            this.errorPlaceholder = drawable;
            return this;
        }

        /**
         * 设置错误符
         *
         * @param resourceId The id of the resource to use as a placeholder.
         * @return This option builder.
         */
        public OptionBuilder error(@DrawableRes int resourceId) {
            this.errorId = resourceId;
            return this;
        }

        /**
         * 设置后备回调符
         *
         * @param drawable The drawable to display as a placeholder.
         * @return This option builder.
         */
        public OptionBuilder fallback(@Nullable Drawable drawable) {
            this.fallbackDrawable = drawable;
            return this;
        }

        /**
         * 设置后备回调符
         *
         * @param resourceId The id of the resource to use as a fallback.
         * @return This option builder.
         */
        public OptionBuilder fallback(@DrawableRes int resourceId) {
            this.fallbackId = resourceId;
            return this;
        }

        /**
         * 设置加载图片的分辨率
         *
         * @param width  The width in pixels to use to load the resource.
         * @param height The height in pixels to use to load the resource.
         * @return This option builder.
         */
        public OptionBuilder override(int width, int height) {
            this.overrideWidth = width;
            this.overrideHeight = height;
            return this;
        }

        /**
         * 设置图片ScaleType
         *
         * @return This option builder.
         */
        public OptionBuilder centerCrop() {
            this.centerCrop = true;
            return this;
        }

        /**
         * 设置图片ScaleType
         *
         * @return This option builder.
         */
        public OptionBuilder fitCenter() {
            this.fitCenter = true;
            return this;
        }

        /**
         * 设置图片ScaleType
         *
         * @return This option builder.
         */
        public OptionBuilder circleCrop() {
            this.circleCrop = true;
            return this;
        }

        /**
         * 设置图片ScaleType
         *
         * @return This option builder.
         */
        public OptionBuilder centerInside() {
            this.centerInside = true;
            return this;
        }

        /**
         * 高斯模糊
         *
         * @param radius   模糊度,取值[1,50]
         * @param sampling 缩放倍数,默认1
         * @return This option builder.
         */
        public OptionBuilder blur(int radius, int sampling) {
            if (radius > 0) {
                this.blurRadius = radius;
            }
            this.blurSampling = sampling <= 1 ? 1 : sampling;
            return this;
        }

        /**
         * 圆角
         *
         * @param radius 圆角半径，像素
         * @return This option builder.
         */
        public OptionBuilder roundedCorners(int radius) {
            if (radius > 0) {
                this.roundedCornersRadius = radius;
            }
            return this;
        }

        /**
         * 设置禁用内存缓存功能，默认false 开启内存缓存功能
         *
         * @param skip True to allow the resource to skip the memory cache.
         * @return This option builder.
         */
        public OptionBuilder skipMemoryCache(boolean skip) {
            this.skipMemoryCache = skip;
            return this;
        }

        /**
         * 设置硬盘缓存类型 {@link DiskCacheType}
         *
         * @param diskCacheType 硬盘缓存类型
         * @return This option builder.
         */
        public OptionBuilder diskCacheStrategy(int diskCacheType) {
            this.diskCacheType = diskCacheType;
            return this;
        }

        /**
         * 加载图片到目标控件显示
         *
         * @param target 目标显示控件，最好是ImageView及其子类
         */
        public void into(View target) {
            this.targetView = target;
            new ImageLoaderOptions(this).request();
        }
    }
}
