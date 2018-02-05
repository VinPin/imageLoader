# imageLoader
Android 网络图片加载封装库
# 添加依赖
```java
compile 'com.vinpin:imageloader:1.0.0'
```
# 开始使用
采用策略设计模式，目前只实现了基于Glide4.5.0的策略，其他的等待扩展。

如果场景需要，在Application中
```java
public class MyApplication extends Application {
    ...
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 如果场景需要
        ImageLoader.onLowMemory(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 如果场景需要
        ImageLoader.onLowMemory(this);
    }
    ...
}
```

一. 基础使用结构，保留原汁原味的链式调用，使用起来就是爽
```java
ImageLoader.with(context).url(url).into(imageView);
```
加载本地资源图片
```java
ImageLoader.with(context).resourceId(resId).into(imageView);
```
加载网络图片
```java
ImageLoader.with(context).url(url).into(imageView);
```
加载文件图片
```java
ImageLoader.with(context).file(file).into(imageView);
```
加载bitmap图片
```java
ImageLoader.with(context).bitmap(bitmap).into(imageView);
```
加载drawable图片
```java
ImageLoader.with(context).drawable(drawable).into(imageView);
```

二. 占位符和错误符
```java
// 占位符
ImageLoader.with(context)
  .url(url)
  .placeholder(R.drawable.placeholder)
  .into(imageView);
// 错误符
ImageLoader.with(context)
  .url(url)
  .error(R.drawable.error)
  .into(imageView);
```

三. 进阶扩展

设置缩略图缩放倍数
```java
ImageLoader.with(context)
  .url(url)
  .thumbnail(0.5f)
  .into(imageView);
```
设置作为静态图加载
```java
ImageLoader.with(context)
  .url(url)
  .asBitmap()
  .into(imageView);
```
设置加载图片的分辨率
```java
ImageLoader.with(context)
  .url(url)
  .override(200,100)
  .into(imageView);
```
设置禁用内存缓存功能，默认false 开启内存缓存功能
```java
ImageLoader.with(context)
  .url(url)
  .skipMemoryCache(true)
  .into(imageView);
```
设置硬盘缓存类型，有以下几种类型：
  *  DiskCacheType.NONE // 不缓存任何内容
  *  DiskCacheType.DATA // 只缓存原始图片
  *  DiskCacheType.RESOURCE // 只缓存转换过后的图片
  *  DiskCacheType.ALL // 既缓存原始图片，也缓存转换过后的图片
  *  DiskCacheType.AUTOMATIC // 根据图片资源智能地选择使用哪一种缓存策略（默认选项）
```java
ImageLoader.with(context)
  .url(url)
  .diskCacheStrategy(DiskCacheType.NONE)
  .into(imageView);
```

# 混淆配置
由于目前只基于Glide4.5.0的封装，需要添加Glide的混淆配置
```java
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
```

# 意见反馈
如有问题或需要扩展其他的功能，请提issues，感谢您的配合！
