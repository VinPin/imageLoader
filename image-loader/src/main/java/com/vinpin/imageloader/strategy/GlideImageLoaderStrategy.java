package com.vinpin.imageloader.strategy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vinpin.imageloader.engine.DiskCacheType;
import com.vinpin.imageloader.engine.ImageLoaderOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide加载图片策略实现类
 *
 * @author vinpin
 * create at 2018/01/29 16:39
 */
public class GlideImageLoaderStrategy implements ImageLoaderStrategy {

    @Override
    public ImageLoaderOptions.OptionBuilder with(@NonNull Context context) {
        return new ImageLoaderOptions.OptionBuilder(context);
    }

    @Override
    public void request(@NonNull final ImageLoaderOptions options) {
        RequestBuilder requestBuilder = getRequestBuilder(options);
        if (options.getTargetView() != null && options.getTargetView() instanceof ImageView) {
            ImageView targetView = (ImageView) options.getTargetView();
            RequestOptions requestOptions = getRequestOptions(options);
            requestBuilder.apply(requestOptions).into(targetView);
        }
    }

    @SuppressLint("CheckResult")
    @NonNull
    private RequestBuilder getRequestBuilder(@NonNull ImageLoaderOptions options) {
        RequestManager requestManager = Glide.with(options.getContext());
        RequestBuilder requestBuilder = options.asBitmap() ? requestManager.asBitmap() : requestManager.asDrawable();
        // 加载图片的来源
        if (!TextUtils.isEmpty(options.getUrl())) {
            requestBuilder.load(options.getUrl());
        } else if (options.getFile() != null) {
            requestBuilder.load(options.getFile());
        } else if (options.getBitmap() != null) {
            requestBuilder.load(options.getBitmap());
        } else if (options.getDrawable() != null) {
            requestBuilder.load(options.getDrawable());
        } else if (options.getResourceId() > 0) {
            requestBuilder.load(options.getResourceId());
        } else {
            requestBuilder.load((String) null);
        }
        // 设置缩略图缩放倍数
        if (options.getThumbSizeMultiplier() > 0) {
            requestBuilder.thumbnail(options.getThumbSizeMultiplier());
        }
        return requestBuilder;
    }

    @SuppressLint("CheckResult")
    @NonNull
    private RequestOptions getRequestOptions(@NonNull ImageLoaderOptions options) {
        RequestOptions requestOptions = new RequestOptions();
        // 设置加载占位图
        if (options.getPlaceholderDrawable() != null) {
            requestOptions.placeholder(options.getPlaceholderDrawable());
        }
        if (options.getPlaceholderId() > 0) {
            requestOptions.placeholder(options.getPlaceholderId());
        }
        // 设置加载失败图
        if (options.getErrorPlaceholder() != null) {
            requestOptions.error(options.getErrorPlaceholder());
        }
        if (options.getErrorId() > 0) {
            requestOptions.error(options.getErrorId());
        }
        // 设置后备回调符
        if (options.getFallbackDrawable() != null) {
            requestOptions.fallback(options.getFallbackDrawable());
        }
        if (options.getFallbackId() > 0) {
            requestOptions.fallback(options.getFallbackId());
        }
        // 设置图片加载的分辨
        if (options.getOverrideWidth() > 0 && options.getOverrideHeight() > 0) {
            requestOptions.override(options.getOverrideWidth(), options.getOverrideHeight());
        }
        setTransformation(requestOptions, options);
        // 设置禁用内存缓存功能
        if (options.isSkipMemoryCache()) {
            requestOptions.skipMemoryCache(true);
        }
        // 设置硬盘缓存方面策略
        DiskCacheStrategy strategy = diskCacheStrategy(options.getDiskCacheType());
        if (strategy != DiskCacheStrategy.AUTOMATIC) {
            requestOptions.diskCacheStrategy(strategy);
        }
        return requestOptions;
    }

    @SuppressLint("CheckResult")
    private void setTransformation(@NonNull RequestOptions requestOptions, @NonNull ImageLoaderOptions options) {
        if (options.isCenterCrop()) {
            requestOptions.centerCrop();
        }
        if (options.isFitCenter()) {
            requestOptions.fitCenter();
        }
        if (options.isCircleCrop()) {
            requestOptions.circleCrop();
        }
        if (options.isCenterInside()) {
            requestOptions.centerInside();
        }
        if (options.getBlurRadius() > 0) {
            requestOptions.transform(new BlurTransformation(options.getBlurRadius(), options.getBlurSampling()));
        }
        if (options.getRoundedCornersRadius() > 0) {
            requestOptions.transform(new RoundedCornersTransformation(options.getRoundedCornersRadius(), 0));
        }
    }

    @NonNull
    private DiskCacheStrategy diskCacheStrategy(int diskCacheType) {
        DiskCacheStrategy strategy;
        switch (diskCacheType) {
            case DiskCacheType.NONE:
                strategy = DiskCacheStrategy.NONE;
                break;
            case DiskCacheType.DATA:
                strategy = DiskCacheStrategy.DATA;
                break;
            case DiskCacheType.RESOURCE:
                strategy = DiskCacheStrategy.RESOURCE;
                break;
            case DiskCacheType.ALL:
                strategy = DiskCacheStrategy.ALL;
                break;
            default:
                strategy = DiskCacheStrategy.AUTOMATIC;
                break;
        }
        return strategy;
    }

    @Override
    public void pauseRequests(@NonNull Context context) {
        Glide.with(context).pauseRequestsRecursive();
    }

    @Override
    public void resumeRequests(@NonNull Context context) {
        Glide.with(context).resumeRequestsRecursive();
    }

    @Override
    public void clear(@NonNull Context context, View view) {
        Glide.with(context).clear(view);
    }

    @Override
    public void clearMemoryCache(@NonNull Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(@NonNull Context context) {
        Glide.get(context.getApplicationContext()).clearDiskCache();
    }

    @Override
    public void trimMemory(@NonNull Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @Override
    public void onLowMemory(@NonNull Context context) {
        Glide.get(context).onLowMemory();
    }
}
