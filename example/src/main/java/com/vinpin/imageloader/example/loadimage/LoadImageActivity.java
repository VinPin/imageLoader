package com.vinpin.imageloader.example.loadimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinpin.adapter.CommonAdapter;
import com.vinpin.adapter.base.ViewHolder;
import com.vinpin.imageloader.ImageLoader;
import com.vinpin.imageloader.example.R;
import com.vinpin.imageloader.example.bean.LoadImageInfo;
import com.vinpin.imageloader.example.config.ImageConfig;

import java.util.ArrayList;

/**
 * 基础使用之加载图片界面
 *
 * @author vinpin
 *         create at 2018/02/06 10:10
 */
public class LoadImageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<LoadImageInfo> mInfos;
    private CommonAdapter<LoadImageInfo> mAdapter;

    /**
     * 创建意图
     *
     * @param context 上下文
     */
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoadImageActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        findViewById();
        mInfos = mInfos == null ? new ArrayList<LoadImageInfo>() : mInfos;
        if (!mInfos.isEmpty()) {
            mInfos.clear();
        }
        for (int i = 0; i < 5; i++) {
            LoadImageInfo info = new LoadImageInfo();
            info.type = i;
            mInfos.add(info);
        }
        setRecyclerViewData();
    }

    private void findViewById() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onDestroy() {
        if (mInfos != null) {
            mInfos.clear();
            mInfos = null;
        }
        mAdapter = null;
        super.onDestroy();
    }

    private void setRecyclerViewData() {
        if (mAdapter == null) {
            mAdapter = new CommonAdapter<LoadImageInfo>(this, R.layout.item_load_image_list, mInfos) {
                @Override
                protected void convert(ViewHolder holder, LoadImageInfo loadImageInfo, int position) {
                    ImageView imageView = holder.getView(R.id.imageView);
                    TextView txtTitle = holder.getView(R.id.txt_title);
                    switch (loadImageInfo.type) {
                        case 0:
                            ImageLoader.with(LoadImageActivity.this)
                                    .resourceId(R.drawable.img_tmp)
                                    .placeholder(R.drawable.img_placeholder)
                                    .error(R.drawable.img_error)
                                    .into(imageView);
                            txtTitle.setText("加载资源图片");
                            break;
                        case 1:
                            ImageLoader.with(LoadImageActivity.this)
                                    .url(ImageConfig.url1)
                                    .placeholder(R.drawable.img_placeholder)
                                    .error(R.drawable.img_error)
                                    .into(imageView);
                            txtTitle.setText("加载网络url图片");
                            break;
                        case 2:
                            ImageLoader.with(LoadImageActivity.this)
                                    .url(ImageConfig.url2)
                                    .placeholder(R.drawable.img_placeholder)
                                    .error(R.drawable.img_error)
                                    .into(imageView);
                            txtTitle.setText("加载网络GIF图片");
                            break;
                        case 3:
                            ImageLoader.with(LoadImageActivity.this)
                                    .url(ImageConfig.url2)
                                    .placeholder(R.drawable.img_placeholder)
                                    .error(R.drawable.img_error)
                                    .asBitmap()
                                    .into(imageView);
                            txtTitle.setText("加载网络GIF图片作为静态图");
                            break;
                        case 4:
                            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.img_tmp);
                            ImageLoader.with(LoadImageActivity.this)
                                    .drawable(drawable)
                                    .placeholder(R.drawable.img_placeholder)
                                    .error(R.drawable.img_error)
                                    .into(imageView);
                            txtTitle.setText("加载drawable图片");
                            break;
                        default:
                            break;
                    }
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
