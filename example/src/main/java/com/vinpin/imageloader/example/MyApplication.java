package com.vinpin.imageloader.example;

import android.app.Application;

import com.vinpin.imageloader.ImageLoader;

/**
 * // TODO
 *
 * @author zwp
 *         create at 2018/01/30 20:20
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 如果需要的话
        ImageLoader.onLowMemory(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 如果需要的话
        ImageLoader.onLowMemory(this);
    }
}
