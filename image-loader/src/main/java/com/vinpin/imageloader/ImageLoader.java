package com.vinpin.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.vinpin.imageloader.engine.ImageLoaderOptions;
import com.vinpin.imageloader.strategy.GlideImageLoaderStrategy;
import com.vinpin.imageloader.strategy.ImageLoaderStrategy;

/**
 * 图片管理类，提供对外接口。使用策略模式。
 *
 * @author vinpin
 *         create at 2018/01/29 16:33
 */
public class ImageLoader {

    /**
     * 默认磁盘缓存大小,250M
     */
    private static final int DISK_CACHE_SIZE = 250;

    private static ImageLoaderStrategy imageLoaderStrategyImpl;

    private ImageLoader() {

    }

    /**
     * 获取实际的图片加载者
     *
     * @return ImageLoaderStrategy
     */
    public static ImageLoaderStrategy getActualImageLoader() {
        if (imageLoaderStrategyImpl == null) {
            imageLoaderStrategyImpl = new GlideImageLoaderStrategy();
        }
        return imageLoaderStrategyImpl;
    }

    /**
     * 获取图片参数构建者
     *
     * @param context 上下文
     * @return a option builder.
     */
    public static ImageLoaderOptions.OptionBuilder with(@NonNull Context context) {
        return getActualImageLoader().with(context);
    }

    /**
     * 请求加载图片
     *
     * @param options 图片参数
     */
    public static void request(@NonNull ImageLoaderOptions options) {
        getActualImageLoader().request(options);
    }

    /**
     * 暂停加载图片
     *
     * @param context 上下文
     */
    public static void pauseRequests(@NonNull Context context) {
        getActualImageLoader().pauseRequests(context);
    }

    /**
     * 恢复加载图片
     *
     * @param context 上下文
     */
    public static void resumeRequests(@NonNull Context context) {
        getActualImageLoader().resumeRequests(context);
    }

    /**
     * 清理view的缓存
     *
     * @param context 上下文
     * @param view    The view to cancel loads and free resources for.
     */
    public static void clear(@NonNull Context context, View view) {
        getActualImageLoader().clear(context, view);
    }

    /**
     * 清理内存缓存，运行在主线程中
     *
     * @param context 上下文
     */
    public static void clearMemoryCache(@NonNull Context context) {
        getActualImageLoader().clearMemoryCache(context);
    }

    /**
     * 清理磁盘缓存，运行在子线程中
     *
     * @param context 上下文
     */
    public static void clearDiskCache(@NonNull Context context) {
        getActualImageLoader().clearDiskCache(context);
    }

    public static void trimMemory(@NonNull Context context, int level) {
        getActualImageLoader().trimMemory(context, level);
    }

    public static void onLowMemory(@NonNull Context context) {
        getActualImageLoader().onLowMemory(context);
    }
}
