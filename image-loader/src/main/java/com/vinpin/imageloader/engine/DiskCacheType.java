package com.vinpin.imageloader.engine;

/**
 * 硬盘缓存类型
 *
 * @author vinpin
 *         create at 2018/01/31 10:37
 */
public interface DiskCacheType {
    /**
     * 不缓存任何内容
     */
    int NONE = 0;
    /**
     * 只缓存原始图片
     */
    int DATA = 1;
    /**
     * 只缓存转换过后的图片
     */
    int RESOURCE = 2;
    /**
     * 既缓存原始图片，也缓存转换过后的图片
     */
    int ALL = 3;
    /**
     * 根据图片资源智能地选择使用哪一种缓存策略（默认选项）
     */
    int AUTOMATIC = 4;
}
