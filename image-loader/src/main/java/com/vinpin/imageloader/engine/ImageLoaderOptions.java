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
 * 图片参数选项类
 *
 * @author vinpin
 *         create at 2018/01/29 16:19
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

    private int placeholderResId;
    private int errorResId;
    private int overrideWidth;
    private int overrideHeight;
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

        this.placeholderResId = builder.placeholderResId;
        this.errorResId = builder.errorResId;
        this.overrideWidth = builder.overrideWidth;
        this.overrideHeight = builder.overrideHeight;
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

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public int getOverrideWidth() {
        return overrideWidth;
    }

    public int getOverrideHeight() {
        return overrideHeight;
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

    /**
     * 静态构建者模式
     */
    public static class OptionBuilder {

        private Context context;
        private String url;
        private File file;
        private Bitmap bitmap;
        private Drawable drawable;
        private int resourceId;

        private float thumbSizeMultiplier;
        private boolean asBitmap = false;

        private int placeholderResId;
        private int errorResId;
        private int overrideWidth;
        private int overrideHeight;
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
         * @param placeholderResId 占位符图片的资源id
         * @return This option builder.
         */
        public OptionBuilder placeholder(int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        /**
         * 设置错误符
         *
         * @param errorResId 错误符图片的资源id
         * @return This option builder.
         */
        public OptionBuilder error(int errorResId) {
            this.errorResId = errorResId;
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
