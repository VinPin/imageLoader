package com.vinpin.imageloader.example.loadimage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
 * 进阶使用之加载图片界面
 *
 * @author vinpin
 * create at 2018/02/06 10:10
 */
public class LoadImageAdvancedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<LoadImageInfo> mInfos;
    private CommonAdapter<LoadImageInfo> mAdapter;

    /**
     * 创建意图
     *
     * @param context 上下文
     */
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoadImageAdvancedActivity.class);
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
        for (int i = 0; i < 8; i++) {
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
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .thumbnail(0.5f)
                                    .into(imageView);
                            txtTitle.setText("缩略图缩放倍数");
                            break;
                        case 1:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .override(100, 100)
                                    .fitCenter()
                                    .into(imageView);
                            txtTitle.setText("加载图片的分辨率");
                            break;
                        case 2:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .fitCenter()
                                    .into(imageView);
                            txtTitle.setText("变换之fitCenter");
                            break;
                        case 3:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .centerCrop()
                                    .into(imageView);
                            txtTitle.setText("变换之centerCrop");
                            break;
                        case 4:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .centerInside()
                                    .into(imageView);
                            txtTitle.setText("变换之centerInside");
                            break;
                        case 5:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .circleCrop()
                                    .into(imageView);
                            txtTitle.setText("变换之circleCrop");
                            break;
                        case 6:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .blur(25, 5)
                                    .into(imageView);
                            txtTitle.setText("变换之高斯模糊");
                            break;
                        case 7:
                            ImageLoader.with(LoadImageAdvancedActivity.this)
                                    .url(ImageConfig.url1)
                                    .roundedCorners(50)
                                    .into(imageView);
                            txtTitle.setText("变换之圆角");
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
