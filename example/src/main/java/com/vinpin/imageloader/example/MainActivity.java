package com.vinpin.imageloader.example;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.vinpin.imageloader.ImageLoader;
import com.vinpin.imageloader.example.loadimage.LoadImageActivity;
import com.vinpin.imageloader.example.loadimage.LoadImageAdvancedActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    private void findViewById() {
        findViewById(R.id.txt_load_image).setOnClickListener(this);
        findViewById(R.id.txt_loadimage_advanced).setOnClickListener(this);
        findViewById(R.id.txt_clear_memory).setOnClickListener(this);
        findViewById(R.id.txt_clear_disk).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_load_image:
                startActivity(LoadImageActivity.newIntent(this));
                break;
            case R.id.txt_loadimage_advanced:
                startActivity(LoadImageAdvancedActivity.newIntent(this));
                break;
            case R.id.txt_clear_memory:
                ImageLoader.clearMemoryCache(this);
                Toast.makeText(this, "清除内存缓存成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_clear_disk:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader.clearDiskCache(MainActivity.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "清除磁盘缓存成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}