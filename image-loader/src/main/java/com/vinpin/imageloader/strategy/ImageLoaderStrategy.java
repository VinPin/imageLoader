package com.vinpin.imageloader.strategy;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;

import com.vinpin.imageloader.engine.ImageLoaderOptions;

/**
 * 图片加载策略接口
 *
 * @author vinpin
 *         create at 2018/01/29 16:24
 */
public interface ImageLoaderStrategy {

    /**
     * 构建图片参数
     *
     * @param context 上下文
     * @return 图片参数构建者
     */
    ImageLoaderOptions.OptionBuilder with(@NonNull Context context);

    /**
     * 请求加载图片
     *
     * @param options 图片参数
     */
    void request(@NonNull final ImageLoaderOptions options);

    /**
     * 暂停加载图片
     *
     * @param context 上下文
     */
    void pauseRequests(@NonNull Context context);

    /**
     * 恢复加载图片
     *
     * @param context 上下文
     */
    void resumeRequests(@NonNull Context context);

    /**
     * 清理view的缓存
     *
     * @param context 上下文
     * @param view    The view to cancel loads and free resources for.
     */
    void clear(@NonNull Context context, View view);

    /**
     * 清理内存缓存
     *
     * @param context 上下文
     */
    void clearMemoryCache(@NonNull Context context);

    /**
     * 清理磁盘缓存
     *
     * @param context 上下文
     */
    void clearDiskCache(@NonNull Context context);

    void trimMemory(@NonNull Context context, int level);

    void onLowMemory(@NonNull Context context);
}
