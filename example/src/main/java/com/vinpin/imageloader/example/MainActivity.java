package com.vinpin.imageloader.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.vinpin.imageloader.ImageLoader;
import com.vinpin.imageloader.engine.DiskCacheType;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    private void findViewById() {
        imageView = findViewById(R.id.imageView);
        findViewById(R.id.txt_url).setOnClickListener(this);
        findViewById(R.id.txt_resid).setOnClickListener(this);
        findViewById(R.id.txt_reset).setOnClickListener(this);
    }

    private String[] urls = new String[]{
            "http://s1.dwstatic.com/group1/M00/86/4A/81beb00a44bc52b4fdd46285de8f8f00.png",
            "https://isparta.github.io/compare-webp/image/gif_webp/gif/1.gif",
            "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2796659031,1466769776&fm=80&w=179&h=119&img.JPEG"
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_url:
                int index = new Random().nextInt(3);
                ImageLoader.with(this).url(urls[index])
                        .placeholder(R.drawable.img_placeholder)
                        .error(R.drawable.img_error)
                        .thumbnail(0.5f)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheType.RESOURCE)
                        .into(imageView);
                break;
            case R.id.txt_resid:
                ImageLoader.with(this).resourceId(R.drawable.img_tmp).into(imageView);
                break;
            case R.id.txt_reset:
                ImageLoader.clearMemoryCache(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader.clearDiskCache(MainActivity.this);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}