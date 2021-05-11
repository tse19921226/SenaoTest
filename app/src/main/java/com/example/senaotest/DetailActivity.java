package com.example.senaotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private ImageView ivMainPic;
    private TextView tvMainID;
    private TextView tvMainName;
    private TextView tvFinalPrice;
    private TopBarView topBarView;
    private StoreItem storeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        storeItem = (StoreItem) getIntent().getSerializableExtra(DataManager.SelectItem_Key);
        Log.d(TAG, "storeItem.finalPrice = " + storeItem.finalPrice);
        initData();
    }

    private void initView(){
        topBarView = findViewById(R.id.topBar);
        ivMainPic = findViewById(R.id.iv_detail_pic);
        tvMainID = findViewById(R.id.tv_main_id);
        tvMainName = findViewById(R.id.tv_main_name);
        tvFinalPrice = findViewById(R.id.tv_detail_final_price);
        topBarView.setViewType(TopBarView.DETAIL_TYPE);
        topBarView.setup();
        topBarView.registerTopBarCallBack(topBarCallBack);
    }

    private void initData(){
        Glide.with(this).load(storeItem.imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivMainPic);
        tvMainID.setText("產品編號 : " + storeItem.martId);
        tvMainName.setText(storeItem.martName);
        tvFinalPrice.setText("$"+storeItem.finalPrice);
    }

    private TopBarView.TopBarCallBack topBarCallBack = new TopBarView.TopBarCallBack() {
        @Override
        public void onClick() {
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        topBarView.unregisterTopBarCallBack();
    }
}